package com.github.cao.awa.conium.block.template.velocity.walk

import com.github.cao.awa.conium.block.template.velocity.ConiumBlockMovementVelocityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.WALK_VELOCITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

open class ConiumBlockWalkVelocityTemplate(walkVelocity: Float) : ConiumBlockMovementVelocityTemplate(walkVelocity, 1.0F, WALK_VELOCITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockWalkVelocityTemplate = ConiumBlockWalkVelocityTemplate(element.asFloat)
    }
}
