package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.Hand
import net.minecraft.world.World

class ConiumItemUseEvent : ConiumItemEvent<ParameterSelective4<Boolean, World, PlayerEntity, Hand, ItemStack>, ConiumItemUseEventMetadata>(
    ConiumEventType.ITEM_USE
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.HAND,
            ConiumEventArgTypes.ITEM_STACK
        ).arise { identity: Any, world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, user, hand, itemStack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemUseEventMetadata {
        return ConiumItemUseEventMetadata(context)
    }
}
