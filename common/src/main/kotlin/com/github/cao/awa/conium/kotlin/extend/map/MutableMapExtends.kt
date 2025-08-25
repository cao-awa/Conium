package com.github.cao.awa.conium.kotlin.extend.map

inline infix fun <reified K> MutableMap<K, *>.remove(key: K) = this.remove(key)
inline infix fun <reified K, reified V> MutableMap<K, V>.putAll(values: Map<K, V>) = this.putAll(values)