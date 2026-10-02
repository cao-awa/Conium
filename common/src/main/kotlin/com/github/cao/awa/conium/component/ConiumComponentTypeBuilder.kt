package com.github.cao.awa.conium.component

import com.github.cao.awa.conium.component.value.ConiumValueCreator
import com.mojang.serialization.Codec
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.ByteBufCodecs

class ConiumComponentTypeBuilder<T : Any>(val type: String, val valueCreator: ConiumValueCreator<T>) {
    private var codec: Codec<T>? = null
    private var packetCodec: StreamCodec<in RegistryFriendlyByteBuf, T>? = null
    private var skipsHandAnimation: Boolean = false

    fun codec(codec: Codec<T>): ConiumComponentTypeBuilder<T> {
        this.codec = codec
        return this
    }

    fun packetCodec(packetCodec: StreamCodec<in RegistryFriendlyByteBuf, T>): ConiumComponentTypeBuilder<T> {
        this.packetCodec = packetCodec
        return this
    }

    fun skipsHandAnimation(skipsHandAnimation: Boolean) {
        this.skipsHandAnimation = skipsHandAnimation
    }

    fun build(): ConiumComponentType<T> {
        val codecVal: Codec<T> = this.codec ?: throw IllegalStateException("Missing Codec for component")
        val packetCodec: StreamCodec<in RegistryFriendlyByteBuf, T> = this.packetCodec ?: ByteBufCodecs.fromCodecWithRegistries(codecVal)
        return ConiumComponentType(this.codec, packetCodec, this.valueCreator, this.type, this.skipsHandAnimation)
    }
}
