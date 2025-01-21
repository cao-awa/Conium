package com.github.cao.awa.conium.entity.renderer

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.conium.entity.setting.ConiumAbstractEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumClientEntitySettings
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.util.Identifier

// TODO model rendering
@Environment(EnvType.CLIENT)
class ConiumEntityRenderer(
    context: EntityRendererFactory.Context,
    private val metadata: ConiumEntityMetadata,
    private val settings: ConiumClientEntitySettings = migrateClient(metadata)
) : LivingEntityRenderer<ConiumEntity, ConiumEntityRenderState, ConiumEntityModel>(
    context,
    settings.clientModel(context),
    0.25F
) {
    companion object {
        fun migrateClient(settings: ConiumAbstractEntitySettings<*>): ConiumClientEntitySettings = settings.client

        fun migrateClient(metadata: ConiumEntityMetadata): ConiumClientEntitySettings = migrateClient(metadata.settings)
    }

    private val texture = this.settings.clientModelTexture

    override fun createRenderState(): ConiumEntityRenderState = ConiumEntityRenderState()

    override fun getTexture(state: ConiumEntityRenderState): Identifier = this.texture
}
