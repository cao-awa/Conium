package com.github.cao.awa.conium.item.builder.bedrock

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class BedrockSchemaItemBuilder(val identifier: Identifier) : ConiumItemBuilder {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): BedrockSchemaItemBuilder {
            return json["minecraft:item"].asJsonObject.let {
                val builder = BedrockSchemaItemBuilder(Identifier.of(it["description"]!!.asJsonObject!!["identifier"].asString!!))

                it["components"]?.asJsonObject!!.let { components ->
                    builder.addTemplates(
                        Manipulate.cast(
                            ConiumTemplate.deserializeItemTemplates(components, registryLookup)
                        )
                    )
                }

                return@let builder
            }
        }
    }

    private val bedrockTemplates: MutableList<ConiumItemTemplate> = CollectionFactor.arrayList()

    override fun templates(): MutableList<ConiumItemTemplate> = this.bedrockTemplates
}
