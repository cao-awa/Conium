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
    fun request(eventType: ConiumEventType): ConiumEventContext<ParameterSelective1<Boolean, Any>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean>().lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>
    ): ConiumEventContext<ParameterSelective2<Boolean, Any, P1>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1>(arg1).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>
    ): ConiumEventContext<ParameterSelective3<Boolean, Any, P1, P2>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2>(arg1, arg2).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>
    ): ConiumEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2, P3>(arg1, arg2, arg3).lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        return context
    }

    @JvmStatic
    @SuppressWarnings("UNCHECKED_CAST")
    fun <P1, P2, P3, P4> request(
        eventType: ConiumEventType,
        arg1: DynamicArgType<P1>,
        arg2: DynamicArgType<P2>,
        arg3: DynamicArgType<P3>,
        arg4: DynamicArgType<P4>
    ): ConiumEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2, P3, P4>(arg1, arg2, arg3, arg4)
                .lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

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
        arg5: DynamicArgType<P5>
    ): ConiumEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2, P3, P4, P5>(arg1, arg2, arg3, arg4, arg5)
                .lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

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
        arg6: DynamicArgType<P6>
    ): ConiumEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2, P3, P4, P5, P6>(arg1, arg2, arg3, arg4, arg5, arg6)
                .lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

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
        arg7: DynamicArgType<P7>
    ): ConiumEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> {
        val context = ConiumEventContext(
            DynamicArgsBuilder.force<Boolean, P1, P2, P3, P4, P5, P6, P7>(arg1, arg2, arg3, arg4, arg5, arg6, arg7)
                .lifecycle(DynamicArgsLifecycle.FOREVER)
        )

        ConiumEvent.forever(eventType, context)

        return context
    }
}
