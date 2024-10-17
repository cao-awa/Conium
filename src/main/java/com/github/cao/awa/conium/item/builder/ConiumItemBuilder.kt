package com.github.cao.awa.conium.item.builder

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.JsonObject
import net.minecraft.item.Item
import net.minecraft.util.Identifier

class ConiumItemBuilder(val identifier: Identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): ConiumItemBuilder {
            val builder = ConiumItemBuilder(Identifier.of(json["id"].asString))

            if (json.has("templates")) {
                builder.addTemplates(
                    Manipulate.cast(
                        ConiumTemplate.deserializeTemplates(json["templates"].asJsonObject)
                    )
                )
            }

            return builder
        }
    }

    var templates: MutableList<ConiumItemTemplate> = ApricotCollectionFactor.arrayList()

    fun addTemplates(templates: List<ConiumItemTemplate>): ConiumItemBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(): Item {
        return ConiumItem.create(this)
    }
}
