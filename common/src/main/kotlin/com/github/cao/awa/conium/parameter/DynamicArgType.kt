package com.github.cao.awa.conium.parameter

class DynamicArgType<T>(val key: String) {
    var dynamicArgs: MutableList<DynamicArgs<*, T?>?> = mutableListOf()

    constructor(key: String, vararg dynamicArgs: DynamicArgs<*, T?>?) : this(key) {
        this.dynamicArgs.addAll(dynamicArgs)
    }

    fun appendArgs(dynamicArgs: DynamicArgs<*, T?>?) {
        this.dynamicArgs.add(dynamicArgs)
    }
}