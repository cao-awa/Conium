@file:Import("")

package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import org.jetbrains.kotlin.script.util.Import

class ConiumItemUseOnBlockEvent : ConiumItemEvent<ParameterSelective2<ServerWorld, ItemUsageContext>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgType.SERVER_WORLD,
            ConiumEventArgType.ITEM_USAGE_CONTEXT
        ).attach(
            forever(ConiumEventType.ITEM_USE_ON_BLOCK)
        ).arise { identity, world, context ->
            noFailure(identity) {
                it.trigger(
                    world,
                    context
                )
            }
        }
    }
}
