package com.github.cao.awa.conium.random.event.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.random.event.metadata.ConiumRandomEventMetadata
import net.minecraft.util.Unit

class ConiumRandomEventType: ConiumCancelableEventType<Unit, ConiumRandomEventMetadata, Unit, ConiumRandomEventMetadata>(
    "random",
    "Unit",
    ConiumEvent.Companion::random
) {
}