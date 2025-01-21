@file:Suppress("unused")
package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgs
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

/**
 *
 * @see ParameterSelective
 * @see ConiumEventContextBuilder
 * @see DynamicArgType
 * @see DynamicArgs
 * @see ConiumEvent
 * @see ConiumEventArgTypes
 *
 * @param P the type of ``ParameterSelective``
 * @param dynamicArgs the dynamic args that provide the argument input to the triggers.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
class ConiumEventContext<P : ParameterSelective?>(
    private val dynamicArgs: DynamicArgs<P, Boolean>
) {
    private var ariseTrigger: P? = null
    private var presageTrigger: P? = null

    private val args: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()
    private val attaches: MutableList<ConiumEventContext<*>> = CollectionFactor.arrayList()
    private val attachesDynamic: MutableList<P> = CollectionFactor.arrayList()

    private var targetedIdentity: ParameterSelective1<Boolean, Any> = ParameterSelective1 { true }

    fun target(target: Any): ConiumEventContext<P> {
        this.targetedIdentity = ParameterSelective1 { it === target }
        return this
    }

    @Suppress("unchecked_cast")
    fun <X : Any> targetTo(predicate: (X) -> Boolean): ConiumEventContext<P> {
        this.targetedIdentity = ParameterSelective1 {
            Manipulate.supplyLater {
                predicate(it as X)
            }.getOr(false)
        }
        return this
    }

    fun arise(trigger: P): ConiumEventContext<P> {
        this.ariseTrigger = trigger
        return this
    }

    fun presage(trigger: P): ConiumEventContext<P> {
        this.presageTrigger = trigger
        return this
    }

    private fun resetArgs(args: MutableMap<DynamicArgType<*>, Any?>): ConiumEventContext<P> {
        this.args.clear()
        this.args.putAll(args)
        return this
    }

    operator fun <X> set(argType: DynamicArgType<X>, value: X): ConiumEventContext<P> = put(argType, value)

    fun <X> put(arg: DynamicArgType<X>, value: X): ConiumEventContext<P> {
        this.args[arg] = value
        return this
    }

    fun <X> dynamic(vararg args: DynamicArgType<X>): ConiumEventContext<P> {
        for (arg: DynamicArgType<X> in args) {
            this.args[arg] = arg
        }
        return this
    }

    fun attachDynamic(dynamicArgs: P): ConiumEventContext<P> {
        this.attachesDynamic.add(dynamicArgs)
        return this
    }

    fun attachDynamic(dynamicArgs: MutableList<P>): ConiumEventContext<P> {
        this.attachesDynamic.addAll(dynamicArgs)
        return this
    }

    fun attach(context: ConiumEventContext<*>): ConiumEventContext<P> {
        this.attaches.add(context)
        return this
    }

    fun attach(context: MutableList<ConiumEventContext<*>>): ConiumEventContext<P> {
        this.attaches.addAll(context)
        return this
    }

    fun presaging(identity: Any): Boolean {
        if (!this.targetedIdentity(identity)) {
            // Do not presage when identity is not target.
            return true
        }

        var success: Boolean = this.presageTrigger == null || this.dynamicArgs.arising(identity, this.args, this.presageTrigger!!)
        for (attach: ConiumEventContext<*> in this.attaches) {
            if (attach.presageTrigger != null) {
                attach.inherit(this)
                success = attach.presaging(identity) && success
            }
        }
        return success
    }

    fun arising(identity: Any): Boolean {
        if (!this.targetedIdentity(identity)) {
            // Do not presage when identity is not target.
            return true
        }

        var success: Boolean = this.ariseTrigger == null || this.dynamicArgs.arising(identity, this.args, this.ariseTrigger!!)
        for (attach: ConiumEventContext<*> in this.attaches) {
            if (attach.ariseTrigger != null) {
                attach.inherit(this)
                success = attach.arising(identity) && success
            }
        }
        for (attachDynamic: P in this.attachesDynamic) {
            this.dynamicArgs.arising(
                identity,
                this.args,
                attachDynamic
            )
        }
        return success
    }

    fun inherit(context: ConiumEventContext<*>): ConiumEventContext<P> {
        return resetArgs(context.args)
    }
}
