package com.github.cao.awa.conium.bedrock.impl.item.stack

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import net.minecraft.world.item.ItemStack
import net.minecraft.core.registries.BuiltInRegistries

@BedrockScriptApi
@BedrockScriptApiFacade("ItemStack")
class BedrockItemStack(private val delegate: ItemStack) {
    val typeId: String get() = typeId()

    private fun typeId(): String = BuiltInRegistries.ITEM.getId(this.delegate.item).toString()
}

val ItemStack.bedrockItemStack: BedrockItemStack
    get() = BedrockItemStack(this)
