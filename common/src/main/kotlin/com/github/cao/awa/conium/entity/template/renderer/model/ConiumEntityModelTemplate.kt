package com.github.cao.awa.conium.entity.template.renderer.model

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.asObject
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates.MODEL
import com.google.gson.JsonElement
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier

class ConiumEntityModelTemplate(
    private val texturePath: Identifier,
    private val model: (EntityRendererFactory.Context) -> ConiumEntityModel,
) : ConiumEntityTemplate(true, MODEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumEntityModelTemplate {
            check(Conium.isClient) { "The template 'model'(ConiumEntityModelTemplate) can only loads in client" }

            return asObject(element) {
                return ConiumEntityModelTemplate(
                    asObject(this["texture"]) {
                        // Create the texture path.
                        Identifier.of(this["path"].asString)
                    }
                ) { context: EntityRendererFactory.Context ->
                    // Create the entity model on the renderer context.
                    ConiumEntityModel.create(context, this)
                }
            }
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        // Setting the model and texture.
        settings.client.let {
            it.clientModel = this.model
            it.clientModelTexture = this.texturePath
        }
    }
}
