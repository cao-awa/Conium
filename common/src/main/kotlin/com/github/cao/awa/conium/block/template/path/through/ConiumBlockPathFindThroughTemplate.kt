package com.github.cao.awa.conium.block.template.path.through

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.PATH_FIND_THROUGH
import com.google.gson.JsonElement

class ConiumBlockPathFindThroughTemplate(
    private val landPathThrough: Boolean,
    private val waterPathThrough: Boolean,
    private val airPathThrough: Boolean
) : ConiumBlockTemplate(name = PATH_FIND_THROUGH) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockPathFindThroughTemplate {
            return element.ifJsonObject({
                ConiumBlockPathFindThroughTemplate(
                    it["land"]?.asBoolean ?: false,
                    it["water"]?.asBoolean ?: false,
                    it["air"]?.asBoolean ?: false,
                )
            }) {
                it.ifBoolean { pathThrough ->
                    ConiumBlockPathFindThroughTemplate(
                        pathThrough,
                        pathThrough,
                        pathThrough
                    )
                }
            }!!
        }
    }

    override fun settings(settings: ConiumBlockSettings) {
        settings.landPathThrough = this.landPathThrough
        settings.waterPathThrough = this.waterPathThrough
        settings.airPathThrough = this.airPathThrough
    }
}
