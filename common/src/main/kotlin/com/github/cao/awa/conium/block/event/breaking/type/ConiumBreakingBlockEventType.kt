package com.github.cao.awa.conium.block.event.breaking.type

import com.github.cao.awa.conium.block.event.breaking.metadata.ConiumBreakingBlockEventMetadata
import com.github.cao.awa.conium.block.event.breaks.metadata.ConiumBreakBlockEventMetadata
import com.github.cao.awa.conium.block.event.broken.metadata.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.block.event.placed.metadata.ConiumPlacedBlockEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumBreakingBlockEventType: ConiumCancelableEventType<Block, ConiumBreakingBlockEventMetadata, Block, ConiumBreakBlockEventMetadata>(
    "breaking_block",
    "Block",
    ConiumEvent.Companion::breakingBlock
)