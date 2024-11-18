package com.github.cao.awa.conium.block.builder.conium

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class ConiumSchemaBlockBuilder(identifier: Identifier) : ConiumBlockBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): ConiumSchemaBlockBuilder {
            val builder = ConiumSchemaBlockBuilder(Identifier.of(json["identifier"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    Manipulate.cast(
                        ConiumTemplate.deserializeBlockTemplates(json["templates"].asJsonObject, registryLookup)
                    )
                )
            }

            return builder
        }
    }
}
