package com.github.cao.awa.conium.item.builder

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumTemplate
import com.google.gson.JsonObject
import net.minecraft.item.Item
import net.minecraft.util.Identifier

class ConiumItemBuilder(val identifier: Identifier) {
    companion object {
        @JvmStatic
        fun deserialize(json: JsonObject): ConiumItemBuilder {
            val builder = ConiumItemBuilder(Identifier.of(json["id"].asString))

            if (json.has("templates")) {
                builder.templates(ConiumTemplate.deserializeTemplates(json["templates"].asJsonObject))
            }

            return builder
        }
    }

    var templates: List<ConiumTemplate>? = null

    fun templates(templates: List<ConiumTemplate>): ConiumItemBuilder {
        this.templates = templates
        return this
    }

    fun build(): Item {
        return ConiumItem.create(this)
    }
}
