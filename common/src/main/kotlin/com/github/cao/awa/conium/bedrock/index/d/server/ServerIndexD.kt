package com.github.cao.awa.conium.bedrock.index.d.server

import com.github.cao.awa.conium.bedrock.index.d.IndexD
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Consumer

class ServerIndexD : IndexD() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ServerIndexD")
        private val mappings: Map<String, String> = CollectionFactor.hashMap<String, String>().also { mappings: MutableMap<String, String> ->
            mappings["system"] = "${BedrockEventContext::class.qualifiedName}.Companion.system"
        }
    }

    override fun forName(refName: String, action: Consumer<String>) {
        val ref: String? = mappings[refName]
        if (ref != null) {
            action.accept(ref)
        } else {
            LOGGER.warn("The reference name '{}' are not importable or not found in conium ServerIndexD(@minecraft/server), ignored", refName)
        }
    }
}
