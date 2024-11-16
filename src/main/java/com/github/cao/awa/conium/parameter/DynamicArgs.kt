package com.github.cao.awa.conium.parameter

import com.github.cao.awa.conium.event.type.DynamicArgType
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.mojang.datafixers.util.Function3

class DynamicArgs<P : ParameterSelective?, R>(
    private val trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>,
    vararg args: () -> DynamicArgType<*>
) {
    private val queryArgs: MutableList<DynamicArgType<*>> = CollectionFactor.arrayList(args.size)
    private var lifecycle: DynamicArgsLifecycle = DynamicArgsLifecycle.ONCE

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>, vararg args: DynamicArgType<*>) : this(trigger, *args.map {
        val getter: () -> DynamicArgType<*> = { it }
        getter
    }.toList().toTypedArray())

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>) : this(trigger, *mutableListOf<() -> DynamicArgType<*>>().toTypedArray())

    init {
        this.queryArgs.addAll(args.map { it() })
    }

    fun lifecycle(lifecycle: DynamicArgsLifecycle): DynamicArgs<P, R> {
        this.lifecycle = lifecycle
        return this
    }

    private fun varyArgs(identity: Any, sources: MutableMap<DynamicArgType<*>, Any?>): Map<DynamicArgType<*>, Any?> {
        val args = CollectionFactor.hashMap<DynamicArgType<*>, Any?>()

        for (arg in sources) {
            arg.value.let {
                if (it is DynamicArgType<*>) {
                    for (dynamic in it.dynamicArgs) {
                        val result = dynamic?.runCatching {
                            arising(identity, sources, null)
                        }?.getOrNull()

                        if (result != null) {
                            args[arg.key] = result
                            break
                        }
                    }
                } else {
                    args[arg.key] = it
                }
            }
        }

        return args
    }

    fun arising(identity: Any, args: MutableMap<DynamicArgType<*>, Any?>, p: P?): R {
        if (p == null) {
            return this.trigger.apply(identity, args, null)
        }

        for (queryArg in this.queryArgs) {
            if (!args.containsKey(queryArg)) {
                args[queryArg] = queryArg
            }
        }
        return this.trigger.apply(identity, varyArgs(identity, args), p)
    }
}