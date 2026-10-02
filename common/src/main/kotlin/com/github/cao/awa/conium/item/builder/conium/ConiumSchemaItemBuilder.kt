package com.github.cao.awa.conium.item.builder.conium

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.resources.Identifier

class ConiumSchemaItemBuilder(identifier: Identifier) : ConiumItemBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): ConiumSchemaItemBuilder {
            // Access identifier field as conium schema.
            // The identifier is required and only as string and cannot miss it.
            val builder = ConiumSchemaItemBuilder(Identifier.parse(json["identifier"]!!.asString!!))

            // Access templates.
            if (json.has("templates")) {
                builder.addTemplates(
                    ConiumTemplate.deserializeItemTemplates(
                        json["templates"].asJsonObject
                    )
                )
            }

            return builder
        }
    }
}
