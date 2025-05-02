package com.github.cao.awa.conium.entity.template.bedrock.collision

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.notSupported
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockEntity.COLLISION_BOX
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper

object ConiumBedrockEntityCollisionBoxTemplate {
    @JvmStatic
    fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumEntityDimensionTemplate = element.ifJsonObject(
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
