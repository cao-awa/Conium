package com.github.cao.awa.conium.block.builder.bedrock

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.resources.Identifier

class BedrockSchemaBlockBuilder(identifier: Identifier) : ConiumBlockBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): BedrockSchemaBlockBuilder {
            return json["minecraft:block"]!!.asJsonObject.let { block ->
                val builder = block["description"]!!.asJsonObject.let { description ->
                    BedrockSchemaBlockBuilder(Identifier.parse(description["identifier"].asString)).also {
//                        it.templates.add()
                    }
                }

                builder.addTemplates(
                    ConiumTemplate.deserializeBlockTemplates(
                        block["components"]!!.asJsonObject
                    )
                )

                builder
            }
        }

        @JvmStatic
        fun earlyDeserialize(json: JsonObject): BedrockSchemaBlockBuilder {
            return json["minecraft:block"]!!.asJsonObject.let { block ->
                val builder = block["description"]!!.asJsonObject.let { description ->
                    BedrockSchemaBlockBuilder(Identifier.parse(description["identifier"].asString)).also {
//                        it.templates.add()
                    }
                }

                builder
            }
        }
    }
}
