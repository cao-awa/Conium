package com.github.cao.awa.conium.bedrock.event.context

import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem
import com.github.cao.awa.conium.bedrock.system.BedrockSystem
import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class BedrockEventContext(val scriptSource: Any) {
    companion object {
        val system: BedrockSystem get() = this._system
        var _system: BedrockSystem = BedrockSystem()
        var currentPosting: Any? = null
        val contexts: MutableMap<Any, BedrockEventContext?> = CollectionFactor.hashMap()

        fun newSystem() {
            this._system = BedrockSystem()
        }

        @JvmStatic
        fun post(scriptSource: Any, body: () -> Unit) {
            this.currentPosting = scriptSource
            body()
        }

        fun completePost() {
            this.currentPosting = null
        }

        fun request(body: () -> Unit) = body()

        fun clearContext(scriptSource: Any) {
            this.contexts[scriptSource] = null
        }

        @JvmStatic
        fun accessWorld(scriptSource: Any): AbstractBedrockWorld = this.contexts[scriptSource]?.world ?: BedrockWorld.DUMMY

        @JvmStatic
        fun accessSystem(): AbstractBedrockSystem = this.system
    }

    private val world: AbstractBedrockWorld get() = world()

    private val system: AbstractBedrockSystem get() = accessSystem()

    abstract fun world(): BedrockWorld
}
