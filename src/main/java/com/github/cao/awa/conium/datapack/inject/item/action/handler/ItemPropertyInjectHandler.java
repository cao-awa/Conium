package com.github.cao.awa.conium.datapack.inject.item.action.handler;

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectAddHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectDivideHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectMinusHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectMultiplyHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.set.ItemPropertyInjectSetHandler;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;

import java.util.Map;

public abstract class ItemPropertyInjectHandler<T> {
    private static final Map<ItemPropertyInjectAction, ItemPropertyInjectHandler<?>> handlers = Manipulate.make(CollectionFactor.hashMap(), handlers -> {
        handlers.put(ItemPropertyInjectAction.SET, new ItemPropertyInjectSetHandler<>());
        handlers.put(ItemPropertyInjectAction.SET_PRESET, new ItemPropertyInjectSetHandler<>());

        handlers.put(ItemPropertyInjectAction.ADD, new ItemPropertyInjectAddHandler<>());
        handlers.put(ItemPropertyInjectAction.MINUS, new ItemPropertyInjectMinusHandler<>());
        handlers.put(ItemPropertyInjectAction.DIVIDE, new ItemPropertyInjectDivideHandler<>());
        handlers.put(ItemPropertyInjectAction.MULTIPLY, new ItemPropertyInjectMultiplyHandler<>());
    });

    public static <X> X doHandles(X first, X second, ItemPropertyInjectAction action) {
        if (first.getClass() == second.getClass() && handlers.containsKey(action)) {
            return Manipulate.cast(handlers.get(action).doHandle(Manipulate.cast(first), Manipulate.cast(second)));
        } else {
            throw new IllegalArgumentException("No handler registered for " + action);
        }
    }

    public abstract T doHandle(T source, T value);
}
