package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.parameter.DynamicArgs

@JvmRecord
data class DynamicArgType<T>(val key: String, val dynamicArgs: DynamicArgs<*, T?>?) {
    constructor(key: String) : this(key, null)
}
