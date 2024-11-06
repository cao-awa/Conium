package com.github.cao.awa.conium.entity.renderer

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory

@Environment(EnvType.CLIENT)
class ConiumEntityRenderer(context: EntityRendererFactory.Context) : EntityRenderer<ConiumEntity, ConiumEntityRenderState>(context) {
    override fun createRenderState(): ConiumEntityRenderState {
        return ConiumEntityRenderState()
    }
}
