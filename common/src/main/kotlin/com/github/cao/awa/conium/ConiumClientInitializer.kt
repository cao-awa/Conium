package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import com.github.cao.awa.conium.event.type.ConiumClientEventArgTypes
import com.github.cao.awa.conium.network.registry.ConiumPacketRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Environment(EnvType.CLIENT)
class ConiumClientInitializer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumClientInitializer")

        @Environment(EnvType.CLIENT)
        fun createEntityRenderer(metadata: ConiumEntityMetadata) {
            ConiumEntityRenderers.renderers[metadata.type] = EntityRendererFactory { context ->
                ConiumEntityRenderer(context, metadata)
            }
        }
    }

    fun onInitializeClient() {
        ConiumClient.onInitialized()

        // Initialize for network packets.
        ConiumPacketRegistry.registerAll()
        ConiumPacketRegistry.packets.let { packets ->
            LOGGER.info("Loaded ${packets.size} network packets")
            Conium.debug(
                "Loaded {} client network packets: {}",
                { packets.size },
                { packets },
                LOGGER::info
            )
        }

        ConiumClientEventArgTypes.onClientInitialized()
    }
}