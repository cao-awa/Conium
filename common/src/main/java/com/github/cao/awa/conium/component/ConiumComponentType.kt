package com.github.cao.awa.conium.component

import com.github.cao.awa.conium.component.value.ConiumValueCreator
import com.mojang.serialization.Codec
import net.minecraft.component.ComponentType
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.registry.Registries
import net.minecraft.util.Util

@JvmRecord
data class ConiumComponentType<T>(
    val xCodec: Codec<T>?,
    val packetCodec: PacketCodec<in RegistryByteBuf, T>?,
    val valueCreator: ConiumValueCreator<T>,
    val type: String
) : ComponentType<T> {
    override fun toString(): String = Util.registryValueToString(Registries.DATA_COMPONENT_TYPE, this)

    override fun getCodec(): Codec<T>? = this.xCodec

    override fun getPacketCodec(): PacketCodec<in RegistryByteBuf, T>? = this.packetCodec
}