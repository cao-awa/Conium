package com.github.cao.awa.conium.registry.lookup;

import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public interface DynamicTagLookup<T> {
    static <T> DynamicTagLookup<T> ofUnbound() {
        return new DynamicTagLookup<>() {
            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public Optional<RegistryEntryList.Named<T>> getOptional(TagKey<T> key) {
                throw new IllegalStateException("Tags not bound, trying to access " + key);
            }

            @Override
            public void forEach(BiConsumer<? super TagKey<T>, ? super RegistryEntryList.Named<T>> consumer) {
                throw new IllegalStateException("Tags not bound");
            }

            @Override
            public Stream<RegistryEntryList.Named<T>> stream() {
                throw new IllegalStateException("Tags not bound");
            }
        };
    }

    static <T> DynamicTagLookup<T> fromMap(Map<TagKey<T>, RegistryEntryList.Named<T>> map) {
        return new DynamicTagLookup<>() {
            @Override
            public boolean isBound() {
                return true;
            }

            @Override
            public Optional<RegistryEntryList.Named<T>> getOptional(TagKey<T> key) {
                return Optional.ofNullable(map.get(key));
            }

            @Override
            public void forEach(BiConsumer<? super TagKey<T>, ? super RegistryEntryList.Named<T>> consumer) {
                map.forEach(consumer);
            }

            @Override
            public Stream<RegistryEntryList.Named<T>> stream() {
                return map.values().stream();
            }
        };
    }

    boolean isBound();

    Optional<RegistryEntryList.Named<T>> getOptional(TagKey<T> key);

    void forEach(BiConsumer<? super TagKey<T>, ? super RegistryEntryList.Named<T>> consumer);

    Stream<RegistryEntryList.Named<T>> stream();
}