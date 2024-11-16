package com.github.cao.awa.conium.parameter

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.mojang.datafixers.util.Function3

/**
 * Dynamic args is a data collecting and varying solution, it required arguments by dynamic arg types, and collect the real arguments to trigger.
 *
 * Dynamic args instance should create by [DynamicArgsBuilder], don't create the instance with trigger manually.
 *
 * @see ParameterSelective
 * @see DynamicArgsBuilder
 * @see DynamicArgType
 * @see DynamicArgsLifecycle
 *
 * @param P the type of ``ParameterSelective``
 * @param R result value of the dynamic args
 * @param trigger the trigger that called in arising
 * @param args required arguments, lazy supply a dynamic arg type
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
class DynamicArgs<P : ParameterSelective?, R>(
    private val trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>,
    vararg args: () -> DynamicArgType<*>
) {
    private val queryArgs: MutableList<DynamicArgType<*>> = CollectionFactor.arrayList(args.size)
    private var lifecycle: DynamicArgsLifecycle = DynamicArgsLifecycle.ONCE

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>, vararg args: DynamicArgType<*>) : this(trigger, *args.map {
        // Vary 'DynamicArgType<*>' to '() -> DynamicArgType<*>'
        { it }
    }.toList().toTypedArray())

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>) : this(trigger, *mutableListOf<() -> DynamicArgType<*>>().toTypedArray())

    init {
        this.queryArgs.addAll(args.map { it() })
    }

    fun lifecycle(lifecycle: DynamicArgsLifecycle): DynamicArgs<P, R> {
        this.lifecycle = lifecycle
        return this
    }

    /**
     *
     * @param identity a unique identity instance
     * @param sources source context arguments
     *
     * @see ParameterSelective
     * @see DynamicArgsBuilder
     * @see DynamicArgType
     *
     * @return the new context arguments that trying to fills required arguments instance
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun varyArgs(identity: Any, sources: MutableMap<DynamicArgType<*>, Any?>): Map<DynamicArgType<*>, Any?> {
        val args = CollectionFactor.hashMap<DynamicArgType<*>, Any?>()

        // Find required arguments from context arguments (manually putted to map).
        for (arg in sources) {
            arg.value.let {
                // Only varying argument type to real argument instance.
                if (it is DynamicArgType<*>) {
                    // Dynamic args has multiple varying methods, find until found or no more method can trys.
                    for (dynamicVarying in it.dynamicArgs) {
                        // Run the dynamic vary.
                        val result = dynamicVarying?.runCatching {
                            // Arise the dynamic args, it will continues to vary args or got a value.
                            arising(identity, sources, null)
                        }?.getOrNull()

                        // When result found, stop dynamic args varying.
                        if (result != null) {
                            // And put to arguments map.
                            args[arg.key] = result
                            break
                        }
                    }
                } else {
                    // The real instance should directly put to arguments map.
                    args[arg.key] = it
                }
            }
        }

        return args
    }

    /**
     * @param identity a unique identity instance
     * @param args context arguments
     * @param p the ``ParameterSelective`` instance
     *
     * @see ParameterSelective
     * @see DynamicArgsBuilder
     * @see DynamicArgType
     *
     * @return expected return value instance
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    fun arising(identity: Any, args: MutableMap<DynamicArgType<*>, Any?>, p: P?): R {
        // When 'ParameterSelective' instance is null, means this dynamic args won't got more args, it should got a value.
        if (p == null) {
            // Do trigger directly, no vary args.
            return this.trigger.apply(identity, args, null)
        }

        // Vary args to got more completed arguments.
        // Put 'queryArg'(DynamicArgType<*>) to source arguments map, it will vary to other value in next step varying.
        for (queryArg in this.queryArgs) {
            if (!args.containsKey(queryArg)) {
                args[queryArg] = queryArg
            }
        }
        // Do vary and trigger.
        return this.trigger.apply(identity, varyArgs(identity, args), p)
    }
}