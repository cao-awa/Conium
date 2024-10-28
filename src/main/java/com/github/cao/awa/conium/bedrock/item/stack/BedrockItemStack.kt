package com.github.cao.awa.conium.bedrock.item.stack

import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries

class BedrockItemStack(private val delegate: ItemStack) {
    val typeId get() = typeId()

    private fun typeId(): String = Registries.ITEM.getId(this.delegate.item).toString()
}

fun ItemStack.bedrockItem(): BedrockItemStack {
    return BedrockItemStack(
        this
    )
}
