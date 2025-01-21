package com.github.cao.awa.conium.server

import com.github.cao.awa.conium.server.datapack.ConiumContentDatapack
import com.github.cao.awa.conium.server.datapack.ConiumServerLoadDatapacks
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumDedicatedServer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumServer")

        @JvmField
        var loadDatapacks: ConiumServerLoadDatapacks = ConiumServerLoadDatapacks()
        val initialized: Boolean get() = _initialized
        private var _initialized: Boolean = false
            set(value) {
                if (!field && value) {
                    field = true
                    LOGGER.info("Conium dedicated server initialized")
                } else {
                    throw IllegalStateException("The dedicated server already initialized, cannot set 'initialized' to true again!")
                }
            }

        @JvmStatic
        fun onLoadData(datapackIdentifier: Identifier, resourceIdentifier: Identifier, content: String) {
            println("Server loaded '$resourceIdentifier' in '$datapackIdentifier': $content")
            synchronized(this) {
                try {
                    this.loadDatapacks.datapacks.computeIfAbsent(
                        datapackIdentifier,
                        ::ConiumContentDatapack
                    ).contents[resourceIdentifier] = content
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        @JvmStatic
        fun onReload() {
            this.loadDatapacks = ConiumServerLoadDatapacks()
        }

        @JvmStatic
        fun onInitialized() {
            this._initialized = true
        }

        @JvmStatic
        fun isInitialized(): Boolean {
            return this.initialized
        }
    }
}
