package com.github.cao.awa.conium.event.trigger

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata

data class ConiumEventTrigger<M: ConiumEventMetadata>(
    val callback: (M) -> Unit,
    val targetIdentity: (Any?) -> Boolean
)
