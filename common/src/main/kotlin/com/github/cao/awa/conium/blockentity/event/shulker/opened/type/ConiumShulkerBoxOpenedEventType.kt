package com.github.cao.awa.conium.blockentity.event.shulker.opened.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block

class ConiumShulkerBoxOpenedEventType: ConiumNoCancelableEventType<Block, ConiumShulkerBoxOpenedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "shulker_box_opened",
    "Block",
    ConiumEvent.Companion::shulkerBoxOpened
)