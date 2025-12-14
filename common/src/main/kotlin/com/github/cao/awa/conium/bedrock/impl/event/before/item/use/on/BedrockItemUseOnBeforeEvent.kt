package com.github.cao.awa.conium.bedrock.impl.event.before.item.use.on

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.impl.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.impl.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.impl.event.before.item.use.on.context.BedrockItemUseOnEventMetadata
import com.github.cao.awa.conium.bedrock.impl.event.before.item.use.on.context.bedrockEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.block.on.use.metadata.ConiumItemUseOnBlockEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEventSignal")
class BedrockItemUseOnBeforeEvent: BedrockEvent<Item, BedrockItemUseOnEventMetadata, ConiumItemUseOnBlockEventMetadata>(ConiumEventType.ITEM_USE_ON_BLOCK) {
    override fun createUnnamed(
        action: ParameterSelective1<Unit, BedrockItemUseOnEventMetadata>,
        scriptSource: Any
    ): ConiumArisingEventContext<*, *> {
        return ConiumEventContextBuilder.unnamed(
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ) { _: Any, usage: ItemUsageContext ->
            !usage.bedrockEventContext(scriptSource, usage.player).also { context: BedrockItemUseOnEventMetadata ->
                BedrockEventContext.contexts[scriptSource] = context
            }.also { context: BedrockItemUseOnEventMetadata ->
                action(context)
                BedrockEventContext.clearContext(scriptSource)
            }.cancel
        }
    }
}
