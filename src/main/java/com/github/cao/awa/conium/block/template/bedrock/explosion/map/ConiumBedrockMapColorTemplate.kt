package com.github.cao.awa.conium.block.template.bedrock.explosion.map

import com.github.cao.awa.conium.block.template.map.ConiumMapColorTemplate
import com.github.cao.awa.conium.kotlin.extent.block.parseAndFindColor
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.MAP_COLOR
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

object ConiumBedrockMapColorTemplate {
    @JvmStatic
    fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumMapColorTemplate = ConiumMapColorTemplate(parseAndFindColor(element.asString), MAP_COLOR)
}
