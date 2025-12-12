package com.github.cao.awa.conium.inactive.event.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType

class ConiumInactiveEventType: ConiumNoCancelableEventType<Unit, ConiumEmptyEventMetadata, Unit, ConiumEmptyEventMetadata>(
    "inactive",
    "Unit",
    ConiumEvent.Companion::inactive
) {
}