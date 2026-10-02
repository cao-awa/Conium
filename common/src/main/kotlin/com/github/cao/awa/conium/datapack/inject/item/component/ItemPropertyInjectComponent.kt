package com.github.cao.awa.conium.datapack.inject.item.component

import com.github.cao.awa.conium.codec.ConiumPacketCodec
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue.Companion.unverified
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier

@JvmRecord
data class ItemPropertyInjectComponent<T : Any>(
    val type: DataComponentType<*>,
    val action: ItemPropertyInjectAction,
    val value: ItemPropertyInjectComponentValue<T>
) {
    companion object {
        @JvmStatic
        fun decode(buf: RegistryFriendlyByteBuf): ItemPropertyInjectComponent<*> {
            val value: ItemPropertyInjectComponentValue<*> = ConiumPacketCodec.ITEM_PROPERTY_INJECT_COMPONENT_VALUE.decode(buf)

            return ItemPropertyInjectComponent(
                value.componentType!!,
                ConiumPacketCodec.ITEM_PROPERTY_INJECT_ACTION.decode(buf),
                value
            )
        }

        @JvmStatic
        fun encode(buf: RegistryFriendlyByteBuf, value: ItemPropertyInjectComponent<*>) {
            ConiumPacketCodec.ITEM_PROPERTY_INJECT_COMPONENT_VALUE.encode(buf, value.value)
            ConiumPacketCodec.ITEM_PROPERTY_INJECT_ACTION.encode(buf, value.action)
        }

        @JvmStatic
        fun <X : Any> verified(
            type: DataComponentType<*>,
            action: ItemPropertyInjectAction,
            value: ItemPropertyInjectComponentValue<*>
        ): ItemPropertyInjectComponent<X> = ItemPropertyInjectComponent(type, action, value.verified(type))

        @JvmStatic
        fun unverified(json: JsonObject): ItemPropertyInjectComponent<Any> {
            val type: DataComponentType<*> = BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Identifier.parse(json["type"].asString))!!

            val action: ItemPropertyInjectAction = if (json.has("action")) {
                ItemPropertyInjectAction.of(json["action"].asString)
            } else ItemPropertyInjectAction.SET_PRESET

            val value: ItemPropertyInjectComponentValue<*> = unverified(json["value"])

            return verified(type, action, value)
        }

        @JvmStatic
        fun <X> unverified(json: JsonArray): List<ItemPropertyInjectComponent<Any>> {
            val components: MutableList<ItemPropertyInjectComponent<Any>> = ArrayList()
            for (element: JsonElement in json) {
                components.add(unverified(element.asJsonObject))
            }
            return components
        }
    }
}
