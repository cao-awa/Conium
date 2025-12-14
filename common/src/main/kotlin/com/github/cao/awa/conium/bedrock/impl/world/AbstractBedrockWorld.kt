package com.github.cao.awa.conium.bedrock.impl.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.impl.event.before.BedrockWorldBeforeEvents
import com.github.cao.awa.conium.bedrock.impl.entity.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.impl.event.after.BedrockWorldAfterEvents

@BedrockScriptApiFacade("World")
abstract class AbstractBedrockWorld {
    @ScriptReadonly
    @BedrockScriptApiFacade("World", "beforeEvents")
    val beforeEvents: BedrockWorldBeforeEvents get() = eventsBefore()

    @ScriptReadonly
    @BedrockScriptApiFacade("World", "afterEvents")
    val afterEvents: BedrockWorldAfterEvents get() = eventsAfter()

    abstract fun getPlayers(): BedrockPlayerDelegate

    private fun eventsBefore(): BedrockWorldBeforeEvents = BedrockWorldBeforeEvents.EVENTS

    private fun eventsAfter(): BedrockWorldAfterEvents = BedrockWorldAfterEvents.EVENTS
}
