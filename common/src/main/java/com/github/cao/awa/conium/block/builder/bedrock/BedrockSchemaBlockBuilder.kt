package com.github.cao.awa.conium.block.builder.bedrock

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class BedrockSchemaBlockBuilder(identifier: Identifier) : ConiumBlockBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): BedrockSchemaBlockBuilder {
            return json["minecraft:block"]!!.asJsonObject.let { block ->
                val builder = block["description"]!!.asJsonObject.let { description ->
                    BedrockSchemaBlockBuilder(Identifier.of(description["identifier"].asString)).also {
//                        it.templates.add()
                    }
                }

                builder.addTemplates(
                    ConiumTemplate.deserializeBlockTemplates(
                        block["components"]!!.asJsonObject, registryLookup
                    )
                )

                builder
            }
        }
    }
}
