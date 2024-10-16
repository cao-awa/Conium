package com.github.cao.awa.conium.datapack.inject.item.action.handler;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectAddHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectDivideHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectMinusHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.ItemPropertyInjectMultiplyHandler;
import com.github.cao.awa.conium.datapack.inject.item.action.handler.set.ItemPropertyInjectSetHandler;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

import java.util.Map;

public abstract class ItemPropertyInjectHandler<T> {
    private static final Map<ItemPropertyInjectAction, ItemPropertyInjectHandler<?>> handlers = Manipulate.operation(ApricotCollectionFactor.hashMap(), handlers -> {
        handlers.put(ItemPropertyInjectAction.SET, new ItemPropertyInjectSetHandler<>());
        handlers.put(ItemPropertyInjectAction.SET_PRESET, new ItemPropertyInjectSetHandler<>());

        handlers.put(ItemPropertyInjectAction.ADD, new ItemPropertyInjectAddHandler<>());
        handlers.put(ItemPropertyInjectAction.MINUS, new ItemPropertyInjectMinusHandler<>());
        handlers.put(ItemPropertyInjectAction.DIVIDE, new ItemPropertyInjectDivideHandler<>());
        handlers.put(ItemPropertyInjectAction.MULTIPLY, new ItemPropertyInjectMultiplyHandler<>());
    });

    public abstract T doHandle(T source, T value);

    public static <X> X doHandles(X first, X second, ItemPropertyInjectAction action) {
        if (first.getClass() == second.getClass() && handlers.containsKey(action)) {
            return Manipulate.cast(handlers.get(action).doHandle(Manipulate.cast(first), Manipulate.cast(second)));
        } else {
            throw new IllegalArgumentException("No handler registered for " + action);
        }
    }
}
