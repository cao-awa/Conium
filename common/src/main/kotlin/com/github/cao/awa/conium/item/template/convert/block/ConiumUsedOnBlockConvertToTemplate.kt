package com.github.cao.awa.conium.item.template.convert.block

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.exception.notSupported
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.resources.Identifier

class ConiumUsedOnBlockConvertToTemplate(
    private val target: String,
    private val resultStack: () -> ItemStack
) : ConiumItemTemplate(name = ConiumItemTemplates.USED_ON_BLOCK_CONVERT_TO) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumUsedOnBlockConvertToTemplate = element.ifJsonObject(
            {
                ConiumUsedOnBlockConvertToTemplate(
                    it["block"].asString
                ) {
                    createItemStack(it, "result")
                }
            },
            notSupported()
        )!!
    }

    private val tagKey: TagKey<Block>? = if (this.target.startsWith("#")) {
        TagKey.create(
            Registries.BLOCK,
            Identifier.withDefaultNamespace(this.target.substring(1))
        )
    } else null

    private val targetBlock: Block? = if (this.tagKey == null) {
        BuiltInRegistries.BLOCK.getValue(Identifier.parse(this.target))
    } else null

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUsedOnBlock.listen(target) {
            val player: Player? = this.player
            if (player != null) {
                val itemStack: ItemStack = this.stack
                val blockState: BlockState = this.blockState

                val isMatch: Boolean = if (tagKey != null) {
                    blockState.`is`(tagKey)
                } else if (targetBlock != null){
                    blockState.block == targetBlock
                } else false

                if (isMatch) {
                    itemStack.shrink(1)

                    player.inventory.add(resultStack())
                }
            }
        }
    }
}
