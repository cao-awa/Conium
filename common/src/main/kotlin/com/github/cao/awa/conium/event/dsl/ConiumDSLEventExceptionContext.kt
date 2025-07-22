package com.github.cao.awa.conium.event.dsl

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

class ConiumDSLEventExceptionContext<I: Any, M: ConiumEventMetadata<I>, T: ConiumEventType<I, M>>(
    val exception: Throwable,
    val metadata: M
): ConiumDSLEventContext<I, M, T>()