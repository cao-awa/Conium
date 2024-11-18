package com.github.cao.awa.conium.block.template.velocity

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifFloat
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Block.MOVEMENT_VELOCITY
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup

open class ConiumBlockMovementVelocityTemplate(private val walkVelocity: Float, private val jumpVelocity: Float, name: String = MOVEMENT_VELOCITY) : ConiumBlockTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockMovementVelocityTemplate = element.ifJsonObject({
            ConiumBlockMovementVelocityTemplate(
                it["walk"]?.asFloat ?: 1.0F,
                it["jump"]?.asFloat ?: 1.0F
            )
        }) {
            it.ifFloat { f ->
                ConiumBlockMovementVelocityTemplate(f, f)
            }
        }!!
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set movement velocities.
        settings.velocityMultiplier(this.walkVelocity)
        settings.jumpVelocityMultiplier(this.jumpVelocity)
    }
}
