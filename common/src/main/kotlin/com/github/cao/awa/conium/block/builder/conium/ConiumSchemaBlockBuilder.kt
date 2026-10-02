package com.github.cao.awa.conium.block.builder.conium

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.resources.Identifier

class ConiumSchemaBlockBuilder(identifier: Identifier) : ConiumBlockBuilder(identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): ConiumSchemaBlockBuilder {
            val builder = ConiumSchemaBlockBuilder(Identifier.parse(json["identifier"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    ConiumTemplate.deserializeBlockTemplates(
                        json["templates"].asJsonObject
                    )
                )
            }

            return builder
        }

        @JvmStatic
        fun earlyDeserialize(json: JsonObject): ConiumSchemaBlockBuilder {
            val builder = ConiumSchemaBlockBuilder(Identifier.parse(json["identifier"].asString))

            return builder
        }
    }
}
