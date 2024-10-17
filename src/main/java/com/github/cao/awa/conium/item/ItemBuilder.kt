package com.github.cao.awa.conium.item

import net.minecraft.item.Item
import net.minecraft.util.Identifier

@JvmRecord
data class ItemBuilder(val identifier: Identifier) {
    fun build(): Item {
        return Item(Item.Settings())
    }
}
