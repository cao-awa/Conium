package com.github.cao.awa.conium.event.trigger

import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.ParameterSelective

data class ConiumEventTrigger<I: Any, M: ConiumEventMetadata<I>>(
    val metadataCallback: M.()-> Unit,
    val targetIdentity: (I?) -> Boolean,
    val alwaysCallback: Boolean = false
) {
    fun identity(context: ConiumArisingEventContext<I, out ParameterSelective>): Boolean {
        return this.targetIdentity(context.identity.doCast())
    }

    fun callback(metadata: M) {
        this.metadataCallback(metadata)
    }

    fun shouldAlwaysCallback(): Boolean {
        return this.alwaysCallback
    }
}
