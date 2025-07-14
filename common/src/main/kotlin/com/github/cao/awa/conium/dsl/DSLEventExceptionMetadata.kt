package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

class DSLEventExceptionMetadata<I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>>(
    val exception: Throwable,
    val metadata: M
): DSLEventMetadata<I, M, T>() {

}