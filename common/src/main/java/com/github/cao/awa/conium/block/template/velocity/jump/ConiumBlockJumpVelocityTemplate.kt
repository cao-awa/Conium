package com.github.cao.awa.conium.block.template.velocity.jump

import com.github.cao.awa.conium.block.template.velocity.ConiumBlockMovementVelocityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.JUMP_VELOCITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

open class ConiumBlockJumpVelocityTemplate(jumpVelocity: Float) : ConiumBlockMovementVelocityTemplate(1.0F, jumpVelocity, JUMP_VELOCITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockJumpVelocityTemplate = ConiumBlockJumpVelocityTemplate(element.asFloat)
    }
}
