package com.github.cao.awa.conium.parameter

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.event.type.DynamicArgType
import com.mojang.datafixers.util.Function3

class DynamicArgs<P : ParameterSelective?, R>(
    private val trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>,
    vararg args: DynamicArgType<*>
) {
    private val queryArgs: MutableList<DynamicArgType<*>> = ApricotCollectionFactor.arrayList(args.size)
    private var lifecycle: DynamicArgsLifecycle = DynamicArgsLifecycle.ONCE

    init {
        this.queryArgs.addAll(args)
    }

    fun lifecycle(lifecycle: DynamicArgsLifecycle): DynamicArgs<P, R> {
        this.lifecycle = lifecycle
        return this
    }

    private fun varyArgs(identity: Any, sources: MutableMap<DynamicArgType<*>, Any?>): Map<DynamicArgType<*>, Any?> {
        val args = ApricotCollectionFactor.hashMap<DynamicArgType<*>, Any?>()

        for (arg in sources) {
            arg.value.let {
                if (it is DynamicArgType<*>) {
                    args.put(arg.key, it.dynamicArgs?.arising(identity, sources, null))
                } else {
                    args.put(arg.key, it)
                }
            }
        }

        return args
    }

    fun arising(identity: Any, args: MutableMap<DynamicArgType<*>, Any?>, p: P?): R {
        if (p == null) {
            return this.trigger.apply(identity, args, p)
        }

        for (queryArg in this.queryArgs) {
            if (!args.containsKey(queryArg)) {
                args[queryArg] = queryArg
            }
        }
        return this.trigger.apply(identity, varyArgs(identity, args), p)
    }
}