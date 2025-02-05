package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.before.BedrockBeforeEvents
import com.github.cao.awa.conium.bedrock.entity.player.delegate.BedrockPlayerDelegate

@BedrockScriptApiFacade("World")
abstract class AbstractBedrockWorld {
    @BedrockScriptApiFacade("World", "beforeEvents")
    val beforeEvents: BedrockBeforeEvents get() = eventsBefore()

    abstract fun getPlayers(): BedrockPlayerDelegate

    private fun eventsBefore(): BedrockBeforeEvents = BedrockBeforeEvents.EVENTS
}
