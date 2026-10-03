package com.github.cao.awa.conium.block.template.friction

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifFloat
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.FRICTION
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour

open class ConiumBlockFrictionTemplate(private val friction: Float, name: String = FRICTION) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockFrictionTemplate = element.ifJsonObject({
            ConiumBlockFrictionTemplate(it["value"]?.asFloat ?: 0.6F)
        }) {
            it.ifFloat { f ->
                ConiumBlockFrictionTemplate(f)
            }
        } ?: ConiumBlockFrictionTemplate(0.6F)
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        settings.friction(this.friction)
    }
}
