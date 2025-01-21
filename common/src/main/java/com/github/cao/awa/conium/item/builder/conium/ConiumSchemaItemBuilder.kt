package com.github.cao.awa.conium.item.builder.conium

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class ConiumSchemaItemBuilder(identifier: Identifier) : ConiumItemBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): ConiumSchemaItemBuilder {
            // Access identifier field as conium schema.
            // The identifier has required and only as string, cannot missing it.
            val builder = ConiumSchemaItemBuilder(Identifier.of(json["identifier"]!!.asString!!))

            // Access templates.
            if (json.has("templates")) {
                builder.addTemplates(
                    Manipulate.cast(
                        ConiumTemplate.deserializeItemTemplates(json["templates"].asJsonObject, registryLookup)
                    )
                )
            }

            return builder
        }
    }
}
