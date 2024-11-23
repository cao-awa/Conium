package com.github.cao.awa.conium.client

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Consumer

class ConiumClient {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumClient")
        private val postedInitializer: MutableList<Consumer<Boolean>> = CollectionFactor.arrayList()
        val initialized: Boolean get() = _initialized
        private var _initialized: Boolean = false
            set(value) {
                if (this.neverInitialized) {
                    throw IllegalStateException("The client already marked never initialized, cannot set initialized state to true")
                }
                if (!field && value) {
                    field = true
                    LOGGER.info("Conium client initialized")

                    postedInitializer.forEach { it.accept(true) }
                } else {
                    throw IllegalStateException("The client already initialized, cannot set 'initialized' to true again!")
                }
            }
        val neverInitialized: Boolean get() = _neverInitialized
        private var _neverInitialized: Boolean = false
            set(value) {
                if (!field && value) {
                    field = true
                    LOGGER.info("Conium client now marked to never initialized")

                    postedInitializer.forEach { it.accept(false) }
                } else {
                    throw IllegalStateException("The client already marked never initialized, cannot set 'neverInitialized' to true again!")
                }
            }

        fun onInitialized() {
            this._initialized = true
        }

        fun willNeverInitialized() {
            this._neverInitialized = true
        }

        fun postInitialize(runnable: Consumer<Boolean>) {
            if (this.initialized || this.neverInitialized) {
                runnable.accept(this.initialized)
            } else {
                this.postedInitializer.add(runnable)
            }
        }
    }
}
