package com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.type

import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opened.metadata.ConiumTrappedChestOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.metadata.ConiumTrappedChestOpeningEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumTrappedChestOpeningEventType: ConiumCancelableEventType<Block, ConiumTrappedChestOpeningEventMetadata, Block, ConiumTrappedChestOpenedEventMetadata>(
    "trapped_chest_opening",
    "Block",
    ConiumEvent.Companion::trappedChestOpening
)