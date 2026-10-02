package com.github.cao.awa.conium.component

import com.github.cao.awa.conium.component.value.ConiumValueCreator
import com.mojang.serialization.Codec
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.core.registries.BuiltInRegistries

@JvmRecord
data class ConiumComponentType<T : Any>(
    val xCodec: Codec<T>?,
    val packetCodec: StreamCodec<in RegistryFriendlyByteBuf, T>?,
    val valueCreator: ConiumValueCreator<T>,
    val type: String,
    val isSkipsHandAnimation: Boolean
) : DataComponentType<T> {
    override fun toString(): String = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(this)?.toString() ?: type

    override fun codec(): Codec<T>? = this.xCodec

    override fun ignoreSwapAnimation(): Boolean = this.isSkipsHandAnimation

    override fun streamCodec(): StreamCodec<in RegistryFriendlyByteBuf, T> =
        this.packetCodec ?: ByteBufCodecs.fromCodecWithRegistries(this.xCodec!!)
}
