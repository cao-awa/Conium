package com.github.cao.awa.conium.entity.template.bedrock.collision

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockEntity.COLLISION_BOX
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

object ConiumBedrockEntityCollisionBoxTemplate {
    @JvmStatic
    fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEntityDimensionTemplate = element.asJsonObject.let {
        ConiumEntityDimensionTemplate(
            it["width"].asFloat,
            it["height"].asFloat,
            COLLISION_BOX
        )
    }
}
