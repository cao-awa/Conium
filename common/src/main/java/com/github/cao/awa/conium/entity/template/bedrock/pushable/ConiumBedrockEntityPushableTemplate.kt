package com.github.cao.awa.conium.entity.template.bedrock.pushable

import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockEntity.PUSHABLE
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

object ConiumBedrockEntityPushableTemplate {
    @JvmStatic
    fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumEntityPushableTemplate = ConiumEntityPushableTemplate.create(element, registryLookup, PUSHABLE)
}
