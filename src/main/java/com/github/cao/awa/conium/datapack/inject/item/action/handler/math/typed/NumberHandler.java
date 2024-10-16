package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction;

import java.math.BigInteger;
import java.util.Map;

public abstract class NumberHandler<T extends Number> {
    private static final Map<Class<? extends Number>, NumberHandler<? extends Number>> handlers = Manipulate.operation(ApricotCollectionFactor.hashMap(), handlers -> {
        handlers.put(Integer.class, new IntegerNumberHandler());
        handlers.put(Long.class, new LongNumberHandler());
        handlers.put(Float.class, new FloatNumberHandler());
        handlers.put(Double.class, new DoubleNumberHandler());
        handlers.put(BigInteger.class, new BigIntegerNumberHandler());
    });

    public abstract T doHandle(T first, T second, ItemPropertyInjectAction action);

    public static <X extends Number> X doHandles(Number first, Number second, ItemPropertyInjectAction action) {
        if (first.getClass() == second.getClass() && handlers.containsKey(first.getClass())) {
            return Manipulate.cast(handlers.get(first.getClass()).doHandle(Manipulate.cast(first), Manipulate.cast(second), action));
        } else {
            throw new IllegalArgumentException("Unsupported number type: " + first.getClass() + " and " + second.getClass());
        }
    }
}
