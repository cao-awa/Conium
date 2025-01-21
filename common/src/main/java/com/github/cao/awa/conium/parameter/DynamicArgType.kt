package com.github.cao.awa.conium.parameter

class DynamicArgType<T>(val key: String, vararg val dynamicArgs: DynamicArgs<*, T?>?)