package com.github.cao.awa.conium.bedrock.impl.system

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.impl.system.task.ConiumTask
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.server.MinecraftServer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.collections.iterator

@BedrockScriptApi
class BedrockSystem : AbstractBedrockSystem() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("BedrockSystem")
    }
    private val tasks: MutableMap<Int, ConiumTask> = CollectionFactor.hashMap()
    private val onceTasks: MutableMap<Int, ConiumTask> = CollectionFactor.hashMap()

    override fun runInterval(callback: () -> Unit, tickInterval: Int): Int {
        Conium.debug("RunningInterval", LOGGER::info)
        val id: Int = this.tasks.size
        this.tasks[id] = ConiumTask(tickInterval, callback)
        return id
    }

    private fun runOnce(action: () -> Unit) {
        this.onceTasks[this.onceTasks.size] = ConiumTask(action = action)
    }

    override fun clearRun(runId: Int?) {
        if (runId == null) {
            return
        }
        runOnce {
            this.tasks.remove(runId)
        }
    }

    fun tick(server: MinecraftServer) {
        for (task in this.tasks) {
            task.value.tick()
        }

        for (onceTask in this.onceTasks) {
            onceTask.value.tick()
        }

        this.onceTasks.clear()
    }
}
