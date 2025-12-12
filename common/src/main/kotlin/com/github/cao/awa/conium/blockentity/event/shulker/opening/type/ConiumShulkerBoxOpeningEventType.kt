package com.github.cao.awa.conium.blockentity.event.shulker.opening.type

import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.block.Block

class ConiumShulkerBoxOpeningEventType: ConiumCancelableEventType<Block, ConiumShulkerBoxOpeningEventMetadata, Block, ConiumShulkerBoxOpenedEventMetadata>(
    "shulker_box_opening",
    "Block",
    ConiumEvent.Companion::shulkerBoxOpening
)