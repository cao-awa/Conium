package com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.type

import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumTrappedChestClosedEventType: ConiumNoCancelableEventType<Block, ConiumTrappedChestClosedEventMetadata, Unit, ConiumInactiveEventMetadata >(
    "trapped_chest_closed",
    "Block",
    ConiumEvent.Companion::trappedChestClosed
)