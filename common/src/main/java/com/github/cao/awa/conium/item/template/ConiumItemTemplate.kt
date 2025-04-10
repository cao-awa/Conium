package com.github.cao.awa.conium.item.template

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.item.Item
import net.minecraft.item.consume.UseAction
import net.minecraft.util.Rarity

abstract class ConiumItemTemplate(
    isClient: Boolean = false,
    name: String,
    conflicts: Map<Class<out ConiumTemplate<*, *>>, String> = CollectionFactor.hashMap()
) : ConiumTemplate<ConiumItem, ConiumItemSettings>(isClient, name, conflicts) {
    companion object {
        fun createRarity(name: String): Rarity {
            return when (name) {
                Rarity.EPIC.asString() -> Rarity.EPIC
                Rarity.RARE.asString() -> Rarity.RARE
                Rarity.UNCOMMON.asString() -> Rarity.UNCOMMON
                Rarity.COMMON.asString() -> Rarity.COMMON
                else -> throw IllegalArgumentException("Unknown rarity name: '$name'")
            }
        }

        fun createUseAction(name: String): UseAction {
            return when (name) {
                UseAction.NONE.asString() -> UseAction.NONE
                UseAction.EAT.asString() -> UseAction.EAT
                UseAction.DRINK.asString() -> UseAction.DRINK
                UseAction.BLOCK.asString() -> UseAction.BLOCK
                UseAction.BOW.asString() -> UseAction.BOW
                UseAction.SPEAR.asString() -> UseAction.SPEAR
                UseAction.CROSSBOW.asString() -> UseAction.CROSSBOW
                UseAction.SPYGLASS.asString() -> UseAction.SPYGLASS
                UseAction.TOOT_HORN.asString() -> UseAction.TOOT_HORN
                UseAction.BRUSH.asString() -> UseAction.BRUSH
                else -> throw IllegalArgumentException("Unknown use animation name: '$name'")
            }
        }

        fun validateStackSize(value: Int): Int {
            if (value !in (1..64)) {
                throw IllegalArgumentException("Invalid stack size value: $value, stack size only allows 1 to 64")
            }
            return value
        }
    }

    override fun complete(target: ConiumItem) {
        // Do nothing.
    }

    override fun attach(target: ConiumItem) {
        // Do nothing.
    }

    override fun prepare(target: ConiumItemSettings) {
        settings(target.vanillaSettings)
        settings(target)
    }

    // Do not call settings directly.
    // Use 'prepare'.
    open fun settings(settings: Item.Settings) {
        // Do nothing.
    }

    // Do not call settings directly.
    // Use 'prepare'.
    open fun settings(settings: ConiumItemSettings) {
        // Do nothing.
    }
}
