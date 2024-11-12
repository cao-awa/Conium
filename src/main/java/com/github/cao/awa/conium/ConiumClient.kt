package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers
import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.render.entity.EntityRendererFactory

class ConiumClient : ClientModInitializer {
    companion object {
        var initialized: Boolean = false
    }

    override fun onInitializeClient() {
        Conium.reloadCallbacks.add {
            ConiumEntityRenderers.renderers.clear()

            Conium.coniumEntityManager!!.metadata.forEach {
                ConiumEntityRenderers.renderers[it.type] = EntityRendererFactory { context ->
                    ConiumEntityRenderer(context, it)
                }
            }
        }

        initialized = true
    }
}