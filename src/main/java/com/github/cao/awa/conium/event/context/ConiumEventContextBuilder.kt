package com.github.cao.awa.conium.event.context

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.DynamicArgType
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
        return ConiumEventContext(DynamicArgsBuilder.force(arg1))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2))
    }

    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2, arg3))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4> requires(
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2, arg3, arg4))
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
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2, arg3, arg4, arg5))
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
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2, arg3, arg4, arg5, arg6))
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
        return ConiumEventContext(DynamicArgsBuilder.force(arg1, arg2, arg3, arg4, arg5, arg6, arg7))
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun request(
        eventType: ConiumEventType,
        arising: ParameterSelective1<Boolean, Any> = ParameterSelective1 { _ -> true }
    ): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(true).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arising: ParameterSelective2<Boolean, Any, P1> = ParameterSelective2 { _, _ -> true }
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arising: ParameterSelective3<Boolean, Any, P1, P2> = ParameterSelective3 { _, _, _ -> true }
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.requires(
                arg1,
                arg2,
                true
            ).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arising: ParameterSelective4<Boolean, Any, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
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

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arising: ParameterSelective5<Boolean, Any, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
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

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arising: ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
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

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arising: ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
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

        context.arise(arising)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4, P5, P6, P7> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>,
        arg5: DynamicArgType<P5>,
        arg6: DynamicArgType<P6>,
        arg7: DynamicArgType<P7>,
        arising: ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective8 { _, _, _, _, _, _, _, _ -> true }
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

        context.arise(arising)

        return context
    }
}
