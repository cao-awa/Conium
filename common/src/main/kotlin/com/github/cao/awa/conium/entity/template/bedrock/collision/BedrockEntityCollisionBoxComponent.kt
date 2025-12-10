package com.github.cao.awa.conium.entity.template.bedrock.collision

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.notSupported
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents.COLLISION_BOX
import com.google.gson.JsonElement
import com.google.gson.JsonObject

object BedrockEntityCollisionBoxComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityDimensionTemplate = element.ifJsonObject(
        { json: JsonObject ->
            ConiumEntityDimensionTemplate(
                json["width"].asFloat,
                json["height"].asFloat,
                COLLISION_BOX
            )
        },
        notSupported()
    )!!
}
