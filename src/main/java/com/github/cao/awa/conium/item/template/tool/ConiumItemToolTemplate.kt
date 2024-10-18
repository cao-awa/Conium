package com.github.cao.awa.conium.item.template.tool

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import java.io.File
import javax.script.ScriptEngineManager

open class ConiumItemToolTemplate(name: String, val material: ToolMaterial, val effectiveBlocks: TagKey<Block>) : ConiumItemTemplate(name) {
    companion object {
//        @JvmStatic
//        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemPickaxeTemplate {
//            if (element is JsonObject) {
//                return ConiumItemPickaxeTemplate()
//            }
//            throw IllegalArgumentException("Not supported syntax: $element")
//        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {
        if (item.material != null) {
            throw IllegalStateException("The item already be ${item.material}, cannot set it to ${this.material}")
        }
        item.material = this.material
    }

    override fun settings(settings: Item.Settings) {
        settings.maxDamage(this.material.durability)
        settings.component(DataComponentTypes.TOOL, this.material.createComponent(this.effectiveBlocks))
    }
}
