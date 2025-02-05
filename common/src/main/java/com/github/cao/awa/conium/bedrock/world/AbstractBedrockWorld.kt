package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.before.BedrockWorldBeforeEvents
import com.github.cao.awa.conium.bedrock.entity.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.event.after.BedrockWorldAfterEvents

@BedrockScriptApiFacade("World")
abstract class AbstractBedrockWorld {
    @BedrockScriptApiFacade("World", "beforeEvents")
    val beforeEvents: BedrockWorldBeforeEvents get() = eventsBefore()
    @BedrockScriptApiFacade("World", "afterEvents")
    val afterEvents: BedrockWorldAfterEvents get() = eventsAfter()

    abstract fun getPlayers(): BedrockPlayerDelegate

    private fun eventsBefore(): BedrockWorldBeforeEvents = BedrockWorldBeforeEvents.EVENTS

    private fun eventsAfter(): BedrockWorldAfterEvents = BedrockWorldAfterEvents.EVENTS
}
