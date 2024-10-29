package com.github.cao.awa.conium.bedrock.event.context.item.use

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.item.stack.toBedrock
import com.github.cao.awa.conium.bedrock.world.toBedrock
import net.minecraft.item.ItemUsageContext

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEvent", "ItemUseOnAfterEvent")
class BedrockItemUseOnEventContext(source: Any, val world: BedrockWorld, val itemStack: BedrockItemStack): BedrockEventContext(source) {
    override fun world(): BedrockWorld = this.world
}

fun ItemUsageContext.toBedrock(scriptSource: Any): BedrockItemUseOnEventContext{
    val context = BedrockItemUseOnEventContext(
        scriptSource,
        this.world.toBedrock(),
        this.stack.toBedrock()
    )
    BedrockEventContext.contexts[scriptSource] = context
    return context
}