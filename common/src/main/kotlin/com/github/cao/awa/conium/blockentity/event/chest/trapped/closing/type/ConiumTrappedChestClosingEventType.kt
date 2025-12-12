package com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.type

import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumTrappedChestClosingEventType: ConiumCancelableEventType<Block, ConiumTrappedChestClosingEventMetadata, Block, ConiumTrappedChestClosedEventMetadata>(
    "trapped_chest_closing",
    "Block",
    ConiumEvent.Companion::trappedChestClosing
)