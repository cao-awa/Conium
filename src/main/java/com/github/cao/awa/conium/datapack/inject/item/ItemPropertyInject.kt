package com.github.cao.awa.conium.datapack.inject.item

import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.JsonObject

@JvmRecord
data class ItemPropertyInject<T>(val target: String, val components: List<ItemPropertyInjectComponent<T>>) {
    companion object {
        @JvmStatic
        fun <X> generic(target: String, components: List<ItemPropertyInjectComponent<*>?>?): ItemPropertyInject<X> {
            return ItemPropertyInject(target, Manipulate.cast(components))
        }

        fun deserialize(json: JsonObject): ItemPropertyInject<Any> {
            val target = json.get("target").asString
            val components = ItemPropertyInjectComponent.unverified<Any>(json.get("components").asJsonArray)

            return generic(target, components)
        }
    }
}
