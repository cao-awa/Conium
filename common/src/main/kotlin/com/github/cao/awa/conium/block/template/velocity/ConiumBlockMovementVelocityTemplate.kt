package com.github.cao.awa.conium.block.template.velocity

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifFloat
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.MOVEMENT_VELOCITY
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour

open class ConiumBlockMovementVelocityTemplate(private val walkVelocity: Float, private val jumpVelocity: Float, name: String = MOVEMENT_VELOCITY) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockMovementVelocityTemplate = element.ifJsonObject({
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

    override fun settings(settings: BlockBehaviour.Properties) {
        // Set movement velocities.
        settings.speedFactor(this.walkVelocity)
        settings.jumpFactor(this.jumpVelocity)
    }
}
