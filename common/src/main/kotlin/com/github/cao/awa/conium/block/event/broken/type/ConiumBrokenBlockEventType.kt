package com.github.cao.awa.conium.block.event.broken.type

import com.github.cao.awa.conium.block.event.broken.metadata.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.block.event.placed.metadata.ConiumPlacedBlockEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumBrokenBlockEventType: ConiumNoCancelableEventType<Block, ConiumBrokenBlockEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "broken_block",
    "Block",
    ConiumEvent.Companion::brokenBlock
)