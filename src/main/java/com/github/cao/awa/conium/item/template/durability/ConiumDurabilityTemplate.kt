package com.github.cao.awa.conium.item.template.durability

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeTool
import com.github.cao.awa.conium.kotlin.extent.component.withCreateTool
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item

open class ConiumDurabilityTemplate(private val durability: Int, private val damageChance: IntRange, name: String) : ConiumItemTemplate(name = name) {
    companion object {
        val defaultChance = IntRange(100, 100)

        fun createChance(jsonObject: JsonObject, key: String = "damage_chance"): IntRange =
            // Create chance range.
            jsonObject[key]?.asJsonObject?.let { chance ->
                createChance(chance["min"].asInt, chance["max"].asInt)
            } ?: defaultChance

        private fun createChance(min: Int, max: Int): IntRange {
            // Do limits check, the range has only allowed 0 to 100 and max cannot smaller than min.
            check(max in 0..100) { "The minimum chance value only allowed in range 0 to 100" }

            check(max in 0..100) { "The maximum chance value only allowed in range 0 to 100" }

            check(min <= max) { "The minimum chance can not be more than the maximum chance" }

            return IntRange(min, max)
        }
    }

    override fun complete(target: ConiumItem) {
        // Should increments 'USED' stat when an item has durability.
        target.shouldPostHit = true

        // Set durability damage chance.
        target.durabilityDamageChance = this.damageChance
    }

    override fun settings(settings: Item.Settings) {
        // Set max durability.
        settings.maxDamage(this.durability)

        // Create default tool component, let it can be damage durability when breaking block.
        settings.components.withComponent(DataComponentTypes.TOOL, withCreateTool(), withComputeTool())
    }
}
