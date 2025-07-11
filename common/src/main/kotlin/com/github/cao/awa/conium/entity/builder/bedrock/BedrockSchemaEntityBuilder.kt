package com.github.cao.awa.conium.entity.builder.bedrock

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class BedrockSchemaEntityBuilder(identifier: Identifier) : ConiumEntityBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): BedrockSchemaEntityBuilder {
            return json["minecraft:entity"]!!.asJsonObject.let { entity ->
                val builder = entity["description"]!!.asJsonObject.let { description ->
                    BedrockSchemaEntityBuilder(Identifier.of(description["identifier"].asString)).also {
                        // TODO
//                        it.templates.add()
                    }
                }

                builder.addTemplates(
                    ConiumTemplate.deserializeEntityTemplates(
                        entity["components"]!!.asJsonObject
                    )
                )

                entity["component_groups"]?.asJsonObject?.entrySet()?.forEach { (groupName, group) ->
                    group as JsonObject

                    builder.addTemplates(
                        groupName,
                        ConiumTemplate.deserializeEntityTemplates(
                            group
                        )
                    )
                }

                builder
            }
        }
    }
}
