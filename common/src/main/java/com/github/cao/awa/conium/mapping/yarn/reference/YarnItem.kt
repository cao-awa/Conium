@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*

/**
 * See the mapping [Item](https://mappings.dev/1.21.4/net/minecraft/world/item/Item.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Item.name: Text by Item::name
val Item.breakSound: SoundEvent by Item::breakSound
val Item.components: ComponentMap by Item::components
val Item.defaultStack: ItemStack by Item::defaultStack
val Item.maxCount: Int by Item::maxCount
val Item.recipeRemainder: ItemStack by Item::recipeRemainder
val Item.translationKey: String by Item::translationKey