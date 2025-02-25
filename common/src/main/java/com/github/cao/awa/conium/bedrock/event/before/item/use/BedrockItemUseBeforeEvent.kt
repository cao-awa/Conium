package com.github.cao.awa.conium.bedrock.event.before.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import com.github.cao.awa.conium.bedrock.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseEventContext
import com.github.cao.awa.conium.bedrock.item.stack.bedrockItemStack
import com.github.cao.awa.conium.bedrock.world.bedrockWorld
import com.github.cao.awa.conium.bedrock.world.dimension.bedrockDimension
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseBeforeEventSignal")
class BedrockItemUseBeforeEvent: BedrockEvent<BedrockItemUseEventContext>(ConiumEventType.ITEM_USE) {
    override fun createUnnamed(action: ParameterSelective1<Unit, BedrockItemUseEventContext>, scriptSource: Any): ConiumEventContext<*> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.SERVER_PLAYER,
            ConiumEventArgTypes.ITEM_STACK
        ).arise { _: Any, world: ServerWorld, source: ServerPlayerEntity, itemStack: ItemStack ->
            BedrockItemUseEventContext(
                scriptSource,
                world.server.bedrockWorld,
                itemStack.bedrockItemStack,
                source.bedrockPlayer
            ).also { context ->
                action(context)
                BedrockEventContext.clearContext(scriptSource)
            }.cancel
        }
    }
}
