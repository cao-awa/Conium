package com.github.cao.awa.conium.entity.builder.bedrock

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class BedrockSchemaEntityBuilder(identifier: Identifier) : ConiumEntityBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): BedrockSchemaEntityBuilder {
            return json["minecraft:entity"]!!.asJsonObject.let { entity ->
                val builder = entity["description"]!!.asJsonObject.let { description ->
                    BedrockSchemaEntityBuilder(Identifier.of(description["identifier"].asString)).also {
//                        it.templates.add()
                    }
                }

                builder.addTemplates(
                    Manipulate.cast(
                        ConiumTemplate.deserializeEntityTemplates(entity["components"]!!.asJsonObject, registryLookup)
                    )
                )

                entity["component_groups"]?.asJsonObject?.entrySet()?.forEach { (groupName, group) ->
                    group as JsonObject

                    builder.addTemplates(
                        groupName,
                        Manipulate.cast(
                            ConiumTemplate.deserializeEntityTemplates(group, registryLookup)
                        )
                    )
                }

                builder
            }
        }
    }
}
