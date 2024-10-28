package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import java.util.function.BiFunction
import java.util.function.Function

abstract class ConiumTemplate<T>(private val name: String) {
    companion object {
        private val templates: MutableMap<String, BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*>>> = CollectionFactor.hashMap()

        @JvmStatic
        fun register(name: String, template: BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*>>) {
            this.templates[name] = template
        }

        fun deserializeTemplates(json: JsonObject, registryLookup: WrapperLookup): MutableList<ConiumTemplate<*>> {
            val templates: MutableList<ConiumTemplate<*>> = CollectionFactor.arrayList()

            for (entry in json.entrySet()) {
                val name = entry.key
                val value = entry.value

                templates.add(deserializeTemplate(name, value, registryLookup))
            }
            return templates
        }

        fun deserializeTemplate(name: String, json: JsonElement, registryLookup: WrapperLookup): ConiumTemplate<*> {
            return this.templates[name]!!.apply(json, registryLookup)
        }
    }

    fun name(): String {
        return this.name
    }

    abstract fun attach(item: T)

    abstract fun complete(item: T)
}
