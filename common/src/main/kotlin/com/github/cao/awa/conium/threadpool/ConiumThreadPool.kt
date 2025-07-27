package com.github.cao.awa.conium.threadpool

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ConiumThreadPool {
    companion object {
        val INSTANCE: ConiumThreadPool = ConiumThreadPool()

        @JvmStatic
        fun execute(task: Runnable) {
            this.INSTANCE.execute(task)
        }
    }
    private val executor: ScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(12)

    fun execute(task: Runnable) {
        this.executor.execute(task)
    }
}