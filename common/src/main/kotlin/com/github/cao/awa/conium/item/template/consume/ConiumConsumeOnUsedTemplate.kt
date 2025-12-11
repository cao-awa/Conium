package com.github.cao.awa.conium.item.template.consume

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

class ConiumConsumeOnUsedTemplate(
    private val consumeOnUsed: Boolean,
    private val consumeOnUsedOnBlock: (BlockState) -> Boolean,
    private val consumeOnUsedOnEntity: (LivingEntity) -> Boolean
) : ConiumItemTemplate(name = ConiumItemTemplates.CONSUME_ON_USED) {
    companion object {
        @JvmStatic
        fun create(
            element: JsonElement
        ): ConiumConsumeOnUsedTemplate = element.ifBoolean(
            { consume: Boolean ->
                ConiumConsumeOnUsedTemplate(
                    consume, { consume }, { consume }
                )
            }
        ) {
            it.ifJsonObject(
                { consume ->
                    var alwaysConsumeOnUsedOnBlock = false
                    val alwaysConsumeOnUsedOnEntity: Boolean = consume["used_on_entity"]?.asBoolean ?: false
                    val targetBlockName: String? = runCatching {
                        consume["used_on_block"].asString
                    }.getOrElse { ex: Throwable ->
                        consume["used_on_block"]?.ifBoolean { alwaysConsume: Boolean ->
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
                                Identifier.of(targetBlockName.substring(1))
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
                        consume["used"]?.asBoolean ?: false,
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
                },
                notSupported()
            )
        }!!
    }

    override fun complete(target: ConiumItem) {
        target.consumeOnUsed = this.consumeOnUsed
        target.consumeOnUsedOnBlock = this.consumeOnUsedOnBlock
        target.consumeOnUsedOnEntity = this.consumeOnUsedOnEntity
    }
}
