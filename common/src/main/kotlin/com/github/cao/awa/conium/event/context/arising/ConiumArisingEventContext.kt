package com.github.cao.awa.conium.event.context.arising

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgs
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.parameter.ParameterSelective2
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
open class ConiumArisingEventContext<I: Any, P : ParameterSelective?>(
    private val dynamicArgs: DynamicArgs<P, Boolean>
) : ConiumEventContext<I>() {
    companion object {
        @JvmStatic
        fun <P: ParameterSelective> of(dynamic: DynamicArgs<*, Boolean>): ConiumArisingEventContext<Any, P> {
            return ConiumArisingEventContext(dynamic.doCast())
        }
    }

    private var ariseTrigger: P? = null
    private var presageTrigger: P? = null

    private val attachesPreparation: MutableList<(ConiumArisingEventContext<I, P>) -> Unit> = CollectionFactor.arrayList()
    private val attaches: MutableList<ConiumArisingEventContext<*, *>> = CollectionFactor.arrayList()
    private val attachesDynamic: MutableList<P> = CollectionFactor.arrayList()

    var targetedIdentity: ParameterSelective1<Boolean, Any> = ParameterSelective1 { true }

    fun target(target: Any): ConiumEventContext<I> {
        this.targetedIdentity = ParameterSelective1 { it === target }
        return this
    }

    fun <X : Any> targetTo(predicate: (X) -> Boolean): ConiumEventContext<I> {
        this.targetedIdentity = ParameterSelective1 {
            Manipulate.supplyLater {
                predicate(it as X)
            }.getOr(false)
        }

        return this
    }

    fun arise(trigger: P): ConiumArisingEventContext<I, P> {
        this.ariseTrigger = trigger
        return this
    }

    fun presage(trigger: P): ConiumArisingEventContext<I, P> {
        this.presageTrigger = trigger
        return this
    }

    fun attachDynamic(dynamicArgs: P): ConiumArisingEventContext<I, P> {
        this.attachesDynamic.add(dynamicArgs)
        return this
    }

    fun attachDynamics(dynamicArgs: MutableList<P>): ConiumArisingEventContext<I, P> {
        this.attachesDynamic.addAll(dynamicArgs)
        return this
    }

    fun attach(context: ConiumArisingEventContext<*, *>): ConiumArisingEventContext<I, P> {
        this.attaches.add(context)
        return this
    }

    fun attaches(context: MutableList<ConiumArisingEventContext<*, *>>): ConiumArisingEventContext<I, P> {
        this.attaches.addAll(context)
        return this
    }

    fun attachPreparation(context: (ConiumArisingEventContext<I, P>) -> Unit): ConiumArisingEventContext<I, P> {
        this.attachesPreparation.add(context)
        return this
    }

    fun attachPreparations(context: MutableList<(ConiumArisingEventContext<I, P>) -> Unit>): ConiumArisingEventContext<I, P> {
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

        for (preparation: (ConiumArisingEventContext<I, P>) -> Unit in this.attachesPreparation) {
            preparation(this)
        }

        var success: Boolean = this.presageTrigger == null || this.dynamicArgs.transform(identity, copyArgs(), this.presageTrigger!!)
        for (attach: ConiumArisingEventContext<*, *> in this.attaches) {
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

        this.identity = identity

        var success: Boolean = this.ariseTrigger == null || this.dynamicArgs.transform(identity, copyArgs(), this.ariseTrigger!!)
        for (attach: ConiumArisingEventContext<*, *> in this.attaches) {
            if (attach.ariseTrigger != null) {
                attach.inherit(this)
                success = attach.arising(identity) && success
            }
        }
        if (success) {
            for (attachDynamic: P in this.attachesDynamic) {
                success = this.dynamicArgs.transform(
                    identity,
                    copyArgs(),
                    attachDynamic
                )
            }
        }
        return success
    }
}