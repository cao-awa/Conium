package com.github.cao.awa.conium.bedrock.event.before.item.use.on

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.bedrockEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent: BedrockEvent<BedrockItemUseOnEventContext>(ConiumEventType.ITEM_USE_ON_BLOCK) {
    override fun createUnnamed(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>, scriptSource: Any): ConiumArisingEventContext<*> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.SERVER_PLAYER
        ).arise { _: Any, usage: ItemUsageContext, source: ServerPlayerEntity ->
            !usage.bedrockEventContext(scriptSource, source).also { context ->
                action(context)
                BedrockEventContext.clearContext(scriptSource)
            }.cancel
        }
    }
}
