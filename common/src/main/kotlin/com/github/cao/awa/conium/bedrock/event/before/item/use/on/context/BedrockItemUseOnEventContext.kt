package com.github.cao.awa.conium.bedrock.event.before.item.use.on.context

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack
import com.github.cao.awa.conium.bedrock.item.stack.bedrockItemStack
import com.github.cao.awa.conium.bedrock.world.BedrockWorld
import com.github.cao.awa.conium.bedrock.world.dimension.BedrockDimension
import com.github.cao.awa.conium.bedrock.world.dimension.bedrockDimension
import com.github.cao.awa.conium.bedrock.world.bedrockWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext

@BedrockScriptApi
@BedrockScriptApiFacade("ItemUseOnBeforeEvent", "ItemUseOnAfterEvent")
class BedrockItemUseOnEventMetadata(
    scriptSource: Any,
    val dimension: BedrockDimension,
    val world: BedrockWorld,
    val itemStack: BedrockItemStack,
    val source: BedrockPlayer?
) : BedrockEventContext<Item, BedrockItemUseOnEventMetadata>(scriptSource) {
    var cancel: Boolean = false

    override fun world(): BedrockWorld = this.world
}

fun ItemUsageContext.bedrockEventContext(scriptSource: Any, source: PlayerEntity?): BedrockItemUseOnEventMetadata = BedrockItemUseOnEventMetadata(
    scriptSource,
    this.world.bedrockDimension,
    this.world.server!!.bedrockWorld,
    this.stack.bedrockItemStack,
    source?.bedrockPlayer
)