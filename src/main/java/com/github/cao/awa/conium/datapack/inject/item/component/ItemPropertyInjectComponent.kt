package com.github.cao.awa.conium.datapack.inject.item.component

import com.github.cao.awa.conium.codec.ConiumPacketCodec
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue.Companion.unverified
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.ComponentType
import net.minecraft.network.RegistryByteBuf
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

@JvmRecord
data class ItemPropertyInjectComponent<T>(
    val type: ComponentType<*>,
    val action: ItemPropertyInjectAction,
    val value: ItemPropertyInjectComponentValue<T>
) {
    companion object {
        @JvmStatic
        fun decode(buf: RegistryByteBuf): ItemPropertyInjectComponent<*> {
            val value: ItemPropertyInjectComponentValue<*> = ConiumPacketCodec.ITEM_PROPERTY_INJECT_COMPONENT_VALUE.decode(buf)

            return ItemPropertyInjectComponent(
                value.componentType!!,
                ConiumPacketCodec.ITEM_PROPERTY_INJECT_ACTION.decode(buf),
                value
            )
        }

        @JvmStatic
        fun encode(buf: RegistryByteBuf, value: ItemPropertyInjectComponent<*>) {
            ConiumPacketCodec.ITEM_PROPERTY_INJECT_COMPONENT_VALUE.encode(buf, value.value)
            ConiumPacketCodec.ITEM_PROPERTY_INJECT_ACTION.encode(buf, value.action)
        }

        @JvmStatic
        fun <X> verified(
            type: ComponentType<*>,
            action: ItemPropertyInjectAction,
            value: ItemPropertyInjectComponentValue<*>
        ): ItemPropertyInjectComponent<X> = ItemPropertyInjectComponent(type, action, value.verified(type))

        @JvmStatic
        fun unverified(json: JsonObject): ItemPropertyInjectComponent<Any> {
            val type: ComponentType<*>? = Registries.DATA_COMPONENT_TYPE[Identifier.of(json["type"].asString)]

            val action: ItemPropertyInjectAction = if (json.has("action")) {
                ItemPropertyInjectAction.of(json["action"].asString)
            } else ItemPropertyInjectAction.SET_PRESET

            val value: ItemPropertyInjectComponentValue<*> = unverified(json["value"])

            return verified(Manipulate.cast(type), action, value)
        }

        @JvmStatic
        fun <X> unverified(json: JsonArray): List<ItemPropertyInjectComponent<Any>> {
            val components: MutableList<ItemPropertyInjectComponent<Any>> = CollectionFactor.arrayList()
            for (element: JsonElement in json) {
                components.add(unverified(element.asJsonObject))
            }
            return components
        }
    }
}
