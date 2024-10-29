package com.github.cao.awa.conium.bedrock.item.stack

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries

@BedrockScriptApi
@BedrockScriptApiFacade("ItemStack")
class BedrockItemStack(private val delegate: ItemStack) {
    val typeId get() = typeId()

    private fun typeId(): String = Registries.ITEM.getId(this.delegate.item).toString()
}

fun ItemStack.toBedrock(): BedrockItemStack {
    return BedrockItemStack(
        this
    )
}
