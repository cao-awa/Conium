package com.github.cao.awa.conium.bedrock.system

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.conium.bedrock.system.task.ConiumTask
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.server.MinecraftServer

class BedrockSystem : AbstractBedrockSystem() {
    private val tasks: MutableMap<Int, ConiumTask> = CollectionFactor.hashMap()
    private val onceTasks: MutableMap<Int, ConiumTask> = CollectionFactor.hashMap()

    override fun runInterval(action: () -> Unit, interval: Int): Int {
        println("RunningInterval")
        val id = this.tasks.size
        this.tasks[id] = ConiumTask(interval, action)
        return id
    }

    private fun runOnce(action: () -> Unit) {
        this.onceTasks[this.onceTasks.size] = ConiumTask(action = action)
    }

    override fun clearRun(id: IntegerReceptacle) {
        runOnce {
            this.tasks.remove(id.get())
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
