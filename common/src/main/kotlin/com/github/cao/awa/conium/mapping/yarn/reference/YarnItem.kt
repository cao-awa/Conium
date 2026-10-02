@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.core.component.DataComponentMap
import net.minecraft.sounds.SoundEvent

/**
 * See the mapping [Item](https://mappings.dev/1.21.4/net/minecraft/world/item/Item.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Item.name: Component get() = this.name
val Item.breakSound: SoundEvent? get() = null
val Item.components: DataComponentMap get() = this.components()
val Item.defaultStack: ItemStack get() = this.defaultInstance
val Item.maxCount: Int get() = this.defaultInstance.maxStackSize
val Item.recipeRemainder: ItemStack get() = this.craftingRemainder?.create() ?: ItemStack.EMPTY
val Item.translationKey: String get() = this.descriptionId

val Item.identifier: Identifier
    get() = BuiltInRegistries.ITEM.getKey(this)
