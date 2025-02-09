package com.github.cao.awa.conium.bedrock.event.after.item.use

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
@BedrockScriptApiFacade("ItemUseOnAfterEventSignal")
class BedrockItemUseOnAfterEvent: BedrockEvent<BedrockItemUseOnEventContext>(ConiumEventType.ITEM_USED_ON_BLOCK) {
    override fun createUnnamed(action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>, scriptSource: Any): ConiumEventContext<*> {
        return ConiumEventContextBuilder.unnamed(
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.SERVER_PLAYER
        ) { _: Any, usage: ItemUsageContext, source: ServerPlayerEntity ->
            action(usage.toBedrock(scriptSource, source))

            BedrockEventContext.clearContext(scriptSource)
        }
    }
}
