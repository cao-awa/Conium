package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.model.EntityModel
import net.minecraft.resources.Identifier

@Environment(EnvType.CLIENT)
class ConiumClientEntitySettings : ConiumAbstractEntitySettings<ConiumClientEntitySettings>() {
    /**
     * Setting the rendering model of the entity.
     *
     * Default is ``emptyModel`` for conium entity.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see ConiumEntityRenderer
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var clientModel: (EntityRendererProvider.Context) -> ConiumEntityModel
        get() = this._clientModel ?: ConiumClientEntitySettingsValue.clientModel
        set(value) {
            this._clientModel = value
        }

    // The delegate.
    private var _clientModel: ((EntityRendererProvider.Context) -> ConiumEntityModel)? = null

    /**
     * Setting the rendering model texture of the entity.
     *
     * Default is a white texture for conium entity.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see ConiumEntityRenderer
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var clientModelTexture: Identifier
        get() = this._clientModelTexture ?: ConiumClientEntitySettingsValue.clientModelTexture
        set(value) {
            this._clientModelTexture = value
        }

    // The delegate.
    private var _clientModelTexture: Identifier? = null

    override var client: ConiumClientEntitySettings
        get() = this
        set(_) {
            throw IllegalArgumentException("Cannot set client settings instance to client settings")
        }

    override fun newInstance(): ConiumClientEntitySettings = ConiumClientEntitySettings()

    override fun migrateTo(settings: ConiumClientEntitySettings): ConiumClientEntitySettings {
        return super.migrateTo(settings.also {
            // Apply settings(only configured, no default).
            this._clientModel?.apply { it.clientModel = this }
            this._clientModelTexture?.apply { it.clientModelTexture = this }
        })
    }
}
