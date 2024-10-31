package com.github.cao.awa.conium.block.builder

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

class ConiumBlockBuilder(val identifier: Identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockBuilder {
            val builder = ConiumBlockBuilder(Identifier.of(json["id"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    Manipulate.cast(
                        ConiumTemplate.deserializeTemplates(json["templates"].asJsonObject, registryLookup)
                    )
                )
            }

            return builder
        }
    }

    var templates: MutableList<ConiumBlockTemplate> = CollectionFactor.arrayList()

    fun addTemplates(templates: List<ConiumBlockTemplate>): ConiumBlockBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(settings: AbstractBlock.Settings): Block {
        return ConiumBlock.create(this, settings)
    }
}
