@file:Suppress("unused", "unchecked_cast")

package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.*
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class ConiumEventContext<I: Any>() {
    private val args: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()

    var enabled: Boolean = true

    var identity: Any? = null

    private fun resetArgs(args: MutableMap<DynamicArgType<*>, Any?>): ConiumEventContext<I> {
        this.args.clear()
        this.args.putAll(args)
        return this
    }

    open fun <R> transform(required: DynamicArgType<R>, action: R.() -> Unit) {
        val result: R = DynamicArgsBuilder.requiresAny(required).transform(
            this,
            this.args,
            null
        ) as R

        action(result)
    }

    operator fun <X> set(argType: DynamicArgType<X>, value: X): ConiumEventContext<I> = put(argType, value)

    operator fun <X> get(argType: DynamicArgType<X>): X = this.args[argType].doCast()

    operator fun <X> get(argType: DynamicArgType<X>, default: X): X = this.args[argType]?.doCast() ?: default

    fun <X> put(arg: DynamicArgType<X>, value: X): ConiumEventContext<I> {
        this.args[arg] = value
        return this
    }

    fun <X> dynamic(vararg args: DynamicArgType<X>): ConiumEventContext<I> {
        for (arg: DynamicArgType<X> in args) {
            this.args[arg] = arg
        }
        return this
    }

    fun inherit(context: ConiumEventContext<*>): ConiumEventContext<I> {
        return resetArgs(context.args).also {
            postInherit(context)
        }
    }

    open fun postInherit(context: ConiumEventContext<*>) {
        // Nothing here.
    }

    fun copyArgs(): MutableMap<DynamicArgType<*>, Any?> {
        return CollectionFactor.hashMap(this.args)
    }
}
