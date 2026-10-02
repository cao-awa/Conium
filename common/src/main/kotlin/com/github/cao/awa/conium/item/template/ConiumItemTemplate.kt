package com.github.cao.awa.conium.item.template

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.template.ConiumTemplate
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.item.Rarity

abstract class ConiumItemTemplate(
    isClient: Boolean = false,
    name: String,
    conflicts: Map<Class<out ConiumTemplate<*, *>>, String> = HashMap()
) : ConiumTemplate<ConiumItem, ConiumItemSettings>(isClient, name, conflicts) {
    companion object {
        fun createRarity(name: String): Rarity {
            return when (name) {
                Rarity.EPIC.serializedName -> Rarity.EPIC
                Rarity.RARE.serializedName -> Rarity.RARE
                Rarity.UNCOMMON.serializedName -> Rarity.UNCOMMON
                Rarity.COMMON.serializedName -> Rarity.COMMON
                else -> throw IllegalArgumentException("Unknown rarity name: '$name'")
            }
        }

        fun createUseAction(name: String): ItemUseAnimation {
            return when (name) {
                ItemUseAnimation.NONE.serializedName -> ItemUseAnimation.NONE
                ItemUseAnimation.EAT.serializedName -> ItemUseAnimation.EAT
                ItemUseAnimation.DRINK.serializedName -> ItemUseAnimation.DRINK
                ItemUseAnimation.BLOCK.serializedName -> ItemUseAnimation.BLOCK
                ItemUseAnimation.BOW.serializedName -> ItemUseAnimation.BOW
                ItemUseAnimation.SPEAR.serializedName -> ItemUseAnimation.SPEAR
                ItemUseAnimation.CROSSBOW.serializedName -> ItemUseAnimation.CROSSBOW
                ItemUseAnimation.SPYGLASS.serializedName -> ItemUseAnimation.SPYGLASS
                ItemUseAnimation.TOOT_HORN.serializedName -> ItemUseAnimation.TOOT_HORN
                ItemUseAnimation.BRUSH.serializedName -> ItemUseAnimation.BRUSH
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
    open fun settings(settings: Item.Properties) {
        // Do nothing.
    }

    // Do not call settings directly.
    // Use 'prepare'.
    open fun settings(settings: ConiumItemSettings) {
        // Do nothing.
    }
}
