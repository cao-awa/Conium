package com.github.cao.awa.conium.entity.template.dimension

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extend.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.DIMENSION
import com.google.gson.JsonElement
import net.minecraft.entity.EntityDimensions

open class ConiumEntityDimensionTemplate(
    private val width: Float,
    private val height: Float,
    name: String = DIMENSION
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumEntityDimensionTemplate = element.ifJsonObject(
            {
                ConiumEntityDimensionTemplate(
                    it["width"].asFloat,
                    it["height"].asFloat
                )
            },
            notSupported()
        )!!

        @JvmStatic
        fun dimensions(width: Float, height: Float): EntityDimensions = EntityDimensions.changing(width, height)
    }

    override fun settings(settings: ConiumEntitySettings) {
        // Set entity dimension.
        settings.dimensions = dimensions(this.width, this.height)
    }
}
