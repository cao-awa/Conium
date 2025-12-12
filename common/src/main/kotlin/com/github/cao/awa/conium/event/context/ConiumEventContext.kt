@file:Suppress("unused", "unchecked_cast")

package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.*
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class ConiumEventContext<I: Any>() {
    open var async: Boolean = false
    open lateinit var exception: Throwable
    private val args: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()

    var enabled: Boolean = true

    var identity: Any? = null

    fun warningNoRepeats(field: Any?, message: String): Boolean {
        if (field != null) {
            throw IllegalStateException("The $message already specified, cannot set a new value")
        }
        return true
    }

    private fun resetArgs(args: MutableMap<DynamicArgType<*>, Any?>): ConiumEventContext<I> {
        this.args.clear()
        this.args.putAll(args)
        return this
    }

    open fun <R: Any> transform(required: DynamicArgType<R>, action: R.() -> Unit) {
        val result: R = DynamicArgsBuilder.requiresAny(required).transform(
            this,
            this.args,
            null
        ) as R

        action(result)
    }

    fun async(): ConiumEventContext<I> {
        this.async = true
        return this
    }

    fun sync(): ConiumEventContext<I> {
        this.async = false
        return this
    }

    fun isAsync(): Boolean = this.async

    operator fun <X: Any> set(argType: DynamicArgType<X>, value: X): ConiumEventContext<I> = put(argType, value)

    operator fun <X: Any> get(argType: DynamicArgType<X>): X = this.args[argType].doCast()

    operator fun <X: Any> get(argType: DynamicArgType<X>, default: X): X = this.args[argType]?.doCast() ?: default

    fun <X: Any> put(arg: DynamicArgType<X>, value: X): ConiumEventContext<I> {
        this.args[arg] = value
        return this
    }

    fun <X: Any> dynamic(vararg args: DynamicArgType<X>): ConiumEventContext<I> {
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
