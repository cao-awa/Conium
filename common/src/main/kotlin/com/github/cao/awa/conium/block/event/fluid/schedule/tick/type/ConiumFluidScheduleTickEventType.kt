package com.github.cao.awa.conium.block.event.fluid.schedule.tick.type

import com.github.cao.awa.conium.block.event.fluid.schedule.tick.metadata.ConiumFluidScheduleTickEventMetadata
import com.github.cao.awa.conium.block.event.fluid.schedule.ticked.metadata.ConiumFluidScheduleTickedEventMetadata
import com.github.cao.awa.conium.block.event.fluid.schedule.ticked.type.ConiumFluidScheduleTickedEventType
import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.fluid.Fluid
import net.minecraft.world.chunk.WorldChunk

class ConiumFluidScheduleTickEventType: ConiumCancelableEventType<Fluid, ConiumFluidScheduleTickEventMetadata, Fluid, ConiumFluidScheduleTickedEventMetadata>(
    "fluid_schedule_tick",
    "Block",
    ConiumEvent.Companion::fluidScheduleTick
)