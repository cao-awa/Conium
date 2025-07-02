package com.github.cao.awa.conium.script.javascript.std.collection.iterator

class JavascriptIterator<V>(private val delegate: MutableIterator<V>): MutableIterator<JavascriptIteratorResult<V>> {
    val done: Boolean get() = hasNext()

    override operator fun next(): JavascriptIteratorResult<V> {
        if (hasNext()) {
            return JavascriptIteratorResult(false, this.delegate.next())
        } else {
            return JavascriptIteratorResult(true, null)
        }
    }

    override fun remove(): Unit = this.delegate.remove()

    override operator fun hasNext(): Boolean = this.delegate.hasNext()
}
