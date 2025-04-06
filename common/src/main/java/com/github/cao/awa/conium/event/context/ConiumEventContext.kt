@file:Suppress("unused", "unchecked_cast")

package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.*
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class ConiumEventContext {
    private val args: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()

    var enabled: Boolean = true

    var identity: Any? = null

    private fun resetArgs(args: MutableMap<DynamicArgType<*>, Any?>): ConiumEventContext {
        this.args.clear()
        this.args.putAll(args)
        return this
    }

    operator fun <X> set(argType: DynamicArgType<X>, value: X): ConiumEventContext = put(argType, value)

    operator fun <X> get(argType: DynamicArgType<X>): X = this.args[argType].doCast()

    operator fun <X> get(argType: DynamicArgType<X>, default: X): X = this.args[argType]?.doCast() ?: default

    fun <X> put(arg: DynamicArgType<X>, value: X): ConiumEventContext {
        this.args[arg] = value
        return this
    }

    fun <X> dynamic(vararg args: DynamicArgType<X>): ConiumEventContext {
        for (arg: DynamicArgType<X> in args) {
            this.args[arg] = arg
        }
        return this
    }

    fun inherit(context: ConiumEventContext): ConiumEventContext {
        return resetArgs(context.args).also {
            postInherit(context)
        }
    }

    open fun postInherit(context: ConiumEventContext) {

    }

    fun copyArgs(): MutableMap<DynamicArgType<*>, Any?> {
        return CollectionFactor.hashMap(this.args)
    }
}
