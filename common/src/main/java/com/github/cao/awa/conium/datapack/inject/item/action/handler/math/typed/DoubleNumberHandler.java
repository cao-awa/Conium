package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

public class DoubleNumberHandler extends NumberHandler<Double> {
    @Override
    public Double doHandle(Double first, Double second, ItemPropertyInjectAction action) {
        return switch (action) {
            case ADD -> first + second;
            case MINUS -> first - second;
            case DIVIDE -> first / second;
            case MULTIPLY -> first * second;
            default -> first;
        };
    }
}
