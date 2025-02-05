package com.github.cao.awa.conium.bedrock.event.before.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.before.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.before.context.item.use.toBedrock
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent {
    @BedrockScriptApi
    @BedrockScriptApiFacade("ItemUseOnBeforeEventSignal", "subscribe")
    fun subscribe(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>) {
        val currentPosting: Any = BedrockEventContext.currentPosting!!

        ConiumEventContextBuilder.preRequestNr(
            ConiumEventType.ITEM_USE_ON_BLOCK,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.SERVER_PLAYER
        ) { _: Any, usage: ItemUsageContext, source: ServerPlayerEntity ->
            action(usage.toBedrock(currentPosting, source))

            BedrockEventContext.clearContext(currentPosting)
        }
    }
}
