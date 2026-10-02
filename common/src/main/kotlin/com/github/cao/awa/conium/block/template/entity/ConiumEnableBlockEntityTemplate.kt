package com.github.cao.awa.conium.block.template.entity

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.blockentity.ConiumBlockEntity
import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.kotlin.extent.json.asObject
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.ENABLE_BLOCK_ENTITY
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class ConiumEnableBlockEntityTemplate(
    val identifier: String,
    val registeredData: MutableMap<String, ConiumNbtDataSerializer<*>>,
    val defaultData: MutableMap<String, Any>
) : ConiumBlockTemplate(name = ENABLE_BLOCK_ENTITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumEnableBlockEntityTemplate {
            return asObject(element) {
                val registeredData: MutableMap<String, ConiumNbtDataSerializer<*>> = HashMap()
                val defaultData: MutableMap<String, Any> = HashMap()

                asObject(this["data"]) {
                    for ((key, value) in asMap()) {
                        when (value) {
                            is JsonObject -> {
                                val type: String = value["type"].asString
                                registeredData[key] = ConiumNbtDataSerializer.getSerializer(type)

                                when (type) {
                                    "int", "integer" -> defaultData[key] = value["default"].asInt
                                    "long" -> defaultData[key] = value["default"].asLong
                                    "short" -> defaultData[key] = value["default"].asShort
                                    "byte" -> defaultData[key] = value["default"].asByte
                                    "double" -> defaultData[key] = value["default"].asDouble
                                    "float" -> defaultData[key] = value["default"].asFloat
                                    "boolean", "bool" -> defaultData[key] = value["default"].asBoolean
                                    "string", "str" -> defaultData[key] = value["default"].asString
                                    else -> illegalArgument("Unsupported type: $type")
                                }
                            }

                            else -> illegalArgument("Unsupported value: $value")
                        }
                    }
                }

                ConiumEnableBlockEntityTemplate(
                    this["identifier"].asString,
                    registeredData,
                    defaultData
                )
            }
        }
    }

    override fun prepare(settings: com.github.cao.awa.conium.block.setting.ConiumBlockSettings) {
        settings.enableBlockEntity = true
        settings.blockEntity.let {
            it.registeredData = this.registeredData
            it.defaultData = this.defaultData
        }
    }

    override fun complete(target: ConiumBlock) {
        // Setting the block entity type.
        target.setting.blockEntity.let { blockEntitySettings ->
            val type = BlockEntityType(
                { pos: BlockPos, state: BlockState ->
                    ConiumBlockEntity(
                        blockEntitySettings,
                        pos,
                        state
                    )
                },
                setOf(target)
            )
            blockEntitySettings.type = type
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.parse(this.identifier), type)
        }
    }
}
