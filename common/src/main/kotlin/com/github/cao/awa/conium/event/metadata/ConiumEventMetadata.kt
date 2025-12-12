package com.github.cao.awa.conium.event.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext

open class ConiumEventMetadata<I: Any, M: ConiumEventContext<*>>: ConiumEventContext<I>() {
    lateinit var lastContext: M
}