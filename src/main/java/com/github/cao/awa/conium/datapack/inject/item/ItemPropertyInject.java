package com.github.cao.awa.conium.datapack.inject.item;

import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent;

import java.util.List;

public record ItemPropertyInject<T>(String target, List<ItemPropertyInjectComponent<T>> components) {
    public static <X> ItemPropertyInject<X> generic(String target, List<ItemPropertyInjectComponent<?>> components) {
        return new ItemPropertyInject<>(target, Manipulate.cast(components));
    }
}
