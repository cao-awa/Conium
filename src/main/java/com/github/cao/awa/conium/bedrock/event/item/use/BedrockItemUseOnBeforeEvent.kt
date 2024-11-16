package com.github.cao.awa.conium.bedrock.event.item.use

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.toBedrock
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent {
    @BedrockScriptApi
    fun subscribe(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>) {
        val currentPosting = BedrockEventContext.currentPosting!!

        ConiumEventContextBuilder.request(
            ConiumEventType.ITEM_USE_ON_BLOCK,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ).presage { _, usage ->
            action.arise(usage.toBedrock(currentPosting))

            BedrockEventContext.clearContext(currentPosting)

            true
        }
    }
}
