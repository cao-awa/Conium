package com.github.cao.awa.conium.block.template.bedrock.friction

import com.github.cao.awa.conium.block.template.friction.ConiumBlockFrictionTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifFloat
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents.FRICTION
import com.google.gson.JsonElement

object BedrockFrictionComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumBlockFrictionTemplate = element.ifJsonObject({
        ConiumBlockFrictionTemplate(it["value"]?.asFloat ?: 0.6F, FRICTION)
    }) {
        it.ifFloat { f ->
            ConiumBlockFrictionTemplate(f, FRICTION)
        }
    } ?: ConiumBlockFrictionTemplate(0.6F, FRICTION)
}
