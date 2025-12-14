package com.github.cao.awa.conium.bedrock.impl.event.before.item.use.context

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.impl.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.impl.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.impl.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.impl.world.BedrockWorld
import net.minecraft.item.Item

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseBeforeEvent", "ItemUseAfterEvent")
class BedrockItemUseEventContext(
    scriptSource: Any,
    val world: BedrockWorld,
    val itemStack: BedrockItemStack,
    val source: BedrockPlayer
) : BedrockEventContext<Item, BedrockItemUseEventContext>(scriptSource) {
    var cancel: Boolean = false

    override fun world(): BedrockWorld = this.world
}