@file:Suppress("unchecked_cast", "unused")

package com.github.cao.awa.conium.parameter.type

import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgs
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

object DynamicArgTypeBuilder {
    val args: MutableMap<String, DynamicArgType<*>> = CollectionFactor.hashMap()

    fun <T> arg(name: String, value: Class<T>): DynamicArgType<T> {
        return this.args.computeIfAbsent(name) {
            return@computeIfAbsent DynamicArgType<T>(name)
        } as DynamicArgType<T>
    }

    fun <T> arg(name: String, vararg dynamicArgs: DynamicArgs<*, T?>?, value: Class<T>): DynamicArgType<T> {
        return this.args.computeIfAbsent(name) {
            return@computeIfAbsent DynamicArgType<T>(name, *dynamicArgs)
        } as DynamicArgType<T>
    }

    fun <T> arg(name: String): DynamicArgType<T> {
        return this.args.computeIfAbsent(name) {
            return@computeIfAbsent DynamicArgType<T>(name)
        } as DynamicArgType<T>
    }

    fun <T> arg(name: String, vararg dynamicArgs: DynamicArgs<*, T?>?): DynamicArgType<T> {
        return this.args.computeIfAbsent(name) {
            return@computeIfAbsent DynamicArgType<T>(name, *dynamicArgs)
        } as DynamicArgType<T>
    }
}
