package com.github.cao.awa.conium.codec

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue
import com.github.cao.awa.conium.kotlin.extent.innate.int
import io.netty.handler.codec.DecoderException
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.ByteBufCodecs
import java.util.ArrayList

object ConiumPacketCodec {
    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT: StreamCodec<RegistryFriendlyByteBuf, ItemPropertyInjectComponent<*>> =
        StreamCodec.of(
            { buf: RegistryFriendlyByteBuf, component: ItemPropertyInjectComponent<*> ->
                ItemPropertyInjectComponent.encode(
                    buf,
                    component
                )
            },
            { buf: RegistryFriendlyByteBuf -> ItemPropertyInjectComponent.decode(buf) }
        )

    @JvmField
    val ITEM_PROPERTY_INJECT_ACTION: StreamCodec<RegistryFriendlyByteBuf, ItemPropertyInjectAction> = StreamCodec.of(
        { buf: RegistryFriendlyByteBuf, action: ItemPropertyInjectAction ->
            buf.writeByte(action.ordinal)
        },
        { buf: RegistryFriendlyByteBuf ->
            val act: Int = buf.readByte().int
            if (act >= ItemPropertyInjectAction.entries.size) {
                throw DecoderException("Unsupported action: '$act'")
            }
            ItemPropertyInjectAction.entries[act]
        }
    )

    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT_LIST: StreamCodec<RegistryFriendlyByteBuf, MutableList<ItemPropertyInjectComponent<*>>> =
        ITEM_PROPERTY_INJECT_COMPONENT.apply(ByteBufCodecs.collection { ArrayList(it) })

    @JvmField
    val ITEM_PROPERTY_INJECT_COMPONENT_VALUE: StreamCodec<RegistryFriendlyByteBuf, ItemPropertyInjectComponentValue<*>> =
        StreamCodec.of(
            { buf: RegistryFriendlyByteBuf, value: ItemPropertyInjectComponentValue<*> ->
                ItemPropertyInjectComponentValue.encode(
                    buf,
                    value
                )
            },
            { buf: RegistryFriendlyByteBuf -> ItemPropertyInjectComponentValue.decode(buf) }
        )
}
