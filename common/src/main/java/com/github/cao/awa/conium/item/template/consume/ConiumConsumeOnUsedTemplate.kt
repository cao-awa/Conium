package com.github.cao.awa.conium.item.template.consume

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

class ConiumConsumeOnUsedTemplate(
    private val consumeOnUsed: Boolean,
    private val consumeOnUsedOnBlock: (BlockState) -> Boolean,
    private val consumeOnUsedOnEntity: (LivingEntity) -> Boolean
) : ConiumItemTemplate(name = ConiumTemplates.Item.CONSUME_ON_USED) {
    companion object {
        @JvmStatic
        fun create(
            element: JsonElement,
            registryLookup: RegistryWrapper.WrapperLookup
        ): ConiumConsumeOnUsedTemplate = element.ifBoolean(
            { consume: Boolean ->
                ConiumConsumeOnUsedTemplate(
                    consume, { consume }, { consume }
                )
            }
        ) {
            if (it is JsonObject) {
                var alwaysConsumeOnUsedOnBlock: Boolean = false
                var alwaysConsumeOnUsedOnEntity: Boolean = it["used_on_entity"]?.asBoolean ?: false
                val targetBlockName: String? = runCatching {
                    it["used_on_block"].asString
                }.getOrElse { ex: Throwable ->
                    it["used_on_block"]?.ifBoolean { alwaysConsume: Boolean ->
                        alwaysConsumeOnUsedOnBlock = alwaysConsume
                    }
                    null
                }

                val tagKey: TagKey<Block>?
                val targetBlock: Block?

                if (!alwaysConsumeOnUsedOnBlock && targetBlockName != null) {
                    tagKey = if (targetBlockName.startsWith("#")) {
                        TagKey.of(
                            RegistryKeys.BLOCK,
                            Identifier.ofVanilla(targetBlockName.substring(1))
                        )
                    } else null

                    targetBlock = if (tagKey == null) {
                        Registries.BLOCK.get(Identifier.of(targetBlockName))
                    } else null
                } else {
                    tagKey = null
                    targetBlock = null
                }

                ConiumConsumeOnUsedTemplate(
                    it["used"]?.asBoolean ?: false,
                    { blockState: BlockState ->
                        if (targetBlockName != null) {
                            val isMatch: Boolean = if (tagKey != null) {
                                blockState.isIn(tagKey)
                            } else {
                                blockState.block == targetBlock
                            }

                            isMatch
                        } else {
                            alwaysConsumeOnUsedOnBlock
                        }
                    },
                    { entity: LivingEntity ->
                        alwaysConsumeOnUsedOnEntity
                    }
                )
            } else {
                throw notSupported(it)
            }
        }!!
    }

    override fun complete(target: ConiumItem) {
        target.consumeOnUsed = this.consumeOnUsed
        target.consumeOnUsedOnBlock = this.consumeOnUsedOnBlock
        target.consumeOnUsedOnEntity = this.consumeOnUsedOnEntity
    }
}
