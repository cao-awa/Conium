package com.github.cao.awa.conium.block.template.explosion.resistance

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.EXPLOSION_RESISTANCE
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup

open class ConiumExplosionResistanceTemplate(private val explosionResistance: Float, name: String = EXPLOSION_RESISTANCE) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumExplosionResistanceTemplate = ConiumExplosionResistanceTemplate(element.asFloat)
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set explosion resistance.
        settings.resistance(this.explosionResistance)
    }
}
