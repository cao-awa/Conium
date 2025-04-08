@file:Suppress("unused")

package com.github.cao.awa.conium.script.index

import com.github.cao.awa.conium.Conium

// Mappings.
import com.github.cao.awa.conium.mapping.yarn.*

// Event.
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder

// Event arg types.
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgsBuilder
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder

// Receptacles.
import com.github.cao.awa.catheter.receptacle.Receptacle
import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.catheter.receptacle.LongReceptacle
import com.github.cao.awa.catheter.receptacle.DoubleReceptacle
import com.github.cao.awa.catheter.receptacle.ByteReceptacle
import com.github.cao.awa.catheter.receptacle.BooleanReceptacle

// Export info.
import com.github.cao.awa.conium.script.ScriptExport
import com.github.cao.awa.conium.script.ScriptExport.Companion.accessExportedField

// Manipulate.
import com.github.cao.awa.sinuatum.manipulate.Manipulate

// ParameterSelective interfaces.
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.parameter.ParameterSelective2
import com.github.cao.awa.conium.parameter.ParameterSelective3
import com.github.cao.awa.conium.parameter.ParameterSelective4
import com.github.cao.awa.conium.parameter.ParameterSelective5
import com.github.cao.awa.conium.parameter.ParameterSelective6
import com.github.cao.awa.conium.parameter.ParameterSelective7
import com.github.cao.awa.conium.parameter.ParameterSelective8

// Conium block entity.
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.event.context.ConiumArgOnlyEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext

// Raycast.
import com.github.cao.awa.conium.raycast.ConiumRaycast

// Entity.
import com.github.cao.awa.conium.kotlin.extent.entity.registerEntity

// Event.
typealias ConiumEvent<P, M> = ConiumEvent<P, M>
typealias ConiumEventType<I> = ConiumEventType<I>
typealias ConiumEventContext = ConiumEventContext
typealias ConiumArisingEventContext<P> = ConiumArisingEventContext<P>
typealias ConiumArgOnlyEventContext = ConiumArgOnlyEventContext
typealias ConiumEventContextBuilder = ConiumEventContextBuilder

fun register(identifier: Identifier, itemProvider: (ItemSettings) -> Item): Item {
    return Conium.coniumItemManager!!.register(identifier, itemProvider)
}

fun register(
    identifier: Identifier,
    blockProvider: (BlockSettings) -> Block,
    settingsProvider: ((ItemSettings) -> Unit)? = null
): Block {
    return Conium.coniumBlockManager!!.register(identifier, blockProvider, settingsProvider)
}

fun <T: Entity> register(identifier: Identifier, entityType: EntityTypeBuilder<T>): EntityType<T> {
    return registerEntity(identifier, entityType)
}

fun <I : Any> preRequest(
    eventType: ConiumEventType<I>,
    presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { true }
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.preRequest(eventType, presaging)

fun <I : Any> preRequestNr(
    eventType: ConiumEventType<I>,
    presaging: ParameterSelective1<Unit, I> = ParameterSelective1 { }
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.preRequestNr(eventType, presaging)

fun <I : Any> request(
    eventType: ConiumEventType<I>,
    arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { true }
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.request(eventType, arising)

fun <I : Any> requestNr(
    eventType: ConiumEventType<I>,
    arising: ParameterSelective1<Unit, I> = ParameterSelective1 { },
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.requestNr(eventType, arising)

fun <I : Any> request(
    eventType: ConiumEventType<I>,
    arising: ParameterSelective1<Boolean, I> = ParameterSelective1 { true },
    presaging: ParameterSelective1<Boolean, I> = ParameterSelective1 { true },
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.request(eventType, arising, presaging)

fun <I : Any> requestNr(
    eventType: ConiumEventType<I>,
    arising: ParameterSelective1<Unit, I> = ParameterSelective1 { },
    presaging: ParameterSelective1<Unit, I> = ParameterSelective1 { },
): ConiumArisingEventContext<*> = ConiumEventContextBuilder.requestNr(eventType, arising, presaging)

fun <I : Any, P1> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.preRequest(eventType, arg1, presaging)

fun <I : Any, P1> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    presaging: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.requestNr(eventType, arg1, presaging)

fun <I : Any, P1> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.request(eventType, arg1, arising)

fun <I : Any, P1> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arising: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arising)

fun <I : Any, P1> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arising: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true },
    presaging: ParameterSelective2<Boolean, I, P1> = ParameterSelective2 { _, _ -> true }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.request(eventType, arg1, arising, presaging)

fun <I : Any, P1> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arising: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> },
    presaging: ParameterSelective2<Unit, I, P1> = ParameterSelective2 { _, _ -> }
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arising, presaging)

fun <I : Any, P1, P2> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, presaging)

fun <I : Any, P1, P2> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    presaging: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, presaging)

fun <I : Any, P1, P2> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arising)

fun <I : Any, P1, P2> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arising: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arising)

fun <I : Any, P1, P2> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arising: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
    presaging: ParameterSelective3<Boolean, I, P1, P2> = ParameterSelective3 { _, _, _ -> true },
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arising, presaging)

fun <I : Any, P1, P2> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arising: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> },
    presaging: ParameterSelective3<Unit, I, P1, P2> = ParameterSelective3 { _, _, _ -> },
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arising, presaging)

fun <I : Any, P1, P2, P3> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, arg3, presaging)

fun <I : Any, P1, P2, P3> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    presaging: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, arg3, presaging)

fun <I : Any, P1, P2, P3> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arising)

fun <I : Any, P1, P2, P3> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arising: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arising)

fun <I : Any, P1, P2, P3> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arising: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true },
    presaging: ParameterSelective4<Boolean, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arising, presaging)

fun <I : Any, P1, P2, P3> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arising: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> },
    presaging: ParameterSelective4<Unit, I, P1, P2, P3> = ParameterSelective4 { _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arising, presaging)

fun <I : Any, P1, P2, P3, P4> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, arg3, arg4, presaging)

fun <I : Any, P1, P2, P3, P4> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    presaging: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, arg3, arg4, presaging)

fun <I : Any, P1, P2, P3, P4> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arising)

fun <I : Any, P1, P2, P3, P4> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arising: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arising)

fun <I : Any, P1, P2, P3, P4> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arising: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true },
    presaging: ParameterSelective5<Boolean, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arising, presaging)

fun <I : Any, P1, P2, P3, P4> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arising: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> },
    presaging: ParameterSelective5<Unit, I, P1, P2, P3, P4> = ParameterSelective5 { _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arising, presaging)

fun <I : Any, P1, P2, P3, P4, P5> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, arg3, arg4, arg5, presaging)

fun <I : Any, P1, P2, P3, P4, P5> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    presaging: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, arg3, arg4, arg5, presaging)

fun <I : Any, P1, P2, P3, P4, P5> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arising)

fun <I : Any, P1, P2, P3, P4, P5> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arising: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arising)

fun <I : Any, P1, P2, P3, P4, P5> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arising: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true },
    presaging: ParameterSelective6<Boolean, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arising, presaging)

fun <I : Any, P1, P2, P3, P4, P5> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arising: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> },
    presaging: ParameterSelective6<Unit, I, P1, P2, P3, P4, P5> = ParameterSelective6 { _, _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arising, presaging)

fun <I : Any, P1, P2, P3, P4, P5, P6> preRequest(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    presaging: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, arg3, arg4, arg5, arg6, presaging)

fun <I : Any, P1, P2, P3, P4, P5, P6> preRequestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    presaging: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, presaging)

fun <I : Any, P1, P2, P3, P4, P5, P6> request(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    arising: ParameterSelective7<Boolean, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> true }
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arising)

fun <I : Any, P1, P2, P3, P4, P5, P6> requestNr(
    eventType: ConiumEventType<I>,
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    arising: ParameterSelective7<Unit, I, P1, P2, P3, P4, P5, P6> = ParameterSelective7 { _, _, _, _, _, _, _ -> }
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arising)

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
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arising, presaging)

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
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arising, presaging)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.preRequest(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, presaging)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.preRequestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, presaging)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arising)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arising)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.request(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arising, presaging)

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
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.requestNr(eventType, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arising, presaging)

fun <R : Any> unnamed(arising: ParameterSelective1<R, Any>): ConiumArisingEventContext<ParameterSelective1<Boolean, Any>> = ConiumEventContextBuilder.unnamed(arising)

fun <P1> unnamed(
    arg1: DynamicArgType<P1>,
    arising: ParameterSelective2<Unit, Any, P1>
): ConiumArisingEventContext<ParameterSelective2<Boolean, Any, P1>> = ConiumEventContextBuilder.unnamed(arg1, arising)

fun <P1, P2> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arising: ParameterSelective3<Unit, Any, P1, P2>
): ConiumArisingEventContext<ParameterSelective3<Boolean, Any, P1, P2>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arising)

fun <P1, P2, P3> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arising: ParameterSelective4<Unit, Any, P1, P2, P3>
): ConiumArisingEventContext<ParameterSelective4<Boolean, Any, P1, P2, P3>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arg3, arising)

fun <P1, P2, P3, P4> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arising: ParameterSelective5<Unit, Any, P1, P2, P3, P4>
): ConiumArisingEventContext<ParameterSelective5<Boolean, Any, P1, P2, P3, P4>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arg3, arg4, arising)

fun <P1, P2, P3, P4, P5> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arising: ParameterSelective6<Unit, Any, P1, P2, P3, P4, P5>
): ConiumArisingEventContext<ParameterSelective6<Boolean, Any, P1, P2, P3, P4, P5>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arg3, arg4, arg5, arising)

fun <P1, P2, P3, P4, P5, P6> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    arising: ParameterSelective7<Unit, Any, P1, P2, P3, P4, P5, P6>
): ConiumArisingEventContext<ParameterSelective7<Boolean, Any, P1, P2, P3, P4, P5, P6>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arg3, arg4, arg5, arg6, arising)

fun <P1, P2, P3, P4, P5, P6, P7> unnamed(
    arg1: DynamicArgType<P1>,
    arg2: DynamicArgType<P2>,
    arg3: DynamicArgType<P3>,
    arg4: DynamicArgType<P4>,
    arg5: DynamicArgType<P5>,
    arg6: DynamicArgType<P6>,
    arg7: DynamicArgType<P7>,
    arising: ParameterSelective8<Unit, Any, P1, P2, P3, P4, P5, P6, P7>
): ConiumArisingEventContext<ParameterSelective8<Boolean, Any, P1, P2, P3, P4, P5, P6, P7>> = ConiumEventContextBuilder.unnamed(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arising)

val SERVER_TICK = ConiumEventType.SERVER_TICK
val SERVER_RANDOM = ConiumEventType.SERVER_RANDOM
val SERVER_TICK_TAIL = ConiumEventType.SERVER_TICK_TAIL
val ITEM_USE_ON_BLOCK = ConiumEventType.ITEM_USE_ON_BLOCK
val ITEM_USED_ON_BLOCK = ConiumEventType.ITEM_USED_ON_BLOCK
val BREAKING_BLOCK = ConiumEventType.BREAKING_BLOCK
val BREAK_BLOCK = ConiumEventType.BREAK_BLOCK
val BROKEN_BLOCK = ConiumEventType.BROKEN_BLOCK
val PLACE_BLOCK = ConiumEventType.PLACE_BLOCK
val PLACED_BLOCK = ConiumEventType.PLACED_BLOCK
val USE_BLOCK = ConiumEventType.USE_BLOCK
val USED_BLOCK = ConiumEventType.USED_BLOCK
val ENTITY_TICK = ConiumEventType.ENTITY_TICK
val ENTITY_TICKED = ConiumEventType.ENTITY_TICKED
val ENTITY_DAMAGE = ConiumEventType.ENTITY_DAMAGE
val ENTITY_DAMAGED = ConiumEventType.ENTITY_DAMAGED
val ENTITY_DIE = ConiumEventType.ENTITY_DIE
val ENTITY_DEAD = ConiumEventType.ENTITY_DEAD
val ENTITY_TRY_SLEEP = ConiumEventType.ENTITY_TRY_SLEEP
val ENTITY_SLEEP = ConiumEventType.ENTITY_SLEEP
val ENTITY_WAKE_UP = ConiumEventType.ENTITY_WAKE_UP
val ENTITY_WAKED_UP = ConiumEventType.ENTITY_WAKED_UP
val ENTITY_SPRINT = ConiumEventType.ENTITY_SPRINT
val ENTITY_SPRINTING = ConiumEventType.ENTITY_SPRINTING
val ENTITY_STOP_SPRINT = ConiumEventType.ENTITY_STOP_SPRINT
val ENTITY_ON_FIRE = ConiumEventType.ENTITY_ON_FIRE
val ENTITY_EXTINGUISH_FIRE = ConiumEventType.ENTITY_EXTINGUISH_FIRE
val ENTITY_EXTINGUISHED_FIRE = ConiumEventType.ENTITY_EXTINGUISHED_FIRE
val FLUID_SCHEDULE_TICK = ConiumEventType.FLUID_SCHEDULE_TICK
val FLUID_SCHEDULE_TICKED = ConiumEventType.FLUID_SCHEDULE_TICKED
val BLOCK_SCHEDULE_TICK = ConiumEventType.BLOCK_SCHEDULE_TICK
val BLOCK_SCHEDULE_TICKED = ConiumEventType.BLOCK_SCHEDULE_TICKED
val SHULKER_BOX_OPENING = ConiumEventType.SHULKER_BOX_OPENING
val SHULKER_BOX_OPENED = ConiumEventType.SHULKER_BOX_OPENED
val SHULKER_BOX_CLOSING = ConiumEventType.SHULKER_BOX_CLOSING
val SHULKER_BOX_CLOSED = ConiumEventType.SHULKER_BOX_CLOSED
val CHEST_OPENING = ConiumEventType.CHEST_OPENING
val CHEST_OPENED = ConiumEventType.CHEST_OPENED
val CHEST_CLOSING = ConiumEventType.CHEST_CLOSING
val CHEST_CLOSED = ConiumEventType.CHEST_CLOSED
val TRAPPED_CHEST_OPENING = ConiumEventType.TRAPPED_CHEST_OPENING
val TRAPPED_CHEST_OPENED = ConiumEventType.TRAPPED_CHEST_OPENED
val TRAPPED_CHEST_CLOSING = ConiumEventType.TRAPPED_CHEST_CLOSING
val TRAPPED_CHEST_CLOSED = ConiumEventType.TRAPPED_CHEST_CLOSED
val ITEM_USE = ConiumEventType.ITEM_USE
val ITEM_USED = ConiumEventType.ITEM_USED
val ITEM_USE_ON_ENTITY = ConiumEventType.ITEM_USE_ON_ENTITY
val ITEM_USED_ON_ENTITY = ConiumEventType.ITEM_USED_ON_ENTITY
val ITEM_USAGE_TICK = ConiumEventType.ITEM_USAGE_TICK
val ITEM_USAGE_TICKED = ConiumEventType.ITEM_USAGE_TICKED
val ITEM_INVENTORY_TICK = ConiumEventType.ITEM_INVENTORY_TICK
val ITEM_INVENTORY_TICKED = ConiumEventType.ITEM_INVENTORY_TICKED
val ITEM_STACK_CLICK = ConiumEventType.ITEM_STACK_CLICK
val ITEM_STACK_CLICKED = ConiumEventType.ITEM_STACK_CLICKED

// Event arg types.
typealias DynamicArgType<T> = DynamicArgType<T>
typealias DynamicArgsBuilder = DynamicArgsBuilder
typealias DynamicArgTypeBuilder = DynamicArgTypeBuilder

val INT: DynamicArgType<Int> = ConiumEventArgTypes.INT
val LONG: DynamicArgType<Long> = ConiumEventArgTypes.LONG
val FLOAT: DynamicArgType<Float> = ConiumEventArgTypes.FLOAT
val DOUBLE: DynamicArgType<Double> = ConiumEventArgTypes.DOUBLE
val RANDOM: DynamicArgType<Random> = ConiumEventArgTypes.RANDOM
val SERVER: DynamicArgType<MinecraftServer> = ConiumEventArgTypes.SERVER
val SCHEDULED_TICK_VIEW: DynamicArgType<ScheduledTickView> = ConiumEventArgTypes.SCHEDULED_TICK_VIEW
val WORLD: DynamicArgType<World> = ConiumEventArgTypes.WORLD
val CLIENT_WORLD: DynamicArgType<ClientWorld> = ConiumEventArgTypes.CLIENT_WORLD
val SERVER_WORLD: DynamicArgType<ServerWorld> = ConiumEventArgTypes.SERVER_WORLD
val ENTITY: DynamicArgType<Entity> = ConiumEventArgTypes.ENTITY
val LIVING_ENTITY: DynamicArgType<LivingEntity> = ConiumEventArgTypes.LIVING_ENTITY
val PLAYER: DynamicArgType<PlayerEntity> = ConiumEventArgTypes.PLAYER
val CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity> = ConiumEventArgTypes.CLIENT_PLAYER
val SERVER_PLAYER: DynamicArgType<ServerPlayerEntity> = ConiumEventArgTypes.SERVER_PLAYER
val BLOCK_POS: DynamicArgType<BlockPos> = ConiumEventArgTypes.BLOCK_POS
val FLUID_STATE: DynamicArgType<FluidState> = ConiumEventArgTypes.FLUID_STATE
val BLOCK_ENTITY: DynamicArgType<BlockEntity> = ConiumEventArgTypes.BLOCK_ENTITY
val C_BLOCK_ENTITY: DynamicArgType<ConiumBlockEntity> = ConiumEventArgTypes.C_BLOCK_ENTITY
val BLOCK_STATE: DynamicArgType<BlockState> = ConiumEventArgTypes.BLOCK_STATE
val ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext> = ConiumEventArgTypes.ITEM_USAGE_CONTEXT
val ITEM_PLACEMENT_CONTEXT: DynamicArgType<ItemPlacementContext> = ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
val ITEM_STACK: DynamicArgType<ItemStack> = ConiumEventArgTypes.ITEM_STACK
val CURSOR_STACK: DynamicArgType<ItemStack> = ConiumEventArgTypes.CURSOR_STACK
val CLICK_TYPE: DynamicArgType<ClickType> = ConiumEventArgTypes.CLICK_TYPE
val SLOT: DynamicArgType<Slot> = ConiumEventArgTypes.SLOT
val SLOT_NUMBER: DynamicArgType<Int> = ConiumEventArgTypes.SLOT_NUMBER
val SELECT_STATUS: DynamicArgType<Boolean> = ConiumEventArgTypes.SELECT_STATUS
val REMAINING_USE_TICKS: DynamicArgType<Int> = ConiumEventArgTypes.REMAINING_USE_TICKS
val HAND: DynamicArgType<Hand> = ConiumEventArgTypes.HAND
val BLOCK_HIT_RESULT: DynamicArgType<BlockHitResult> = ConiumEventArgTypes.BLOCK_HIT_RESULT
val ACTION_RESULT: DynamicArgType<ActionResult> = ConiumEventArgTypes.ACTION_RESULT
val VIEWER_COUNT_MANAGER: DynamicArgType<ViewerCountManager> = ConiumEventArgTypes.VIEWER_COUNT_MANAGER

// Receptacles typealias.
typealias Receptacle<T> = Receptacle<T>
typealias IntegerReceptacle = IntegerReceptacle
typealias LongReceptacle = LongReceptacle
typealias DoubleReceptacle = DoubleReceptacle
typealias ByteReceptacle = ByteReceptacle
typealias BooleanReceptacle = BooleanReceptacle

// Export info.
typealias ScriptExport = ScriptExport

fun <T> accessExportedField(instance: Any, name: String, fieldName: String): T? = accessExportedField(instance, name, fieldName)

// Manipulate.
typealias Manipulate<M> = Manipulate<M>

// ParameterSelective interfaces typealias.
typealias ParameterSelective = ParameterSelective
typealias ParameterSelective0<R> = ParameterSelective0<R>
typealias ParameterSelective1<R, P1> = ParameterSelective1<R, P1>
typealias ParameterSelective2<R, P1, P2> = ParameterSelective2<R, P1, P2>
typealias ParameterSelective3<R, P1, P2, P3> = ParameterSelective3<R, P1, P2, P3>
typealias ParameterSelective4<R, P1, P2, P3, P4> = ParameterSelective4<R, P1, P2, P3, P4>
typealias ParameterSelective5<R, P1, P2, P3, P4, P5> = ParameterSelective5<R, P1, P2, P3, P4, P5>
typealias ParameterSelective6<R, P1, P2, P3, P4, P5, P6> = ParameterSelective6<R, P1, P2, P3, P4, P5, P6>
typealias ParameterSelective7<R, P1, P2, P3, P4, P5, P6, P7> = ParameterSelective7<R, P1, P2, P3, P4, P5, P6, P7>
typealias ParameterSelective8<R, P1, P2, P3, P4, P5, P6, P7, P8> = ParameterSelective8<R, P1, P2, P3, P4, P5, P6, P7, P8>

// Conium block entity.
typealias ConiumBlockEntity = ConiumBlockEntity

// Raycast.
typealias ConiumRaycast = ConiumRaycast