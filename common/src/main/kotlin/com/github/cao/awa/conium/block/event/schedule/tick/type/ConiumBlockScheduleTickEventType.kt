package com.github.cao.awa.conium.block.event.schedule.tick.type

import com.github.cao.awa.conium.block.event.schedule.tick.metadata.ConiumBlockScheduleTickEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block

class ConiumBlockScheduleTickEventType: ConiumCancelableEventType<Block, ConiumBlockScheduleTickEventMetadata, Block, ConiumBlockScheduleTickedEventMetadata>(
    "block_schedule_tick",
    "Block",
    ConiumEvent.Companion::blockScheduleTick
)