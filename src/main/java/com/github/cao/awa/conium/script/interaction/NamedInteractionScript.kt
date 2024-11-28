package com.github.cao.awa.conium.script.interaction

import com.github.cao.awa.conium.event.context.ConiumEventContext

@JvmRecord
data class NamedInteractionScript<R>(val name: String, val context: ConiumEventContext<*>, val result: (Any) -> R)