package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

public class FloatNumberHandler extends NumberHandler<Float> {
    @Override
    public Float doHandle(Float first, Float second, ItemPropertyInjectAction action) {
        return switch (action) {
            case ADD -> first + second;
            case MINUS -> first - second;
            case DIVIDE -> first / second;
            case MULTIPLY -> first * second;
            default -> first;
        };
    }
}
