package com.github.cao.awa.conium.bedrock.impl.event.before.item.use

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.impl.entity.player.bedrockPlayer
import com.github.cao.awa.conium.bedrock.impl.event.BedrockEvent
import com.github.cao.awa.conium.bedrock.impl.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.impl.event.before.item.use.context.BedrockItemUseEventContext
import com.github.cao.awa.conium.bedrock.impl.item.stack.bedrockItemStack
import com.github.cao.awa.conium.bedrock.impl.world.bedrockWorld
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseBeforeEventSignal")
class BedrockItemUseBeforeEvent: BedrockEvent<Item, BedrockItemUseEventContext, ConiumItemUseEventMetadata>(ConiumEventType.ITEM_USE) {
    override fun createUnnamed(
        action: ParameterSelective1<Unit, BedrockItemUseEventContext>, scriptSource: Any
    ): ConiumArisingEventContext<*, *> {
        return ConiumEventContextBuilder.unnamed(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK
        ) { _: Any, world: World, source: PlayerEntity, itemStack: ItemStack ->
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
