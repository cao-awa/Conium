package com.github.cao.awa.conium.datapack.inject.item

import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.google.gson.JsonObject

@JvmRecord
data class ItemPropertyInject<T>(val target: String, val components: List<ItemPropertyInjectComponent<T>>) {
    companion object {
        @JvmStatic
        fun <X> generic(target: String, components: List<ItemPropertyInjectComponent<*>>): ItemPropertyInject<X> = ItemPropertyInject(target, components.doCast())

        @JvmStatic
        fun deserialize(json: JsonObject): ItemPropertyInject<Any> = generic(json["target"].asString, ItemPropertyInjectComponent.unverified<Any>(json["components"].asJsonArray))
    }
}
