package com.github.cao.awa.conium.util

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Concludes the process once the client has fully shut down.
 *
 * Architectury Transformer 5.2.92 leaks a non-daemon thread pool: `ReadClasspathProviderImpl`
 * builds one with `Executors.newFixedThreadPool(availableProcessors())` and never shuts it down.
 * The leaked workers park in `ThreadPoolExecutor.getTask`, which swallows `InterruptedException`
 * and retries, so they can never be woken; `Thread.stop` throws `UnsupportedOperationException`
 * since JDK 20. Nothing can reap them, and the JVM therefore never leaves `DestroyJavaVM`.
 *
 * Minecraft starts `ClientShutdownWatchdog` as soon as `Minecraft.run` returns and force exits
 * with `-8` after 15 seconds. This guard wins that race by exiting normally, which also lets the
 * shutdown hooks (log flushing, `ConiumThreadPool` termination) finish properly.
 *
 * It is armed at the tail of `Minecraft.close`, which only runs after the world has already been
 * saved and disconnected, so reacting there cannot lose world data.
 *
 * @author cao_awa
 * @author 草二号机
 * @since 1.0.0
 */
class ConiumShutdownGuard {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumShutdownGuard")

        /**
         * How long, in milliseconds, the JVM is given to leave on its own before it is concluded.
         */
        private const val GRACE_MILLIS: Long = 2_000L

        private val installed: AtomicBoolean = AtomicBoolean(false)

        /**
         * Arm the shutdown fallback. Must be called on the game thread, and is a no-op when called
         * again, so that a repeated `close` cannot install a second guard.
         */
        @JvmStatic
        fun install() {
            if (!this.installed.compareAndSet(false, true)) {
                return
            }

            val gameThread: Thread = Thread.currentThread()

            Thread({ conclude(gameThread) }, "ConiumShutdownGuard").apply {
                // The guard must never be the reason that the JVM stays alive.
                this.isDaemon = true
                this.priority = Thread.NORM_PRIORITY
                this.start()
            }
        }

        private fun conclude(gameThread: Thread) {
            try {
                Thread.sleep(GRACE_MILLIS)
            } catch (exception: InterruptedException) {
                // Being interrupted means the JVM is already on its way out.
                Thread.currentThread().interrupt()
                return
            }

            if (gameThread.isAlive) {
                // `Main.main` has not returned yet, so the client is still legitimately closing.
                return
            }

            LOGGER.warn(
                "The client did not exit within {}ms after closing, force concluding the process "
                        + "to avoid the shutdown watchdog crash",
                GRACE_MILLIS
            )

            // Runs the shutdown hooks and then halts, instead of waiting for the leaked
            // non-daemon threads that will never terminate.
            System.exit(0)
        }
    }
}
