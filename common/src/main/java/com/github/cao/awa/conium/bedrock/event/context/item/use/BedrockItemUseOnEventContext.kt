package com.github.cao.awa.conium.bedrock.event.context.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.toBedrock
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.item.stack.toBedrock
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.conium.bedrock.world.toBedrock
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEvent", "ItemUseOnAfterEvent")
class BedrockItemUseOnEventContext(
    scriptSource: Any,
    val world: BedrockWorld,
    val itemStack: BedrockItemStack,
    val source: BedrockPlayer
) : BedrockEventContext(scriptSource) {
    override fun world(): BedrockWorld = this.world
}

fun ItemUsageContext.toBedrock(scriptSource: Any, source: ServerPlayerEntity): BedrockItemUseOnEventContext {
    val context = BedrockItemUseOnEventContext(
        scriptSource,
        this.world.toBedrock(),
        this.stack.toBedrock(),
        source.toBedrock()
    )
    BedrockEventContext.contexts[scriptSource] = context
    return context
}