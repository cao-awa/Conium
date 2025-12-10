package com.github.cao.awa.conium.component

import com.github.cao.awa.conium.component.value.ConiumValueCreator
import com.mojang.serialization.Codec
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import java.util.*

class ConiumComponentTypeBuilder<T>(val type: String, val valueCreator: ConiumValueCreator<T>) {
    private var codec: Codec<T>? = null
    private var packetCodec: PacketCodec<in RegistryByteBuf, T>? = null
    private var skipsHandAnimation: Boolean = false

    fun codec(codec: Codec<T>): ConiumComponentTypeBuilder<T> {
        this.codec = codec
        return this
    }

    fun packetCodec(packetCodec: PacketCodec<in RegistryByteBuf, T>): ConiumComponentTypeBuilder<T> {
        this.packetCodec = packetCodec
        return this
    }

    fun skipsHandAnimation(skipsHandAnimation: Boolean) {
        this.skipsHandAnimation = skipsHandAnimation
    }

    fun build(): ConiumComponentType<T> {
        val packetCodec: PacketCodec<in RegistryByteBuf, T> = Objects.requireNonNullElseGet(this.packetCodec) {
            PacketCodecs.registryCodec(Objects.requireNonNull(this.codec, "Missing Codec for component"))
        }
        return ConiumComponentType(this.codec, packetCodec, this.valueCreator, this.type, this.skipsHandAnimation)
    }
}
