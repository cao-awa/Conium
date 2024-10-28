package com.github.cao.awa.conium.codec

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import io.netty.handler.codec.DecoderException
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

object ConiumPacketCodec {
    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT: PacketCodec<RegistryByteBuf, ItemPropertyInjectComponent<*>> =
        PacketCodec.ofStatic(
            { buf: RegistryByteBuf, component: ItemPropertyInjectComponent<*> ->
                ItemPropertyInjectComponent.encode(
                    buf,
                    component
                )
            },
            { buf: RegistryByteBuf -> ItemPropertyInjectComponent.decode(buf) })

    @JvmField
    val ITEM_PROPERTY_INJECT_ACTION: PacketCodec<RegistryByteBuf, ItemPropertyInjectAction> = PacketCodec.ofStatic(
        { buf: RegistryByteBuf, action: ItemPropertyInjectAction ->
            buf.writeByte(action.ordinal)
        },
        { buf: RegistryByteBuf ->
            val act = buf.readByte().toInt()
            if (act >= ItemPropertyInjectAction.entries.size) {
                throw DecoderException("Unsupported action: '$act'")
            }
            ItemPropertyInjectAction.entries[act]
        })

    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT_LIST: PacketCodec<RegistryByteBuf, MutableList<ItemPropertyInjectComponent<*>>> =
        ITEM_PROPERTY_INJECT_COMPONENT.collect(PacketCodecs.toCollection(CollectionFactor::arrayList))

    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT_VALUE: PacketCodec<RegistryByteBuf, ItemPropertyInjectComponentValue<*>> =
        PacketCodec.ofStatic(
            { buf: RegistryByteBuf, value: ItemPropertyInjectComponentValue<*> ->
                ItemPropertyInjectComponentValue.encode(
                    buf,
                    value
                )
            },
            { buf: RegistryByteBuf -> ItemPropertyInjectComponentValue.decode(buf) })
}
