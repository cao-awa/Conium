package com.github.cao.awa.conium.bedrock.event.item.use

import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.bedrockContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1

class BedrockItemUseOnEvent {
    fun subscribe(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>) {
        val currentPosting = BedrockEventContext.currentPosting!!

        println("$currentPosting subscribing to item use on")

        ConiumEventContextBuilder.request(
            ConiumEventType.ITEM_USE_ON_BLOCK,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ).arise { _, usage ->
            action.arise(usage.bedrockContext(currentPosting))

            BedrockEventContext.clearContext(currentPosting)

            true
        }
    }
}
