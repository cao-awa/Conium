@file:Suppress("unused", "unchecked_cast")

package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.script.manager.ConiumScriptManager
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.*
import com.github.cao.awa.conium.script.interaction.NamedInteractionScript

/**
 * Building the event contexts.
 *
 * @see ConiumEvent
 * @see ConiumArisingEventContext
 * @see DynamicArgs
 * @see DynamicArgType
 * @see DynamicArgsBuilder
 * @see ParameterSelective
 * @see DynamicArgsLifecycle
 * @see NamedInteractionScript
 * @see ConiumScriptManager
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object ConiumEventContextBuilder {
    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun requires(): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> = ConiumArisingEventContext(DynamicArgsBuilder.force())

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1> requires(
        arg1: DynamicArgType<P1>
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     * @param P3 Type of third parameter
     * @param arg3 The third parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     * @param P3 Type of third parameter
     * @param arg3 The third parameter will input to attached contexts
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     * @param P3 Type of third parameter
     * @param arg3 The third parameter will input to attached contexts
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter will input to attached contexts
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     * @param P3 Type of third parameter
     * @param arg3 The third parameter will input to attached contexts
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter will input to attached contexts
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter will input to attached contexts
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5, P6> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true))

    /**
     * Make an event context that should only call in event [requirement][ConiumEvent.requirement].
     *
     * The event context be the base context to arise attached contexts with no argument provided.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter will input to attached contexts
     * @param P2 Type of second parameter
     * @param arg2 The second parameter will input to attached contexts
     * @param P3 Type of third parameter
     * @param arg3 The third parameter will input to attached contexts
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter will input to attached contexts
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter will input to attached contexts
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter will input to attached contexts
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter will input to attached contexts
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [ONCE][DynamicArgsLifecycle.ONCE] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5, P6, P7> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumArisingEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true))

    private fun <P : ParameterSelective, I : Any> forever(eventType: ConiumEventType<I>, dynamicArgs: DynamicArgs<P, Boolean>): ConiumArisingEventContext<P> {
        return ConiumArisingEventContext(dynamicArgs).apply {
            // Attach this context to event.
            ConiumEvent.forever(eventType, this)
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> preRequest(
        eventType: ConiumEventType<I>,
        presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { true }
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return forever(
            eventType,
            // Require a no argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage {
                (it as? I)?.let(presaging::arise) ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> preRequestNr(
        eventType: ConiumEventType<I>,
        presaging: ParameterSelective1<Unit, I> = ParameterSelective1 { }
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return preRequest(eventType) { i: I ->
            presaging(i)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> request(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { true }
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return forever(
            eventType,
            // Require a no argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise {
                (it as? I)?.let(arising::arise) ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> requestNr(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Unit, I> = ParameterSelective1 { }
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return request(eventType) { i: I ->
            arising(i)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> request(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { true },
        presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { true },
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return forever(
            eventType,
            // Require a no argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage {
                (it as? I)?.let(presaging::arise) ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise {
                (it as? I)?.let(arising::arise) ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any> requestNr(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Unit, I> = ParameterSelective1 { },
        presaging: ParameterSelective1<Unit, I> = ParameterSelective1 { },
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return request(
            eventType,
            { i: I ->
                arising(i)
                true
            },
            { i: I ->
                presaging(i)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return forever(
            eventType,
            // Require a one-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1 ->
                (i as? I)?.let { presaging(it, p1) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        presaging: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return preRequest(
            eventType,
            arg1
        ) { i: I, p1: P1 ->
            presaging(i, p1)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return forever(
            eventType,
            // Require a one-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1 ->
                (i as? I)?.let { arising(it, p1) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return request(
            eventType,
            arg1
        ) { i: I, p1: P1 ->
            arising(i, p1)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true },
        presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return forever(
            eventType,
            // Require a one-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1 ->
                (i as? I)?.let { presaging(it, p1) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1 ->
                (i as? I)?.let { arising(it, p1) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> },
        presaging: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return request(
            eventType,
            arg1,
            { i: I, p1: P1 ->
                arising(i, p1)
                true
            },
            { i: I, p1: P1 ->
                presaging(i, p1)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return forever(
            eventType,
            // Require a two-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2 ->
                (i as? I)?.let { presaging(it, p1, p2) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        presaging: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return preRequest(
            eventType,
            arg1,
            arg2
        ) { i: I, p1: P1, p2: P2 ->
            presaging(i, p1, p2)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return forever(
            eventType,
            // Require a two-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2 ->
                (i as? I)?.let { arising(it, p1, p2) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return request(
            eventType,
            arg1,
            arg2
        ) { i: I, p1: P1, p2: P2 ->
            arising(i, p1, p2)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
        presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return forever(
            eventType,
            // Require a two-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2 ->
                (i as? I)?.let { presaging(it, p1, p2) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2 ->
                (i as? I)?.let { arising(it, p1, p2) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> },
        presaging: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> },
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return request(
            eventType,
            arg1,
            arg2,
            { i: I, p1: P1, p2: P2 ->
                arising(i, p1, p2)
                true
            },
            { i: I, p1: P1, p2: P2 ->
                presaging(i, p1, p2)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return forever(
            eventType,
            // Require a three-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3 ->
                (i as? I)?.let { presaging(it, p1, p2, p3) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        presaging: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return preRequest(
            eventType,
            arg1,
            arg2,
            arg3
        ) { i: I, p1: P1, p2: P2, p3: P3 ->
            presaging(i, p1, p2, p3)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return forever(
            eventType,
            // Require a three-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i, p1, p2, p3 ->
                (i as? I)?.let { arising(it, p1, p2, p3) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3
        ) { i: I, p1: P1, p2: P2, p3: P3 ->
            arising(i, p1, p2, p3)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true },
        presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return forever(
            eventType,
            // Require a three-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3 ->
                (i as? I)?.let { presaging(it, p1, p2, p3) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3 ->
                (i as? I)?.let { arising(it, p1, p2, p3) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> },
        presaging: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            { i: I, p1: P1, p2: P2, p3: P3 ->
                arising(i, p1, p2, p3)
                true
            },
            { i: I, p1: P1, p2: P2, p3: P3 ->
                presaging(i, p1, p2, p3)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The fourth parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return forever(
            eventType,
            // Require a four-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The fourth parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The third parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        presaging: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return preRequest(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4 ->
            presaging(i, p1, p2, p3, p4)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return forever(
            eventType,
            // Require a four-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4 ->
            arising(i, p1, p2, p3, p4)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true },
        presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return forever(
            eventType,
            // Require a four-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> },
        presaging: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4 ->
                arising(i, p1, p2, p3, p4)
                true
            },
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4 ->
                presaging(i, p1, p2, p3, p4)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return forever(
            eventType,
            // Require a five-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        presaging: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return preRequest(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
            presaging(i, p1, p2, p3, p4, p5)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return forever(
            eventType,
            // Require a five-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
            arising(i, p1, p2, p3, p4, p5)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true },
        presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return forever(
            eventType,
            // Require a five-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> },
        presaging: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                arising(i, p1, p2, p3, p4, p5)
                true
            },
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                presaging(i, p1, p2, p3, p4, p5)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        presaging: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return forever(
            eventType,
            // Require a six-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        presaging: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return preRequest(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
            presaging(i, p1, p2, p3, p4, p5, p6)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return forever(
            eventType,
            // Require a six-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
            arising(i, p1, p2, p3, p4, p5, p6)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true },
        presaging: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return forever(
            eventType,
            // Require a six-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> },
        presaging: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6,
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                arising(i, p1, p2, p3, p4, p5, p6)
                true
            },
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                presaging(i, p1, p2, p3, p4, p5, p6)
                true
            }
        )
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        presaging: ParameterSelective8<Boolean, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return forever(
            eventType,
            // Require a seven-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                arg7,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers presaging in the context with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> preRequestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        presaging: ParameterSelective8<Unit, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return preRequest(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6,
            arg7
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
            presaging(i, p1, p2, p3, p4, p5, p6, p7)
            true
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Boolean, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return forever(
            eventType,
            // Require a seven-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                arg7,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that only triggers arising in the context with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Unit, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6,
            arg7
        ) { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
            arising(i, p1, p2, p3, p4, p5, p6, p7)
            true
        }
    }

    /**
     * Make a sub context of the specified event type with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Boolean, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> true },
        presaging: ParameterSelective8<Boolean, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> true }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return forever(
            eventType,
            // Require a seven-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                arg7,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            // Attach presage trigger, this is event be cancelable when parameter selective returns false.
            presage { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
            }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
            }
        }
    }

    /**
     * Make a sub context of the specified event type with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     * @param presaging The presaging trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, P1, P2, P3, P4, P5, P6, P7> requestNr(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Unit, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> },
        presaging: ParameterSelective8<Unit, I, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> }
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return request(
            eventType,
            arg1,
            arg2,
            arg3,
            arg4,
            arg5,
            arg6,
            arg7,
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                arising(i, p1, p2, p3, p4, p5, p6, p7)
                true
            },
            { i: I, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                presaging(i, p1, p2, p3, p4, p5, p6, p7)
                true
            }
        )
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with no arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<R, I>
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return forever(
            eventType,
            // Require a no argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with one argument, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<R, I, P1>
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return forever(
            eventType,
            // Require a one-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with two arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<R, I, P1, P2>
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return forever(
            eventType,
            // Require a two-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of the specified event type that changes a variable every time trigger with three arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2, P3> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<R, I, P1, P2, P3>
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return forever(
            eventType,
            // Require a three-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2, p3)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with four arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2, P3, P4> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<R, I, P1, P2, P3, P4>
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return forever(
            eventType,
            // Require a four-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2, p3, p4)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with five arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2, P3, P4, P5> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<R, I, P1, P2, P3, P4, P5>
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return forever(
            eventType,
            // Require a five-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2, p3, p4, p5)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with six arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2, P3, P4, P5, P6> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<R, I, P1, P2, P3, P4, P5, P6>
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return forever(
            eventType,
            // Require a six-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2, p3, p4, p5, p6)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make a sub context of specified event type that changes a variable every time trigger with seven arguments, then auto attach to forever lifecycle of the event type.
     *
     * The context will arise when event base context arising and invalidate when reload events.
     *
     * @param I The specified input identity instance, see the reified type of [ConiumEventType][ConiumEventType]
     * @param R The result type of the parameter selective
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param name The name of this exporting context
     * @param defaultResult The default value when failure
     * @param eventType The event type that will be attaching
     * @param arising The arising trigger, default is always successes
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     * @see NamedInteractionScript
     *
     * @return The context instance with [FOREVER][DynamicArgsLifecycle.FOREVER] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <I : Any, R : Any, P1, P2, P3, P4, P5, P6, P7> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<R, I, P1, P2, P3, P4, P5, P6, P7>
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return forever(
            eventType,
            // Require a seven-argument dynamic args and contract to forever lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        ).apply {
            var result: R = defaultResult

            // Export to script manager, let data driven frameworks can access the context.
            Conium.scriptManager!!.export(name, this) { result }

            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                // Change the result variable.
                result = (i as? I)?.let {
                    arising(i, p1, p2, p3, p4, p5, p6, p7)
                } ?: defaultResult
                true
            }
        }
    }

    /**
     * Make an unnamed event context that receiving the event context source, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param arising The callback to handle the event context source
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun unnamedNr(
        arising: (Any, ConiumArisingEventContext<*>) -> Unit
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return ConiumArisingEventContext(
            // Require a no argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any ->
                arising(i, this)
                true
            }
        }
    }

    /**
     * Make an unnamed event context that no anything, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param arising The callback to handle the event context source
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun unnamed(): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return ConiumArisingEventContext(
            // Require a no argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )
    }

    /**
     * Make an unnamed event context that receiving the event context source, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param arising The callback to handle the event context source
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun unnamed(
        arising: (Any, ConiumArisingEventContext<*>) -> Boolean
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return ConiumArisingEventContext(
            // Require a no argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any ->
                arising(i, this)
            }
        }
    }

    /**
     * Make an unnamed event context with no arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param R The result type of the parameter selective
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <R : Any> unnamed(
        arising: ParameterSelective1<R, Any>
    ): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> {
        return ConiumArisingEventContext(
            // Require a no argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any ->
                i.let(arising::arise)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with one argument, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1> unnamed(
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Unit, Any, P1>
    ): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return ConiumArisingEventContext(
            // Require a one-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1 ->
                arising(i, p1)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with two arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Unit, Any, P1, P2>
    ): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return ConiumArisingEventContext(
            // Require a two-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2 ->
                arising(i, p1, p2)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with three arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Unit, Any, P1, P2, P3>
    ): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return ConiumArisingEventContext(
            // Require a three-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3 ->
                arising(i, p1, p2, p3)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with four arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Unit, Any, P1, P2, P3, P4>
    ): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return ConiumArisingEventContext(
            // Require a four-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
                arising(i, p1, p2, p3, p4)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with five arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Unit, Any, P1, P2, P3, P4, P5>
    ): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return ConiumArisingEventContext(
            // Require a five-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
                arising(i, p1, p2, p3, p4, p5)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with six arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5, P6> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Unit, Any, P1, P2, P3, P4, P5, P6>
    ): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return ConiumArisingEventContext(
            // Require a six-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
                arising(i, p1, p2, p3, p4, p5, p6)
                true
            }
        }
    }

    /**
     * Make an unnamed event context with seven arguments, it should manually attach to the events that need to attach.
     *
     * The context lifecycle only related to the lifecycle of its attached event context.
     *
     * @param P1 Type of first parameter
     * @param arg1 The first parameter that will be input to the parameter selective instance
     * @param P2 Type of second parameter
     * @param arg2 The second parameter that will be input to the parameter selective instance
     * @param P3 Type of third parameter
     * @param arg3 The third parameter that will be input to the parameter selective instance
     * @param P4 Type of fourth parameter
     * @param arg4 The fourth parameter that will be input to the parameter selective instance
     * @param P5 Type of fifth parameter
     * @param arg5 The fifth parameter that will be input to the parameter selective instance
     * @param P6 Type of sixth parameter
     * @param arg6 The sixth parameter that will be input to the parameter selective instance
     * @param P7 Type of seventh parameter
     * @param arg7 The seventh parameter that will be input to the parameter selective instance
     * @param arising The arising trigger
     *
     * @see ConiumEvent
     * @see ConiumArisingEventContext
     * @see DynamicArgs
     * @see DynamicArgType
     * @see ConiumEventArgTypes
     * @see ParameterSelective
     * @see DynamicArgsLifecycle
     *
     * @return The context instance with [UNNAMED][DynamicArgsLifecycle.UNNAMED] lifecycle.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun <P1, P2, P3, P4, P5, P6, P7> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Unit, Any, P1, P2, P3, P4, P5, P6, P7>
    ): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return ConiumArisingEventContext(
            // Require a seven-argument dynamic args and contract to unnamed lifecycle.
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        ).apply {
            // Attach arise trigger, not event cancelable.
            arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
                arising(i, p1, p2, p3, p4, p5, p6, p7)
                true
            }
        }
    }
}
