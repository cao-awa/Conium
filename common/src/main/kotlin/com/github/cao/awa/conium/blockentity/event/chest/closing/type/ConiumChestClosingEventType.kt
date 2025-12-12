package com.github.cao.awa.conium.blockentity.event.chest.closing.type

import com.github.cao.awa.conium.blockentity.event.chest.closed.metadata.ConiumChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.closing.metadata.ConiumChestClosingEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.block.Block

class ConiumChestClosingEventType: ConiumCancelableEventType<Block, ConiumChestClosingEventMetadata, Block, ConiumChestClosedEventMetadata>(
    "chest_closing",
    "Block",
    ConiumEvent.Companion::chestClosing
)