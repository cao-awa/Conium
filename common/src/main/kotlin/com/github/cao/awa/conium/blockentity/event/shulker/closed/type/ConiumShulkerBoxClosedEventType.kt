package com.github.cao.awa.conium.blockentity.event.shulker.closed.type

import com.github.cao.awa.conium.blockentity.event.shulker.closed.metadata.ConiumShulkerBoxClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.closing.metadata.ConiumShulkerBoxClosingEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.mapping.yarn.Block
import net.minecraft.world.chunk.WorldChunk

class ConiumShulkerBoxClosedEventType: ConiumNoCancelableEventType<Block, ConiumShulkerBoxClosedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "shulker_box_closed",
    "Block",
    ConiumEvent.Companion::shulkerBoxClosed
)