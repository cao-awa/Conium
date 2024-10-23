package com.github.cao.awa.conium.block.builder

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.item.Item
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

    var templates: MutableList<ConiumBlockTemplate> = ApricotCollectionFactor.arrayList()

    fun addTemplates(templates: List<ConiumBlockTemplate>): ConiumBlockBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(): Block {
        return ConiumBlock.create(this)
    }
}
