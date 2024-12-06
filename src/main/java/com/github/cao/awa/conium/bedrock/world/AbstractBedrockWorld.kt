package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.BedrockBeforeEvents
import com.github.cao.awa.conium.bedrock.world.player.delegate.BedrockPlayerDelegate

@BedrockScriptApiFacade("World")
abstract class AbstractBedrockWorld {
    val beforeEvents: BedrockBeforeEvents get() = eventsBefore()

    abstract fun getPlayers(): BedrockPlayerDelegate

    private fun eventsBefore(): BedrockBeforeEvents = BedrockBeforeEvents.EVENTS
}
