package com.github.cao.awa.conium.blockentity.event.chest.closed.type

import com.github.cao.awa.conium.blockentity.event.chest.closed.metadata.ConiumChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumChestClosedEventType: ConiumNoCancelableEventType<Block, ConiumChestClosedEventMetadata, Unit, ConiumInactiveEventMetadata >(
    "chest_closed",
    "Block",
    ConiumEvent.Companion::chestClosed
)