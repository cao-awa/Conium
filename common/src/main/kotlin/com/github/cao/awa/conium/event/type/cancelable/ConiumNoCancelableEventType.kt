package com.github.cao.awa.conium.event.type.cancelable

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

class ConiumNoCancelableEventType<I : Any, M: ConiumEventMetadata<I>, C: Any, N: ConiumEventMetadata<C>>(name: String, identityDescription: String): ConiumEventType<I, M, C, N>(name, identityDescription) {
}