package com.github.cao.awa.conium.entity.template.renderer.model

import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsValue
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.MODEL
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Identifier

class ConiumEntityModelTemplate(
    private val texturePath: Identifier,
    private val model: (EntityRendererFactory.Context) -> ConiumEntityModel,
) : ConiumEntityTemplate(MODEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEntityModelTemplate {
            element as JsonObject

            return ConiumEntityModelTemplate(element["texture"].let {
                it as JsonObject

                // Create the texture path.
                Identifier.of(it["path"].asString)
            }) { context ->
                // Create the entity model on the renderer context.
                ConiumEntityModel.create(context, element)
            }
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        // Setting the model and texture.
        settings.migrate(ConiumEntitySettingsValue.clientMigrateKey) {
            it.clientModel = this.model
            it.clientModelTexture = this.texturePath
        }
    }
}
