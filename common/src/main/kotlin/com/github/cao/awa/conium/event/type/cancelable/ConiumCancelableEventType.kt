package com.github.cao.awa.conium.event.type.cancelable

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

open class ConiumCancelableEventType<I : Any, M: ConiumEventMetadata<I, M>, C: Any, N: ConiumEventMetadata<C, N>>(
    name: String,
    identityDescription: String,
    instance: () -> ConiumEvent<I, M, *, *>
): ConiumEventType<I, M, C, N>(
    name,
    identityDescription,
    instance
)