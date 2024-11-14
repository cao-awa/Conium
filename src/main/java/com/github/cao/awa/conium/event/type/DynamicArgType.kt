package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.parameter.DynamicArgs

class DynamicArgType<T>(val key: String, vararg val dynamicArgs: DynamicArgs<*, T?>?) {
    constructor(key: String) : this(key, null)
}
