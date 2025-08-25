package com.github.cao.awa.conium.entity.template.renderer.model

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.MODEL
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class ConiumEntityModelTemplate(
    private val texturePath: Identifier,
    private val model: (EntityRendererFactory.Context) -> ConiumEntityModel,
) : ConiumEntityTemplate(true, MODEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumEntityModelTemplate {
            check(Conium.isClient) { "The template 'model'(ConiumEntityModelTemplate) can only loads in client" }

            val obj: JsonObject = element.asJsonObject

            return ConiumEntityModelTemplate(obj["texture"].asJsonObject.let {
                // Create the texture path.
                Identifier.of(it["path"].asString)
            }) { context: EntityRendererFactory.Context ->
                // Create the entity model on the renderer context.
                ConiumEntityModel.create(context, obj)
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
