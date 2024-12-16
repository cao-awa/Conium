package com.github.cao.awa.conium.bedrock.server

import com.github.cao.awa.conium.bedrock.IndexD
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.sinuatum.manipulate.QuickManipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Consumer

class ServerIndexD : IndexD() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ServerIndexD")
        private val mappings: Map<String, String> = QuickManipulate.operation(CollectionFactor.hashMap()) { mappings: MutableMap<String, String> ->
            mappings["system"] = "${BedrockEventContext::class.qualifiedName}.Companion.system"
        }
    }

    override fun forName(refName: String, action: Consumer<String>) {
        mappings[refName]?.also { ref: String ->
            action.accept(ref)
        } ?: {
            LOGGER.warn("The reference name '{}' are not found in conium ServerIndexD(@minecraft/server)", refName)
        }
    }
}
