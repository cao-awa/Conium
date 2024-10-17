package com.github.cao.awa.conium.component;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.conium.component.value.ConiumValueCreator;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.UnaryOperator;

public class ConiumComponentTypes {
    private static final Map<Identifier, ConiumComponentType<?>> types = ApricotCollectionFactor.hashMap();

    public static final ConiumComponentType<Integer> TEST = register(
            "test",
            (builder) -> builder.codec(Codec.INT).packetCodec(PacketCodecs.INTEGER),
            JsonElement::getAsInt
    );

    public static <T> ConiumComponentType<T> register(String path, UnaryOperator<ConiumComponentTypeBuilder<T>> builderOperator, ConiumValueCreator<T> valueCreator) {
        return register(Identifier.of("conium", path), builderOperator, valueCreator);
    }

    public static <T> ConiumComponentType<T> register(Identifier id, UnaryOperator<ConiumComponentTypeBuilder<T>> builderOperator, ConiumValueCreator<T> valueCreator) {
        ConiumComponentType<T> type = builderOperator.apply(new ConiumComponentTypeBuilder<T>(id.toString()).valueCreator(valueCreator)).build();
        Registry.register(Registries.DATA_COMPONENT_TYPE, id, type);
        types.put(id, type);
        return type;
    }

    public static void init() {

    }

    public static ConiumComponentType<?> get(String path) {
        return types.get(Identifier.of("conium", path));
    }

    public static ConiumComponentType<?> get(Identifier identifier) {
        return types.get(identifier);
    }

    public static <T> T createValue(String path, JsonElement element) {
        return createValue(Identifier.of("conium", path), element);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createValue(Identifier type, JsonElement element) {
        return (T) types.get(type).valueCreator().createValue(element);
    }
}
