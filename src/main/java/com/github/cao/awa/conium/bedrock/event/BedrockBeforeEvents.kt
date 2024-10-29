package com.github.cao.awa.conium.bedrock.event

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.item.use.BedrockItemUseOnBeforeEvent

@BedrockScriptApi
@BedrockScriptApiFacade("WorldBeforeEvents")
class BedrockBeforeEvents {
    companion object {
        val EVENTS = BedrockBeforeEvents()
    }

    val itemUseOn = BedrockItemUseOnBeforeEvent()
}
