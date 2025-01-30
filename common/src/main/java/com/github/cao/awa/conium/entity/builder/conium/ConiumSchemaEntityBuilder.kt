package com.github.cao.awa.conium.entity.builder.conium

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class ConiumSchemaEntityBuilder(identifier: Identifier) : ConiumEntityBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): ConiumSchemaEntityBuilder {
            val builder = ConiumSchemaEntityBuilder(Identifier.of(json["identifier"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    ConiumTemplate.deserializeEntityTemplates(
                        json["templates"].asJsonObject, registryLookup
                    )
                )
            }

            return builder
        }
    }
}
