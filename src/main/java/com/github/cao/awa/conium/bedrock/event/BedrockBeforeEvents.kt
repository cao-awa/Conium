package com.github.cao.awa.conium.bedrock.event

import com.github.cao.awa.conium.bedrock.event.item.use.BedrockItemUseOnEvent

class BedrockBeforeEvents {
    companion object {
        val EVENTS = BedrockBeforeEvents()
    }

    val itemUseOn = BedrockItemUseOnEvent()
}
