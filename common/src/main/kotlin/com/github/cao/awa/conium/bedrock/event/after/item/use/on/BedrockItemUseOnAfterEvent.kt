package com.github.cao.awa.conium.bedrock.event.after.item.use.on

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
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnAfterEventSignal")
class BedrockItemUseOnAfterEvent: BedrockEvent<Item, BedrockItemUseOnEventContext>(ConiumEventType.ITEM_USED_ON_BLOCK) {
    override fun createUnnamed(
        action: ParameterSelective1<Unit, BedrockItemUseOnEventContext>,
        scriptSource: Any
    ): ConiumArisingEventContext<*, *> {
        return ConiumEventContextBuilder.unnamed(
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.SERVER_PLAYER
        ) { _: Any, usage: ItemUsageContext, source: ServerPlayerEntity ->
            usage.bedrockEventContext(scriptSource, source).also { context: BedrockItemUseOnEventContext ->
                BedrockEventContext.contexts[scriptSource] = context
            }.also{ context: BedrockItemUseOnEventContext ->
                action(context)

                BedrockEventContext.clearContext(scriptSource)
            }
        }
    }
}
