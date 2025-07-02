package com.github.cao.awa.conium.bedrock.event.before.item.use

import com.github.cao.awa.conium.Conium
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
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseBeforeEventSignal")
class BedrockItemUseBeforeEvent: BedrockEvent<BedrockItemUseEventContext>(ConiumEventType.ITEM_USE) {
    override fun createUnnamed(action: ParameterSelective1<Unit, BedrockItemUseEventContext>, scriptSource: Any): ConiumArisingEventContext<*> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK
        ).arise { _: Any, world: World, source: PlayerEntity, itemStack: ItemStack ->
            val server: MinecraftServer? = world.server

            if (server == null) {
                Conium.debug { "World server are not prepared now" }

                false
            } else {
                BedrockItemUseEventContext(
                    scriptSource,
                    server.bedrockWorld,
                    itemStack.bedrockItemStack,
                    source.bedrockPlayer
                ).also { context: BedrockItemUseEventContext ->
                    BedrockEventContext.contexts[scriptSource] = context
                }.also { context: BedrockItemUseEventContext ->
                    action(context)
                    BedrockEventContext.clearContext(scriptSource)
                }.cancel
            }
        }
    }
}
