package com.github.cao.awa.conium.datapack.inject.item.component

import com.github.cao.awa.conium.component.ConiumComponentType
import com.github.cao.awa.conium.extent.caster.cast
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec

@JvmRecord
data class ItemPropertyInjectComponentValue<X : Any>(val value: X?, val componentType: DataComponentType<X>?) {
    companion object {
        @JvmStatic
        fun decode(buf: RegistryFriendlyByteBuf): ItemPropertyInjectComponentValue<*> {
            val componentType: DataComponentType<*> = DataComponentType.STREAM_CODEC.decode(buf)

            return ItemPropertyInjectComponentValue(
                componentType.streamCodec().decode(buf),
                componentType.cast()
            )
        }

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun encode(buf: RegistryFriendlyByteBuf, value: ItemPropertyInjectComponentValue<*>) {
            DataComponentType.STREAM_CODEC.encode(buf, value.componentType!!)
            val codec = value.componentType.streamCodec() as StreamCodec<RegistryFriendlyByteBuf, Any>
            codec.encode(buf, value.value!!)
        }

        @JvmStatic
        fun unverified(value: Any): ItemPropertyInjectComponentValue<*> {
            return ItemPropertyInjectComponentValue(value, null)
        }
    }

    fun <Y : Any> verified(type: DataComponentType<*>): ItemPropertyInjectComponentValue<Y> {
        if (type is ConiumComponentType<*>) {
            type.let {
                return ItemPropertyInjectComponentValue(
                    it.valueCreator.castValue(this.value).cast(),
                    type.cast()
                )
            }
        }

        return ItemPropertyInjectComponentValue(null, null)
    }
}
