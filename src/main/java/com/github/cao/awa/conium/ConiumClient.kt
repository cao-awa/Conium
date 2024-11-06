package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers
import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.render.entity.EntityRendererFactory


class ConiumClient : ClientModInitializer {
    override fun onInitializeClient() {
        Conium.reloadCallbacks.add {
            ConiumEntityRenderers.renderers.clear()

            Conium.coniumEntityManager!!.types.forEach {
                ConiumEntityRenderers.renderers[it] = EntityRendererFactory { context ->
                    ConiumEntityRenderer(context)
                }
            }
        }
    }
}