@file:Suppress("unchecked_cast", "unused")

package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.*

object ConiumEventContextBuilder {
    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun requires(): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        return ConiumEventContext(DynamicArgsBuilder.force())
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1> requires(
        arg1: DynamicArgType<P1>
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, true))
    }

    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6, P7> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        return ConiumEventContext(DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any> preRequest(
        eventType: ConiumEventType<I>,
        presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { _ -> true }
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage {
            (it as? I)?.let(presaging::arise) ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any> request(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { _ -> true }
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise {
            (it as? I)?.let(arising::arise) ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any> request(
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { _ -> true },
        presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { _ -> true },
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise {
            (it as? I)?.let(arising::arise) ?: true
        }

        context.presage {
            (it as? I)?.let(presaging::arise) ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1 ->
            (i as? I)?.let { presaging(it, p1) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1 ->
            (i as? I)?.let { arising(it, p1) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true },
        presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1 ->
            (i as? I)?.let { arising(it, p1) } ?: true
        }

        context.presage { i, p1 ->
            (i as? I)?.let { presaging(it, p1) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2 ->
            (i as? I)?.let { presaging(it, p1, p2) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2 ->
            (i as? I)?.let { arising(it, p1, p2) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
        presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2 ->
            (i as? I)?.let { arising(it, p1, p2) } ?: true
        }

        context.presage { i, p1, p2 ->
            (i as? I)?.let { presaging(it, p1, p2) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2, p3 ->
            (i as? I)?.let { presaging(it, p1, p2, p3) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3 ->
            (i as? I)?.let { arising(it, p1, p2, p3) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true },
        presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3 ->
            (i as? I)?.let { arising(it, p1, p2, p3) } ?: true
        }

        context.presage { i, p1, p2, p3 ->
            (i as? I)?.let { presaging(it, p1, p2, p3) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2, p3, p4 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4) } ?: true
        }

        return context
    }


    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true },
        presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4) } ?: true
        }

        context.presage { i, p1, p2, p3, p4 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4, P5> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2, p3, p4, p5 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4, P5> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4, P5> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true },
        presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5) } ?: true
        }

        context.presage { i, p1, p2, p3, p4, p5 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4, P5, P6> preRequest(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        presaging: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2, p3, p4, p5, p6 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, P1, P2, P3, P4, P5, P6> request(
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5, p6 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                arg3,
                arg4,
                arg5,
                arg6,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5, p6 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6) } ?: true
        }

        context.presage { i, p1, p2, p3, p4, p5, p6 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
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
        )

        ConiumEvent.forever(eventType, context)

        context.presage { i, p1, p2, p3, p4, p5, p6, p7 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
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
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5, p6, p7 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
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
        )

        ConiumEvent.forever(eventType, context)

        context.arise { i, p1, p2, p3, p4, p5, p6, p7 ->
            (i as? I)?.let { arising(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
        }

        context.presage { i, p1, p2, p3, p4, p5, p6, p7 ->
            (i as? I)?.let { presaging(it, p1, p2, p3, p4, p5, p6, p7) } ?: true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, R : Any> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arising: ParameterSelective1<R, I>
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any ->
            result = (i as? I)?.let {
                arising(i)
            } ?: defaultResult
            true
        }

        return context
    }


    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, R : Any, P1> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<R, I, P1>
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1 ->
            result = (i as? I)?.let {
                arising(i, p1)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, R : Any, P1, P2> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<R, I, P1, P2>
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2 ->
            result = (i as? I)?.let {
                arising(i, p1, p2)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, R : Any, P1, P2, P3> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<R, I, P1, P2, P3>
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2, p3: P3 ->
            result = (i as? I)?.let {
                arising(i, p1, p2, p3)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <I : Any, R : Any, P1, P2, P3, P4> export(
        name: String,
        defaultResult: R,
        eventType: ConiumEventType<I>,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<R, I, P1, P2, P3, P4>
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
            result = (i as? I)?.let {
                arising(i, p1, p2, p3, p4)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
            result = (i as? I)?.let {
                arising(i, p1, p2, p3, p4, p5)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
            result = (i as? I)?.let {
                arising(i, p1, p2, p3, p4, p5, p6)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
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
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        var result: R = defaultResult

        Conium.scriptManager!!.export(name, context) { result }

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
            result = (i as? I)?.let {
                arising(i, p1, p2, p3, p4, p5, p6, p7)
            } ?: defaultResult
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <R : Any> unnamed(
        arising: ParameterSelective1<R, Any>
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any ->
            i.let(arising::arise)
            true
        }

        return context
    }


    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1> unnamed(
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Unit, Any, P1>
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1 ->
            arising(i, p1)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Unit, Any, P1, P2>
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2 ->
            arising(i, p1, p2)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Unit, Any, P1, P2, P3>
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2, p3: P3 ->
            arising(i, p1, p2, p3)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Unit, Any, P1, P2, P3, P4>
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4 ->
            arising(i, p1, p2, p3, p4)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Unit, Any, P1, P2, P3, P4, P5>
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5 ->
            arising(i, p1, p2, p3, p4, p5)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Unit, Any, P1, P2, P3, P4, P5, P6>
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6 ->
            arising(i, p1, p2, p3, p4, p5, p6)
            true
        }

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6, P7> unnamed(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Unit, Any, P1, P2, P3, P4, P5, P6, P7>
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(arg1, arg2, arg3, arg4, arg5, arg6, arg7, true).lifecycle(DynamicArgsLifecycle.UNNAMED)
        )

        context.arise { i: Any, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7 ->
            arising(i, p1, p2, p3, p4, p5, p6, p7)
            true
        }

        return context
    }
}
