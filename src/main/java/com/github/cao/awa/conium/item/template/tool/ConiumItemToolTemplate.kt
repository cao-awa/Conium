package com.github.cao.awa.conium.item.template.tool

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.TagKey

open class ConiumItemToolTemplate(name: String, private val material: ToolMaterial, private val effectiveBlocks: TagKey<Block>, private val attackDamage: Float, private val attackSpeed: Float) : ConiumItemTemplate(name) {
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
        this.material.applyToolSettings(settings, this.effectiveBlocks, this.attackDamage, this.attackSpeed)
    }
}
