package com.github.cao.awa.conium.event.trigger

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata

data class ConiumEventTrigger<I: Any, M: ConiumEventMetadata<I>>(
    val callback: M.()-> Unit,
    val targetIdentity: (I?) -> Boolean,
    val alwaysCallback: Boolean = false
)
