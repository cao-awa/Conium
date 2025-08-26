package com.github.cao.awa.conium.item.template.convert.block

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.event.use.block.ConiumItemUsedOnBlockEventMetadata
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.consume.UseAction
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

class ConiumUsedOnBlockConvertToTemplate(
    private val target: String,
    private val resultStack: () -> ItemStack
) : ConiumItemTemplate(name = ConiumTemplates.Item.USED_ON_BLOCK_CONVERT_TO) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumUsedOnBlockConvertToTemplate = element.ifJsonObject(
            {
                ConiumUsedOnBlockConvertToTemplate(
                    it["block"].asString
                ) {
                    createItemStack(it, "result")
                }
            }
        ) {
            throw notSupported(it)
        }!!
    }

    private val tagKey: TagKey<Block>? = if (this.target.startsWith("#")) {
        TagKey.of(
            RegistryKeys.BLOCK,
            Identifier.ofVanilla(this.target.substring(1))
        )
    } else null

    private val targetBlock: Block? = if (this.tagKey == null) {
        Registries.BLOCK.get(Identifier.of(this.target))
    } else null

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUsedOnBlock.listen(target) {
            val player: PlayerEntity? = this.player
            if (player != null) {
                val itemStack: ItemStack = this.stack
                val blockState: BlockState = this.blockState

                val isMatch: Boolean = if (tagKey != null) {
                    blockState.isIn(tagKey)
                } else if (targetBlock != null){
                    blockState.block == targetBlock
                } else false

                if (isMatch) {
                    itemStack.decrement(1)

                    player.inventory.insertStack(resultStack())
                }
            }
        }
    }
}
