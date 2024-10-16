package com.github.cao.awa.conium.codec;

import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInject;
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent;
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentType;

import java.util.List;

public class ConiumCodec {
    public static final Codec<ItemPropertyInjectAction> ITEM_PROPERTY_INJECT_ACTION = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<ItemPropertyInjectAction> read(DynamicOps<T> ops, T input) {
            String action = input instanceof JsonElement jsonElement ? jsonElement.getAsString() : input.toString();

            return switch (action) {
                case "set" -> DataResult.success(ItemPropertyInjectAction.SET);
                case "set_preset" -> DataResult.success(ItemPropertyInjectAction.SET_PRESET);
                case "minus" -> DataResult.success(ItemPropertyInjectAction.MINUS);
                case "add" -> DataResult.success(ItemPropertyInjectAction.ADD);
                case "divide" -> DataResult.success(ItemPropertyInjectAction.DIVIDE);
                case "multiply" -> DataResult.success(ItemPropertyInjectAction.MULTIPLY);
                default -> DataResult.error(() -> "No that action can be completed: '" + action + "'");
            };
        }

        @Override
        public <T> T write(DynamicOps<T> ops, ItemPropertyInjectAction value) {
            return switch (value) {
                case SET -> ops.createString("set");
                case SET_PRESET -> ops.createString("set_preset");
                case MINUS -> ops.createString("minus");
                case ADD -> ops.createString("add");
                case DIVIDE -> ops.createString("divide");
                case MULTIPLY -> ops.createString("multiply");
            };
        }
    };

    public static Codec<ItemPropertyInjectComponent<?>> ITEM_PROPERTY_INJECT_COMPONENT = RecordCodecBuilder.create(instance -> instance.group(
            ComponentType.CODEC.fieldOf("type").forGetter(ItemPropertyInjectComponent::type),
            ITEM_PROPERTY_INJECT_ACTION.fieldOf("action").orElse(ItemPropertyInjectAction.SET_PRESET).forGetter(ItemPropertyInjectComponent::action),
            new PrimitiveCodec<ItemPropertyInjectComponentValue<?>>() {
                @Override
                public <T> DataResult<ItemPropertyInjectComponentValue<?>> read(DynamicOps<T> ops, T input) {
                    return DataResult.success(ItemPropertyInjectComponentValue.unverified(input));
                }

                @Override
                public <T> T write(DynamicOps<T> ops, ItemPropertyInjectComponentValue<?> value) {
                    // Cannot write.
                    return null;
                }
            }.fieldOf("value").forGetter(ItemPropertyInjectComponent::value)
    ).apply(instance, ItemPropertyInjectComponent::verified));

    public static final Codec<ItemPropertyInject<?>> ITEM_PROPERTY_INJECT = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("target").forGetter(ItemPropertyInject::target),
            ITEM_PROPERTY_INJECT_COMPONENT.listOf().fieldOf("components").forGetter(inject -> (List) inject.components())
    ).apply(instance, ItemPropertyInject::generic));
}
