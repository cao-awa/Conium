package com.github.cao.awa.conium.blockentity.event.chest.opened.type

import com.github.cao.awa.conium.blockentity.event.chest.opened.metadata.ConiumChestOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opened.metadata.ConiumTrappedChestOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumChestOpenedEventType: ConiumNoCancelableEventType<Block, ConiumChestOpenedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "chest_opened",
    "Block",
    ConiumEvent.Companion::chestOpened
)