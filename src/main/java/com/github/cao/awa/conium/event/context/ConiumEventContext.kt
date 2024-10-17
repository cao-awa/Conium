package com.github.cao.awa.conium.event.context

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.mojang.datafixers.util.Function3
import com.mojang.datafixers.util.Function4
import org.apache.logging.log4j.util.TriConsumer
import java.util.function.BiConsumer

class ConiumEventContext<P : ParameterSelective?>(
    private val trigger: Function3<Any, Map<String, Any?>, P, Boolean>,
    vararg args: String
) {
    private var eventTrigger: P? = null

    private val args: MutableMap<String, Any?> = ApricotCollectionFactor.hashMap()
    private val queryArg: MutableList<String> =
        ApricotCollectionFactor.arrayList(args.size)

    init {
        this.queryArg.addAll(args)
    }

    fun trigger(trigger: P): ConiumEventContext<P> {
        this.eventTrigger = trigger
        return this
    }

    fun <X> put(arg: ConiumEventArgType<X>, value: X) {
        this.args[arg.key] = value
    }

    fun trigger(identity: Any): Boolean {
        return this.trigger.apply(identity, this.args, this.eventTrigger!!)
    }
}
