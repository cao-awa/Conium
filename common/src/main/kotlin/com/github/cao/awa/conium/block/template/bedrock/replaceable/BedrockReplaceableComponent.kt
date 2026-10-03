package com.github.cao.awa.conium.block.template.bedrock.replaceable

import com.github.cao.awa.conium.block.template.replaceable.ConiumBlockReplaceableTemplate
import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents.REPLACEABLE
import com.google.gson.JsonElement

object BedrockReplaceableComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumBlockReplaceableTemplate {
        val replaceable = if (element.isJsonPrimitive) {
            element.asBoolean
        } else if (element.isJsonObject) {
            element.asJsonObject.get("value")?.asBoolean ?: true
        } else {
            true
        }
        return ConiumBlockReplaceableTemplate(replaceable, REPLACEABLE)
    }
}
