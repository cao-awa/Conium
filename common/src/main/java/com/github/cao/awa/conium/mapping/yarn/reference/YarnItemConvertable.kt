@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible

/**
 * See the mapping [ItemConvertible](https://mappings.dev/1.21.4/net/minecraft/world/level/ItemLike.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

fun ItemConvertible.asItem(): Item = this.asItem()