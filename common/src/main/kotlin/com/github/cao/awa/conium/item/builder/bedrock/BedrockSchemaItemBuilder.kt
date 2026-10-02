package com.github.cao.awa.conium.item.builder.bedrock

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.resources.Identifier

class BedrockSchemaItemBuilder(identifier: Identifier) : ConiumItemBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): BedrockSchemaItemBuilder {
            return json["minecraft:item"].asJsonObject.let {
                val builder = BedrockSchemaItemBuilder(Identifier.parse(it["description"]!!.asJsonObject!!["identifier"].asString!!))

                it["components"]?.asJsonObject!!.let { components ->
                    builder.addTemplates(
                        ConiumTemplate.deserializeItemTemplates(
                            components
                        )
                    )
                }

                return@let builder
            }
        }
    }
}
