package com.github.cao.awa.conium.entity.renderer

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsValue
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.util.Identifier

// TODO model rendering
@Environment(EnvType.CLIENT)
class ConiumEntityRenderer(
    context: EntityRendererFactory.Context,
    private val metadata: ConiumEntityMetadata
) : LivingEntityRenderer<ConiumEntity, ConiumEntityRenderState, ConiumEntityModel>(
    context,
    migrateClient(metadata).clientModel(context),
    0F
) {
    companion object {
        private fun migrateClient(settings: ConiumEntitySettings): ConiumEntitySettings {
            return settings.migrate(ConiumEntitySettingsValue.clientMigrateKey)
        }

        private fun migrateClient(metadata: ConiumEntityMetadata): ConiumEntitySettings {
            return metadata.settings.migrate(ConiumEntitySettingsValue.clientMigrateKey)
        }
    }

    private val settings: ConiumEntitySettings = migrateClient(this.metadata)

    private val texture = this.settings.clientModelTexture

    override fun createRenderState(): ConiumEntityRenderState {
        return ConiumEntityRenderState()
    }

    override fun getTexture(state: ConiumEntityRenderState): Identifier = this.texture
}
