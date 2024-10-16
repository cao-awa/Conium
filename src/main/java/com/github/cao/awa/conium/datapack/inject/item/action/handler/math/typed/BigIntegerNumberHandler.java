package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

import java.math.BigInteger;

public class BigIntegerNumberHandler extends NumberHandler<BigInteger> {
    @Override
    public BigInteger doHandle(BigInteger first, BigInteger second, ItemPropertyInjectAction action) {
        return switch (action) {
            case ADD -> first.add(second);
            case MINUS -> first.subtract(second);
            case DIVIDE -> first.divide(second);
            case MULTIPLY -> first.multiply(second);
            default -> first;
        };
    }
}
