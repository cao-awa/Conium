package com.github.cao.awa.conium.script.javascript.std.collection.set

import com.github.cao.awa.conium.annotation.script.javascript.JavaScriptApi
import com.github.cao.awa.conium.script.javascript.std.collection.iterator.JavascriptIterator
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

@JavaScriptApi("Set")
class Set<V> : MutableSet<V> {
    private val delegate: MutableSet<V> = CollectionFactor.hashSet()

    // Delegate properties.
    // Also 'size' in Javascript.
    @JavaScriptApi("Set", "#size")
    override val size: Int get() = this.delegate.size

    // Delegate operations.
    override fun iterator(): MutableIterator<V> = this.delegate.iterator()

    // Also 'add' in JavaScript.
    @JavaScriptApi("Set", "add")
    override fun add(element: V): Boolean = this.delegate.add(element)
    override fun remove(element: V): Boolean = this.delegate.remove(element)
    override fun addAll(elements: Collection<V>): Boolean = this.delegate.addAll(elements)
    override fun removeAll(elements: Collection<V>): Boolean = this.delegate.removeAll(elements.toSet())
    override fun retainAll(elements: Collection<V>): Boolean = this.delegate.retainAll(elements.toSet())

    // Also 'clear' in JavaScript.
    @JavaScriptApi("Set", "clear")
    override fun clear(): Unit = this.delegate.clear()
    override fun isEmpty(): Boolean = this.delegate.isEmpty()
    override fun contains(element: @UnsafeVariance V): Boolean = this.delegate.contains(element)
    override fun containsAll(elements: Collection<@UnsafeVariance V>): Boolean = this.delegate.containsAll(elements)

    // Javascript operations.
    @JavaScriptApi("Set", "delete")
    fun delete(element: V): Boolean = remove(element)
    @JavaScriptApi("Set", "forEach")
    fun forEach(action: (@UnsafeVariance V, V, Int) -> Unit): Unit = this.delegate.forEachIndexed { index: Int, value: V -> action(value, value, index) }
    @JavaScriptApi("Set", "has")
    fun has(element: @UnsafeVariance V): Boolean = contains(element)
    @JavaScriptApi("Set", "keys")
    fun keys(): JavascriptIterator<V> = values()
    @JavaScriptApi("Set", "values")
    fun values(): JavascriptIterator<V> = JavascriptIterator(iterator())
}
