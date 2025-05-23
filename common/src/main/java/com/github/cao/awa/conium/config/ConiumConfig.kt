package com.github.cao.awa.conium.config

import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.sinuatum.resource.loader.ResourceLoader
import com.github.cao.awa.sinuatum.util.io.IOUtil
import com.google.gson.JsonObject
import net.minecraft.util.JsonHelper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

class ConiumConfig {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumConfig")
        val CONFIG_FILE: File = File("config/conium.json")
        @JvmField
        var debugs: Boolean = true
        @JvmField
        var enableBedrockScript: Boolean = true
        @JvmField
        var sendBugTrace: Boolean = false

        fun makeConfig() {
            LOGGER.info("Processing conium config")

            if (this.CONFIG_FILE.isFile) {
                readConfig()
            } else {
                LOGGER.info("Conium config not found, try create now")

                createConfig()
                readConfig()
            }

            printConfig()
        }

        fun readConfig() {
            JsonHelper.deserialize(IOUtil.read(this.CONFIG_FILE.reader())).let { config: JsonObject ->
                config["enable_debug"].ifBoolean {
                    this.debugs = it
                }

                config["enable_bedrock_script"].ifBoolean {
                    this.enableBedrockScript = it
                }

//                config["send_bug_report"].ifBoolean {
//                    this.sendBugTrace = it
//                }
            }
        }

        fun createConfig() {
            this.CONFIG_FILE.parentFile.mkdirs()

            IOUtil.write(
                this.CONFIG_FILE.writer(),
                IOUtil.read(ResourceLoader.get("assets/conium/config/default.conium.config.json"))
            )
        }

        fun printConfig() {
            if (this.debugs) {
                LOGGER.info("Conium is enabled debug mode, may cause performances reduce")
            }

            if (!this.enableBedrockScript) {
                LOGGER.info("Conium is disabled bedrock script supports by configuration")
            }
//            Not available now
//            if (this.sendBugTrace) {
//                LOGGER.info("Conium bug tracer are enabled, the error trace may send to server")
//            }
        }
    }
}