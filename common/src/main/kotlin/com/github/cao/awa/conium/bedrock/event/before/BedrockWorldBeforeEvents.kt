package com.github.cao.awa.conium.bedrock.event.before

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.before.item.use.BedrockItemUseBeforeEvent
import com.github.cao.awa.conium.bedrock.event.before.item.use.on.BedrockItemUseOnBeforeEvent

@BedrockScriptApi
@BedrockScriptApiFacade("WorldBeforeEvents")
class BedrockWorldBeforeEvents {
    companion object {
        val EVENTS: BedrockWorldBeforeEvents = BedrockWorldBeforeEvents()
    }

    @BedrockScriptApi
    @BedrockScriptApiFacade("WorldBeforeEvents", "itemUseOn")
    val itemUseOn: BedrockItemUseOnBeforeEvent = BedrockItemUseOnBeforeEvent()

    @BedrockScriptApi
    @BedrockScriptApiFacade("WorldBeforeEvents", "itemUse")
    val itemUse: BedrockItemUseBeforeEvent = BedrockItemUseBeforeEvent()
}
