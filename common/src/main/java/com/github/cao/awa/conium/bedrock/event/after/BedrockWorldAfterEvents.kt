package com.github.cao.awa.conium.bedrock.event.after

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.after.item.use.on.BedrockItemUseOnAfterEvent

@BedrockScriptApi
@BedrockScriptApiFacade("WorldAfterEvents")
class BedrockWorldAfterEvents {
    companion object {
        val EVENTS: BedrockWorldAfterEvents = BedrockWorldAfterEvents()
    }

    @BedrockScriptApi
    @BedrockScriptApiFacade("WorldAfterEvents", "itemUseOn")
    val itemUseOn: BedrockItemUseOnAfterEvent = BedrockItemUseOnAfterEvent()
}
