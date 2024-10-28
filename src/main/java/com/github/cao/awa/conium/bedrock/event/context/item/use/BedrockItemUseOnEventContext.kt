package com.github.cao.awa.conium.bedrock.event.context.item.use

import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.item.stack.bedrockItem
import com.github.cao.awa.conium.bedrock.world.bedrockWorld
import net.minecraft.item.ItemUsageContext

class BedrockItemUseOnEventContext(source: Any, val world: BedrockWorld, val itemStack: BedrockItemStack): BedrockEventContext(source) {
    override fun world(): BedrockWorld = this.world
}

fun ItemUsageContext.bedrockContext(scriptSource: Any): BedrockItemUseOnEventContext{
    val context = BedrockItemUseOnEventContext(
        scriptSource,
        this.world.bedrockWorld(),
        this.stack.bedrockItem()
    )
    BedrockEventContext.contexts[scriptSource] = context
    return context
}