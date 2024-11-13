package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.render.entity.EntityRendererFactory

class ConiumClient : ClientModInitializer {
    companion object {
        var initialized: Boolean = false

        fun createEntityRenderer(metadata: ConiumEntityMetadata) {
            ConiumEntityRenderers.renderers[metadata.type] = EntityRendererFactory { context ->
                ConiumEntityRenderer(context, metadata)
            }
        }
    }

    override fun onInitializeClient() {
        initialized = true
    }
}