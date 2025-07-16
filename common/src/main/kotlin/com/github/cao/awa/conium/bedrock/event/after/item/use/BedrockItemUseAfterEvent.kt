package com.github.cao.awa.conium.bedrock.event.after.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import com.github.cao.awa.conium.bedrock.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseEventContext
import com.github.cao.awa.conium.bedrock.item.stack.bedrockItemStack
import com.github.cao.awa.conium.bedrock.world.bedrockWorld
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseAfterEventSignal")
class BedrockItemUseAfterEvent : BedrockEvent<BedrockItemUseEventContext>(ConiumEventType.ITEM_USED) {
    override fun createUnnamed(
        action: ParameterSelective1<Unit, BedrockItemUseEventContext>,
        scriptSource: Any
    ): ConiumArisingEventContext<*> {
        return ConiumEventContextBuilder.unnamed(
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.SERVER_PLAYER,
            ConiumEventArgTypes.ITEM_STACK
        ) { _: Any, world: ServerWorld, source: ServerPlayerEntity, itemStack: ItemStack ->
            BedrockItemUseEventContext(
                scriptSource,
                world.server.bedrockWorld,
                itemStack.bedrockItemStack,
                source.bedrockPlayer
            ).also { context ->
                action(context)
                BedrockEventContext.clearContext(scriptSource)
            }
        }
    }
}
