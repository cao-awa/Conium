package com.github.cao.awa.conium.component;

import com.github.cao.awa.conium.component.value.ConiumValueCreator;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ConiumComponentTypeBuilder<T> {
    @NotNull
    private final String type;
    @Nullable
    private Codec<T> codec;
    @Nullable
    private PacketCodec<? super RegistryByteBuf, T> packetCodec;
    private ConiumValueCreator<T> valueCreator;

    public ConiumComponentTypeBuilder(@NotNull String type) {
        this.type = type;
    }

    public ConiumComponentTypeBuilder<T> codec(Codec<T> codec) {
        this.codec = codec;
        return this;
    }

    public ConiumComponentTypeBuilder<T> packetCodec(PacketCodec<? super RegistryByteBuf, T> packetCodec) {
        this.packetCodec = packetCodec;
        return this;
    }

    public ConiumComponentTypeBuilder<T> valueCreator(ConiumValueCreator<T> valueCreator) {
        this.valueCreator = valueCreator;
        return this;
    }

    public ConiumComponentType<T> build() {
        PacketCodec<? super RegistryByteBuf, T> packetCodec = Objects.requireNonNullElseGet(this.packetCodec, () -> PacketCodecs.registryCodec(Objects.requireNonNull(this.codec, "Missing Codec for component")));
        return new ConiumComponentType<>(this.codec, packetCodec, valueCreator, this.type);
    }
}
