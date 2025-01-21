package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

public class LongNumberHandler extends NumberHandler<Long> {
    @Override
    public Long doHandle(Long first, Long second, ItemPropertyInjectAction action) {
        return switch (action) {
            case ADD -> first + second;
            case MINUS -> first - second;
            case DIVIDE -> first / second;
            case MULTIPLY -> first * second;
            default -> first;
        };
    }
}
