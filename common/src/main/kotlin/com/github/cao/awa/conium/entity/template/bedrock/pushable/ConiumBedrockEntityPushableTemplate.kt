package com.github.cao.awa.conium.entity.template.bedrock.pushable

import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockEntity.PUSHABLE
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

object ConiumBedrockEntityPushableTemplate {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityPushableTemplate = ConiumEntityPushableTemplate.create(element, PUSHABLE)
}
