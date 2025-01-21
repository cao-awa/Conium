package com.github.cao.awa.conium.bedrock.event

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.item.use.BedrockItemUseOnBeforeEvent

@BedrockScriptApi
@BedrockScriptApiFacade("WorldBeforeEvents")
class BedrockBeforeEvents {
    companion object {
        val EVENTS: BedrockBeforeEvents = BedrockBeforeEvents()
    }

    @BedrockScriptApi
    val itemUseOn: BedrockItemUseOnBeforeEvent = BedrockItemUseOnBeforeEvent()
}
