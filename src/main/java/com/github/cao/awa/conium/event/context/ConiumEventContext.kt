package com.github.cao.awa.conium.event.context

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.event.context.lifecycle.ConiumEventContextLifecycle
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.mojang.datafixers.util.Function3

class ConiumEventContext<P : ParameterSelective?>(
    private val trigger: Function3<Any, Map<String, Any?>, P, Boolean>,
    vararg args: String
) {
    private var eventTrigger: P? = null

    private val args: MutableMap<String, Any?> = ApricotCollectionFactor.hashMap()
    private val queryArgs: MutableList<String> = ApricotCollectionFactor.arrayList(args.size)
    private val attaches: MutableList<ConiumEventContext<*>> = ApricotCollectionFactor.arrayList()
    private var lifecycle: ConiumEventContextLifecycle = ConiumEventContextLifecycle.ONCE

    init {
        this.queryArgs.addAll(args)
    }

    fun trigger(trigger: P): ConiumEventContext<P> {
        this.eventTrigger = trigger
        return this
    }

    fun resetArgs(args: MutableMap<String, Any?>): ConiumEventContext<P> {
        this.args.clear()
        this.args.putAll(args)
        return this
    }

    fun resetQueryArgs(args: MutableList<String>): ConiumEventContext<P> {
        this.queryArgs.clear()
        this.queryArgs.addAll(args)
        return this
    }

    fun <X> put(arg: ConiumEventArgType<X>, value: X) {
        this.args[arg.key] = value
    }

    fun attach(context: ConiumEventContext<*>): ConiumEventContext<P> {
        this.attaches.add(context)
        return this
    }

    fun attach(context: MutableList<ConiumEventContext<*>>): ConiumEventContext<P> {
        this.attaches.addAll(context)
        return this
    }

    fun lifecycle(lifecycle: ConiumEventContextLifecycle): ConiumEventContext<P> {
        this.lifecycle = lifecycle
        return this
    }

    fun fire(identity: Any): Boolean {
        var success = this.trigger.apply(identity, this.args, this.eventTrigger!!)
        for (attach in this.attaches) {
            val attachSuccess = attach.resetArgs(this.args)
                .resetQueryArgs(this.queryArgs)
                .fire(identity)

            success = success && attachSuccess
        }
        return success
    }
}
