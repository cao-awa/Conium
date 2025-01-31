package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

public class IntegerNumberHandler extends NumberHandler<Integer> {
    @Override
    public Integer doHandle(Integer first, Integer second, ItemPropertyInjectAction action) {
        return switch (action) {
            case ADD -> first + second;
            case MINUS -> first - second;
            case DIVIDE -> first / second;
            case MULTIPLY -> first * second;
            default -> first;
        };
    }
}
