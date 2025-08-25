package com.github.cao.awa.conium.threadpool

import java.util.concurrent.ScheduledThreadPoolExecutor

class ConiumThreadPool {
    companion object {
        @JvmStatic
        val INSTANCE: ConiumThreadPool = ConiumThreadPool()

        @JvmStatic
        fun submit(task: Runnable) {
            this.INSTANCE.execute(task)
        }
    }
    private val executor: ScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(12)

    fun execute(task: Runnable) {
        this.executor.execute(task)
    }
}