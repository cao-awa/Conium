package com.github.cao.awa.conium.datapack.inject.item.component

import com.github.cao.awa.conium.component.ConiumComponentType
import com.github.cao.awa.conium.extent.manipulate.cast
import net.minecraft.component.ComponentType
import net.minecraft.network.RegistryByteBuf

@JvmRecord
data class ItemPropertyInjectComponentValue<X>(val value: X?, val componentType: ComponentType<X>?) {
    companion object {
        @JvmStatic
        fun decode(buf: RegistryByteBuf): ItemPropertyInjectComponentValue<*> {
            val componentType: ComponentType<*> = ComponentType.PACKET_CODEC.decode(buf)

            return ItemPropertyInjectComponentValue(
                componentType.packetCodec.decode(buf),
                componentType.cast()
            )
        }

        @JvmStatic
        fun encode(buf: RegistryByteBuf, value: ItemPropertyInjectComponentValue<*>) {
            ComponentType.PACKET_CODEC.encode(buf, value.componentType)
            value.componentType!!.packetCodec.encode(buf, value.value.cast())
        }

        @JvmStatic
        fun unverified(value: Any): ItemPropertyInjectComponentValue<*> {
            return ItemPropertyInjectComponentValue(value, null)
        }
    }

    fun <Y> verified(type: ComponentType<*>): ItemPropertyInjectComponentValue<Y> {
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
