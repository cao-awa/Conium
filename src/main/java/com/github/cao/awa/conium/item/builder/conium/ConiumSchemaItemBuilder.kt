package com.github.cao.awa.conium.item.builder.conium

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class ConiumSchemaItemBuilder(val identifier: Identifier): ConiumItemBuilder {
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

    private val coniumTemplates: MutableList<ConiumItemTemplate> = CollectionFactor.arrayList()

    override fun templates(): MutableList<ConiumItemTemplate> = this.coniumTemplates
}
