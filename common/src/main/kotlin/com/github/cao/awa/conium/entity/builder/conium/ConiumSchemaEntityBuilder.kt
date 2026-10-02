package com.github.cao.awa.conium.entity.builder.conium

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.resources.Identifier

class ConiumSchemaEntityBuilder(identifier: Identifier) : ConiumEntityBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): ConiumSchemaEntityBuilder {
            val builder = ConiumSchemaEntityBuilder(Identifier.parse(json["identifier"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    ConiumTemplate.deserializeEntityTemplates(
                        json["templates"].asJsonObject
                    )
                )
            }

            return builder
        }
    }
}
