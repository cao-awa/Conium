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
open class ConiumTypedArisingEventContext<I: Any, P : ParameterSelective?>(
    private val dynamicArgs: DynamicArgs<P, Boolean>
) : ConiumArisingEventContext<I, P>(dynamicArgs) {
    private val attachesPreparation: MutableList<(ConiumTypedArisingEventContext<I, P>) -> Unit> = CollectionFactor.arrayList()
    private val attaches: MutableList<ConiumTypedArisingEventContext<*, *>> = CollectionFactor.arrayList()

    fun attach(context: ConiumTypedArisingEventContext<*, *>): ConiumTypedArisingEventContext<I, P> {
        this.attaches.add(context)
        return this
    }

    fun attaches(context: MutableList<ConiumTypedArisingEventContext<*, *>>): ConiumTypedArisingEventContext<I, P> {
        this.attaches.addAll(context)
        return this
    }

    fun attachPreparation(context: (ConiumTypedArisingEventContext<I, P>) -> Unit): ConiumTypedArisingEventContext<I, P> {
        this.attachesPreparation.add(context)
        return this
    }

    fun attachPreparations(context: MutableList<(ConiumTypedArisingEventContext<I, P>) -> Unit>): ConiumTypedArisingEventContext<I, P> {
        this.attachesPreparation.addAll(context)
        return this
    }
}