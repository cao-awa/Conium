package com.github.cao.awa.conium.event.context.arising

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
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
open class ConiumArisingEventContext<P : ParameterSelective?>(
    private val dynamicArgs: DynamicArgs<P, Boolean>
) : ConiumEventContext() {
    private var ariseTrigger: P? = null
    private var presageTrigger: P? = null

    private val attachesPreparation: MutableList<(ConiumArisingEventContext<P>) -> Unit> = CollectionFactor.arrayList()
    private val attaches: MutableList<ConiumArisingEventContext<*>> = CollectionFactor.arrayList()
    private val attachesDynamic: MutableList<P> = CollectionFactor.arrayList()

    var targetedIdentity: ParameterSelective1<Boolean, Any> = ParameterSelective1 { true }

    fun target(target: Any): ConiumEventContext {
        this.targetedIdentity = ParameterSelective1 { it === target }
        return this
    }

    fun <X : Any> targetTo(predicate: (X) -> Boolean): ConiumEventContext {
        this.targetedIdentity = ParameterSelective1 {
            Manipulate.supplyLater {
                predicate(it as X)
            }.getOr(false)
        }
        return this
    }

    fun arise(trigger: P): ConiumArisingEventContext<P> {
        this.ariseTrigger = trigger
        return this
    }

    fun presage(trigger: P): ConiumArisingEventContext<P> {
        this.presageTrigger = trigger
        return this
    }


    fun attachDynamic(dynamicArgs: P): ConiumArisingEventContext<P> {
        this.attachesDynamic.add(dynamicArgs)
        return this
    }

    fun attachDynamics(dynamicArgs: MutableList<P>): ConiumArisingEventContext<P> {
        this.attachesDynamic.addAll(dynamicArgs)
        return this
    }

    fun attach(context: ConiumArisingEventContext<*>): ConiumArisingEventContext<P> {
        this.attaches.add(context)
        return this
    }

    fun attaches(context: MutableList<ConiumArisingEventContext<*>>): ConiumArisingEventContext<P> {
        this.attaches.addAll(context)
        return this
    }

    fun attachPreparation(context: (ConiumArisingEventContext<P>) -> Unit): ConiumArisingEventContext<P> {
        this.attachesPreparation.add(context)
        return this
    }

    fun attachPreparations(context: MutableList<(ConiumArisingEventContext<P>) -> Unit>): ConiumArisingEventContext<P> {
        this.attachesPreparation.addAll(context)
        return this
    }

    fun presaging(identity: Any): Boolean {
        if (!this.enabled) {
            return true
        }

        if (!this.targetedIdentity(identity)) {
            // Do not presage when identity is not target.
            return true
        }

        this.identity = identity

        for (preparation: (ConiumArisingEventContext<P>) -> Unit in this.attachesPreparation) {
            preparation(this)
        }

        var success: Boolean = this.presageTrigger == null || this.dynamicArgs.arising(identity, copyArgs(), this.presageTrigger!!)
        for (attach: ConiumArisingEventContext<*> in this.attaches) {
            if (attach.presageTrigger != null) {
                attach.inherit(this)
                success = attach.presaging(identity) && success
            }
        }
        return success
    }

    fun arising(identity: Any): Boolean {
        if (!this.enabled) {
            return true
        }

        if (!this.targetedIdentity(identity)) {
            // Do not presage when identity is not target.
            return true
        }

        var success: Boolean = this.ariseTrigger == null || this.dynamicArgs.arising(identity, copyArgs(), this.ariseTrigger!!)
        for (attach: ConiumArisingEventContext<*> in this.attaches) {
            if (attach.ariseTrigger != null) {
                attach.inherit(this)
                success = attach.arising(identity) && success
            }
        }
        if (success) {
            for (attachDynamic: P in this.attachesDynamic) {
                success = this.dynamicArgs.arising(
                    identity,
                    copyArgs(),
                    attachDynamic
                )
            }
        }
        return success
    }
}