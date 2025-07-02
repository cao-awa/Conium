package com.github.cao.awa.conium.bedrock.event.context

import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem
import com.github.cao.awa.conium.bedrock.system.BedrockSystem
import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class BedrockEventContext(val scriptSource: Any) {
    companion object {
        val system: BedrockSystem get() = _system
        var _system: BedrockSystem = BedrockSystem()
        var currentPosting: Any? = null
        val contexts: MutableMap<Any, BedrockEventContext?> = CollectionFactor.hashMap()

        fun newSystem() {
            _system = BedrockSystem()
        }

        @JvmStatic
        fun post(scriptSource: Any, body: () -> Unit) {
            currentPosting = scriptSource
            body()
        }

        fun completePost() {
            currentPosting = null
        }

        fun request(body: () -> Unit) = body()

        fun clearContext(scriptSource: Any) {
            contexts[scriptSource] = null
        }

        @JvmStatic
        fun accessWorld(scriptSource: Any): AbstractBedrockWorld = contexts[scriptSource]?.world ?: BedrockWorld.DUMMY

        @JvmStatic
        fun accessSystem(): AbstractBedrockSystem = system
    }

    private val world: AbstractBedrockWorld get() = world()

    private val system: AbstractBedrockSystem get() = accessSystem()

    abstract fun world(): BedrockWorld
}
