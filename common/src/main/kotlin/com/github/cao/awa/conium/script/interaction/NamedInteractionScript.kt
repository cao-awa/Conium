package com.github.cao.awa.conium.script.interaction

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext

@JvmRecord
data class NamedInteractionScript<R>(val name: String, val context: ConiumArisingEventContext<*>, val result: (Any) -> R)