package com.github.cao.awa.conium.threadpool

import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class ConiumThreadPool {
    companion object {
        @JvmStatic
        val INSTANCE: ConiumThreadPool = ConiumThreadPool()

        @JvmStatic
        fun submit(task: Runnable) {
            this.INSTANCE.execute(task)
        }
    }

    private val threadCounter: AtomicInteger = AtomicInteger()

    /**
     * The threads must be daemon, otherwise they will keep the JVM alive after the client
     * shuts down, which leads to the "Client shutdown from post-main" watchdog crash.
     *
     * @author cao_awa
     * @author 草二号机
     * @since 1.0.0
     */
    private val executor: ScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(12) { task ->
        Thread(task, "ConiumThreadPool-${this.threadCounter.incrementAndGet()}").apply {
            this.isDaemon = true
            this.priority = Thread.NORM_PRIORITY
        }
    }.apply {
        this.removeOnCancelPolicy = true
        this.setExecuteExistingDelayedTasksAfterShutdownPolicy(false)
        this.setContinueExistingPeriodicTasksAfterShutdownPolicy(false)

        Runtime.getRuntime().addShutdownHook(Thread {
            this.shutdown()

            // Give the in-flight tasks a chance to finish before the JVM goes down.
            this.awaitTermination(1, TimeUnit.SECONDS)
        })
    }

    fun execute(task: Runnable) {
        this.executor.execute(task)
    }
}
