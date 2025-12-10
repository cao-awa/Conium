package com.github.cao.awa.conium.block.template.map

import com.github.cao.awa.conium.kotlin.extent.block.parseAndFindColor
import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents.MAP_COLOR
import com.google.gson.JsonElement

object ConiumBedrockMapColorTemplate {
    @JvmStatic
    fun create(element: JsonElement): ConiumMapColorTemplate = ConiumMapColorTemplate(parseAndFindColor(element.asString), MAP_COLOR)
}
