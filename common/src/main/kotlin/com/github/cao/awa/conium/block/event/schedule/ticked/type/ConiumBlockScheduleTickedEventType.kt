package com.github.cao.awa.conium.block.event.schedule.ticked.type
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.chunk.LevelChunk

class ConiumBlockScheduleTickedEventType: ConiumNoCancelableEventType<Block, ConiumBlockScheduleTickedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "block_schedule_ticked",
    "Block",
    ConiumEvent.Companion::blockScheduleTicked
)