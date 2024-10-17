package com.github.cao.awa.conium.template

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.item.ConiumItem
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.util.function.Function

abstract class ConiumTemplate(private val name: String) {
    companion object {
        private val templates: MutableMap<String, Function<JsonElement, ConiumTemplate>> = ApricotCollectionFactor.hashMap()

        @JvmStatic
        fun register(name: String, template: Function<JsonElement, ConiumTemplate>) {
            this.templates[name] = template
        }

        fun deserializeTemplates(json: JsonObject): MutableList<ConiumTemplate> {
            val templates: MutableList<ConiumTemplate> = ApricotCollectionFactor.arrayList()

            for (entry in json.entrySet()) {
                val name = entry.key
                val value = entry.value

                templates.add(deserializeTemplate(name, value))
            }
            return templates
        }

        fun deserializeTemplate(name: String, json: JsonElement): ConiumTemplate {
            return this.templates[name]!!.apply(json)
        }
    }

    fun name(): String {
        return this.name
    }

    abstract fun attach(item: ConiumItem)
}
