package com.github.cao.awa.conium.script.generic.anonymous

import java.util.HashMap

open class AnonymousObject(size: Int) : Iterable<MutableMap.MutableEntry<String, Any?>?> {
    private val delegate: MutableMap<String, Any?> = HashMap(size)

    open fun set(key: String, element: Any?): AnonymousObject {
        this.delegate.put(key, element)
        return this
    }

    open operator fun <T> get(key: String): T? {
        return this.delegate[key] as? T
    }

    open fun <T> getAs(key: String): T {
        return this.delegate[key] as T
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.delegate.entries.iterator()
    }
}