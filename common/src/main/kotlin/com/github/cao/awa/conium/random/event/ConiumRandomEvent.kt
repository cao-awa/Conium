package com.github.cao.awa.conium.random.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.DynamicArgsBuilder.Companion.requires
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import net.minecraft.util.Unit as MinecraftUnit

class ConiumRandomEvent : ConiumEvent<MinecraftUnit, ConiumRandomEventMetadata, ParameterSelective0<Boolean>>(ConiumEventType.RANDOM) {
    override fun requirement(): ConiumArisingEventContext<MinecraftUnit, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.UNIT
        ) { identity: MinecraftUnit ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }.doCast()
        // TODO
    }

    override fun metadata(context: ConiumEventContext<MinecraftUnit>): ConiumRandomEventMetadata {
        return ConiumRandomEventMetadata(context)
    }
}
