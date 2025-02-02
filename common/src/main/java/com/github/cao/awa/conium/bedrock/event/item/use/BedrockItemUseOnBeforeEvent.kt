package com.github.cao.awa.conium.bedrock.event.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.toBedrock
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemUsageContext

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent {
    @BedrockScriptApi
    fun subscribe(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>) {
        val currentPosting: Any = BedrockEventContext.currentPosting!!

        ConiumEventContextBuilder.preRequestNr(
            ConiumEventType.ITEM_USE_ON_BLOCK,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ) { _: Any, usage: ItemUsageContext ->
            action(usage.toBedrock(currentPosting))

            BedrockEventContext.clearContext(currentPosting)
        }
    }
}
