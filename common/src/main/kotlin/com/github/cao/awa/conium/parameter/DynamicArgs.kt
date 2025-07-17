package com.github.cao.awa.conium.parameter

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.mojang.datafixers.util.Function3
import java.util.*
import java.util.function.Supplier

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
class DynamicArgs<P : ParameterSelective?, R> {
    private val trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>
    private val args: MutableList<DynamicArgType<*>>
    private val queryArgs: MutableList<DynamicArgType<*>>
    private var lifecycle: DynamicArgsLifecycle = DynamicArgsLifecycle.ONCE

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>, vararg args: DynamicArgType<*>) {
        this.trigger = trigger
        this.args = args.toMutableList()
        this.queryArgs = this.args
    }

    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>, vararg args: Supplier<DynamicArgType<*>>) {
        this.trigger = trigger
        this.args = args.map(Supplier<DynamicArgType<*>>::get).toMutableList()
        this.queryArgs = this.args
    }

    // No varying args constructor.
    constructor(trigger: Function3<Any, Map<DynamicArgType<*>, Any?>, P, R>) {
        this.trigger = trigger
        this.args = Collections.emptyList()
        this.queryArgs = Collections.emptyList()
    }

    fun lifecycle(lifecycle: DynamicArgsLifecycle): DynamicArgs<P, R> {
        this.lifecycle = lifecycle
        return this
    }

    /**
     * Collect not-real values and vary to the real value to arguments map.
     *
     * @param identity a unique identity instance
     * @param sources source context arguments
     *
     * @see ParameterSelective
     * @see DynamicArgsBuilder
     * @see DynamicArgType
     *
     * @return the new context arguments that trying to fill required arguments instance
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun varyArgs(identity: Any, sources: MutableMap<DynamicArgType<*>, Any?>): Map<DynamicArgType<*>, Any?> {
        return synchronized(this) {
            val args: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()

            // Find required arguments from context arguments (manually putted to map).
            // Copy the map data to prevent ConcurrentModificationException.
            for ((key: DynamicArgType<*>, value: Any?) in CollectionFactor.hashMap(sources)) {
                // Only varying argument type to real argument instance.
                if (value is DynamicArgType<*>) {
                    // Dynamic args has multiple varying methods, find until found or no more method can try.
                    for (dynamicVarying: DynamicArgs<*, *>? in value.dynamicArgs) {
                        // Do not process null dynamic args or transform the dynamic args that doesn't have correct lifecycles.
                        if (dynamicVarying == null || dynamicVarying.lifecycle != DynamicArgsLifecycle.TRANSFORM) {
                            continue
                        }

                        // Run the dynamic vary, usually, not all args must got a result, when exception, it means the args cannot transform from any other args.
                        val result: Any? = dynamicVarying.runCatching {
                            // Arise the dynamic args, it will continue to vary args or got a value.
                            transform(identity, sources, null)
                        }.getOrNull()

                        // When a result found, stop dynamic args varying.
                        if (result != null) {
                            // And put to arguments map.
                            args[key] = result
                            break
                        }
                    }
                } else {
                    // The real instance should directly put to the argument map.
                    args[key] = value
                }
            }

            args
        }
    }

    /**
     * Collect and varying arguments when the context arising.
     *
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
    fun transform(identity: Any, args: MutableMap<DynamicArgType<*>, Any?>, p: P?): R {
        // Vary args to got more completed arguments.
        // Put 'queryArg' to source arguments map, it will vary to other value in the next step varying.
        for (queryArg: DynamicArgType<*> in this.queryArgs) {
            // Do not add query arg to varying when args contains the real value.
            if (args.containsKey(queryArg)) {
                continue
            }
            // Put to varying when no real value contains.
            args[queryArg] = queryArg
        }

        // Do vary and trigger.
        return this.trigger.apply(identity, varyArgs(identity, args), p)
    }
}