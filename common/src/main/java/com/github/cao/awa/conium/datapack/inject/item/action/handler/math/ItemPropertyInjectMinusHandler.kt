package com.github.cao.awa.conium.datapack.inject.item.action.handler.math;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed.NumberHandler;

public class ItemPropertyInjectMinusHandler<T extends Number> extends ItemPropertyInjectHandler<T> {
    @Override
    public T doHandle(T source, T value) {
        if (source instanceof Number sourceNumber && value instanceof Number valueNumber) {
            return NumberHandler.doHandles(sourceNumber, valueNumber, ItemPropertyInjectAction.MINUS);
        }
        return source;
    }
}
