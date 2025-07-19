package com.github.cao.awa.conium.bedrock.event.context.item.use

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.conium.bedrock.world.dimension.BedrockDimension
import net.minecraft.item.Item

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseBeforeEvent", "ItemUseAfterEvent")
class BedrockItemUseEventContext(
    scriptSource: Any,
    val world: BedrockWorld,
    val itemStack: BedrockItemStack,
    val source: BedrockPlayer
) : BedrockEventContext<Item>(scriptSource) {
    var cancel: Boolean = false

    override fun world(): BedrockWorld = this.world
}
