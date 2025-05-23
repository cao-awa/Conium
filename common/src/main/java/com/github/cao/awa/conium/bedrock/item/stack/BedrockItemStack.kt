package com.github.cao.awa.conium.bedrock.item.stack

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries

@BedrockScriptApi
@BedrockScriptApiFacade("ItemStack")
class BedrockItemStack(private val delegate: ItemStack) {
    val typeId: String get() = typeId()

    private fun typeId(): String = Registries.ITEM.getId(this.delegate.item).toString()
}

val ItemStack.bedrockItemStack: BedrockItemStack
    get() = BedrockItemStack(this)
