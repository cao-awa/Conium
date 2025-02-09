package com.github.cao.awa.conium.bedrock.event.before.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.toBedrock
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent: BedrockEvent<BedrockItemUseOnEventContext>(ConiumEventType.ITEM_USE_ON_BLOCK) {
    override fun createUnnamed(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>, scriptSource: Any): ConiumEventContext<*> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.SERVER_PLAYER
        ).arise { _: Any, usage: ItemUsageContext, source: ServerPlayerEntity ->
            !usage.toBedrock(scriptSource, source).also { context ->
                action(context)
                BedrockEventContext.clearContext(scriptSource)
            }.cancel
        }
    }
}
