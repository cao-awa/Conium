package com.github.cao.awa.conium.script.generic.anonymous

import java.util.HashMap

open class AnonymousObject(size: Int) : Iterable<MutableMap.MutableEntry<String, Any?>> {
    val delegate: MutableMap<String, Any?> = HashMap(size)

    open fun set(key: String, element: Any?): AnonymousObject {
        this.delegate.put(key, element)
        return this
    }

    inline operator fun <reified T> get(key: String): T? {
        return this.delegate[key] as? T
    }

    inline fun <reified T> getAs(key: String): T {
        return this.delegate[key] as T
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.delegate.entries.iterator()
    }
}

fun AnonymousObject.getNumber(key: String): Number = getAs(key)
fun AnonymousObject.getFloat(key: String): Float = getAs(key)
fun AnonymousObject.getDouble(key: String): Double = getAs(key)
fun AnonymousObject.getInt(key: String): Int = getAs(key)
fun AnonymousObject.getLong(key: String): Long = getAs(key)
fun AnonymousObject.getString(key: String): String = getAs(key)
