package com.github.cao.awa.conium.datapack.inject.item.action.handler.set;

import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler;

public class ItemPropertyInjectSetHandler<T> extends ItemPropertyInjectHandler<T> {
    @Override
    public T doHandle(T source, T value) {
        return value;
    }
}
