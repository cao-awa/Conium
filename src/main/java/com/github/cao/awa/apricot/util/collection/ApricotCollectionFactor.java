package com.github.cao.awa.apricot.util.collection;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ApricotCollectionFactor {
    public static <K, V> HashBiMap<K, V> hashBiMap() {
        return HashBiMap.create();
        //        return new Object2ObjectOpenHashMap<>();
    }

    public static <K, V> HashBiMap<K, V> hashBiMap(Map<K, V> map) {
        return HashBiMap.create(map);
        //        return new Object2ObjectOpenHashMap<>(map);
    }

    public static <K, V> HashMap<K, V> hashMap() {
        return new HashMap<>();
        //        return new Object2ObjectOpenHashMap<>();
    }

    public static <K, V> HashMap<K, V> hashMap(Map<K, V> map) {
        return new HashMap<>(map);
        //        return new Object2ObjectOpenHashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> linkedHashMap() {
        return new LinkedHashMap<>();
        //        return new Object2ObjectOpenHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> linkedHashMap(Map<K, V> map) {
        return new LinkedHashMap<>(map);
        //        return new Object2ObjectOpenHashMap<>();
    }

    public static <K, V> ConcurrentHashMap<K, V> concurrentHashMap() {
        return new ConcurrentHashMap<>();
    }

    public static <V> List<V> syncList() {
        return Collections.synchronizedList(arrayList());
    }

    public static <V> List<V> syncList(int capacity) {
        return Collections.synchronizedList(arrayList(capacity));
    }

    public static <V> List<V> syncList(List<V> delegate) {
        return Collections.synchronizedList(arrayList(delegate));
    }

    public static <V> ArrayList<V> arrayList() {
        return new ArrayList<>();
        //        return new ObjectArrayList<>();
    }

    public static <V> ArrayList<V> arrayList(int capacity) {
        return new ArrayList<>(capacity);
        //        return new ObjectArrayList<>(capacity);
    }

    public static <V> ArrayList<V> arrayList(List<V> delegate) {
        return new ArrayList<>(delegate);
        //        return new ObjectArrayList<>(delegate);
    }

    public static <V> LinkedList<V> linkedList() {
        return new LinkedList<>();
    }

    public static <V> HashSet<V> hashSet() {
        return new HashSet<>();
        //         return new ObjectOpenHashSet<>();
    }

    public static <V> Set<V> concurrentHashSet() {
        return Sets.newConcurrentHashSet();
    }
}
