package com.github.cao.awa.conium.bedrock

import com.github.cao.awa.conium.bedrock.server.ServerIndexD
import com.github.cao.awa.sinuatum.manipulate.QuickManipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Consumer

abstract class IndexD {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("IndexD")
        private val packages: Map<String, IndexD> = QuickManipulate.operation(CollectionFactor.hashMap()) { packages: MutableMap<String, IndexD> ->
            packages["@minecraft/server"] = ServerIndexD()
        }

        @JvmStatic
        fun tryImport(packageName: String, refs: Collection<String>, action: Consumer<String>) {
            packages[packageName]?.also { indexD: IndexD ->
                refs.distinct().forEach { ref: String ->
                    indexD.forName(ref, action)
                }
            } ?: {
                LOGGER.warn("The package '{}' are not found in conium IndexD", packageName)
            }
        }
    }

    abstract fun forName(refName: String, action: Consumer<String>)
}
