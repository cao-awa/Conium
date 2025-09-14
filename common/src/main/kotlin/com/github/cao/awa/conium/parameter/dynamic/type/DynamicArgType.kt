package com.github.cao.awa.conium.parameter.dynamic.type

import com.github.cao.awa.conium.parameter.dynamic.DynamicArgs

class DynamicArgType<R>(val key: String) {
    var dynamicArgs: MutableList<DynamicArgs<*, R?>?> = mutableListOf()

    constructor(key: String, vararg dynamicArgs: DynamicArgs<*, R?>?) : this(key) {
        this.dynamicArgs.addAll(dynamicArgs)
    }

    fun appendArgs(dynamicArgs: DynamicArgs<*, R?>?) {
        this.dynamicArgs.add(dynamicArgs)
    }
}