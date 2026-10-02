package com.github.cao.awa.conium.item.template.consume

import com.github.cao.awa.conium.exception.notSupported
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.kotlin.extent.json.ifString
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.resources.Identifier

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
                    var alwaysConsumeOnUsedOnEntity = false
                    val targetEntity: EntityType<*>? = consume["used_on_entity"].ifString({ targetEntity ->
                        BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(targetEntity)).also {
                            alwaysConsumeOnUsedOnEntity = true
                        }
                    }) { entityConsume ->
                        entityConsume.ifBoolean { alwaysConsume: Boolean ->
                            alwaysConsumeOnUsedOnEntity = alwaysConsume
                        }
                        null
                    }

                    val targetBlockName: String? = runCatching {
                        consume["used_on_block"].asString.also {
                            alwaysConsumeOnUsedOnBlock = true
                        }
                    }.getOrElse { ex: Throwable ->
                        consume["used_on_block"]?.ifBoolean { alwaysConsume: Boolean ->
                            alwaysConsumeOnUsedOnBlock = alwaysConsume
                        }
                        null
                    }

                    val tagKey: TagKey<Block>?
                    val targetBlock: Block?

                    if (alwaysConsumeOnUsedOnBlock && targetBlockName != null) {
                        tagKey = if (targetBlockName.startsWith("#")) {
                            TagKey.create(
                                Registries.BLOCK,
                                Identifier.parse(targetBlockName.substring(1))
                            )
                        } else null

                        targetBlock = if (tagKey == null) {
                            BuiltInRegistries.BLOCK.getValue(Identifier.parse(targetBlockName))
                        } else null
                    } else {
                        tagKey = null
                        targetBlock = null
                    }

                    ConiumConsumeOnUsedTemplate(
                        consume["used"]?.asBoolean ?: false,
                        { blockState: BlockState ->
                            if (alwaysConsumeOnUsedOnBlock) {
                                true
                            }
                            if (targetBlockName != null) {
                                if (tagKey != null) {
                                    blockState.`is`(tagKey)
                                } else {
                                    blockState.block == targetBlock
                                }
                            } else {
                                false
                            }
                        },
                        { entity: LivingEntity ->
                            alwaysConsumeOnUsedOnEntity || (targetEntity != null && entity.type == targetEntity)
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
