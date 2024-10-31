package com.github.cao.awa.conium.item.template.tool

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

open class ConiumItemToolTemplate(
    name: String,
    private val material: ToolMaterial?,
    private val effectiveBlocks: TagKey<Block> = BlockTags.AIR,
    private val attackDamage: Float = -1F,
    private val attackSpeed: Float = -1F,
    private val durability: Int = -1,
) : ConiumItemTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemToolTemplate {
            if (element is JsonObject) {
                return ConiumItemToolTemplate(ConiumTemplates.TOOL, null)
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
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
        this.material?.let { material ->
            settings.maxDamage(material.durability)
            material.applyToolSettings(settings, this.effectiveBlocks, this.attackDamage, this.attackSpeed)
        }

        if (this.durability > 0) {
            settings.maxDamage(this.durability)
        }
    }
}
