package com.github.cao.awa.conium.entity.template.dimension

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.DIMENSION
import com.google.gson.JsonElement
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumEntityDimensionTemplate(
    private val width: Float,
    private val height: Float,
    name: String = DIMENSION
) : ConiumEntityTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEntityDimensionTemplate = element.asJsonObject.let {
            ConiumEntityDimensionTemplate(
                it["width"].asFloat,
                it["height"].asFloat
            )
        }
    }

    override fun type(type: EntityType.Builder<ConiumEntity>) {
        // Set entity dimension.
        type.dimensions(this.width, this.height)
    }
}
