package com.github.cao.awa.conium.block.event.fluid.schedule.ticked.type
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.fluid.schedule.ticked.metadata.ConiumFluidScheduleTickedEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.chunk.LevelChunk

class ConiumFluidScheduleTickedEventType: ConiumNoCancelableEventType<Fluid, ConiumFluidScheduleTickedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "fluid_schedule_ticked",
    "Block",
    ConiumEvent.Companion::fluidScheduleTicked
)