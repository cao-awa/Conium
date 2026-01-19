package com.github.cao.awa.conium.item.template.display.name

import com.alibaba.fastjson2.JSON
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeTool
import com.github.cao.awa.conium.kotlin.extent.component.withCreateTool
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.mapping.yarn.Text
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item

open class ConiumDisplayNameTemplate(
    private val displayName: String,
    name: String = ConiumItemTemplates.DISPLAY_NAME
) : ConiumItemTemplate(name = name) {
    companion object {
        fun create(json: JsonElement): ConiumDisplayNameTemplate = ConiumDisplayNameTemplate(json.asString)
    }
    override fun settings(settings: ConiumItemSettings) {
        settings.displayName = Text.translatable(this.displayName)
    }
}
