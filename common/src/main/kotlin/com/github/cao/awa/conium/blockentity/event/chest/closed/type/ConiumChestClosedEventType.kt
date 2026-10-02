package com.github.cao.awa.conium.blockentity.event.chest.closed.type
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.blockentity.event.chest.closed.metadata.ConiumChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.chunk.LevelChunk

class ConiumChestClosedEventType: ConiumNoCancelableEventType<Block, ConiumChestClosedEventMetadata, Unit, ConiumInactiveEventMetadata >(
    "chest_closed",
    "Block",
    ConiumEvent.Companion::chestClosed
)