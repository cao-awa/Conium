package com.github.cao.awa.conium.parameter

class DynamicArgType<I: Any, R>(val key: String) {
    var dynamicArgs: MutableList<DynamicArgs<I, *, R?>?> = mutableListOf()

    constructor(key: String, vararg dynamicArgs: DynamicArgs<I, *, R?>?) : this(key) {
        this.dynamicArgs.addAll(dynamicArgs)
    }

    fun appendArgs(dynamicArgs: DynamicArgs<I, *, R?>?) {
        this.dynamicArgs.add(dynamicArgs)
    }
}