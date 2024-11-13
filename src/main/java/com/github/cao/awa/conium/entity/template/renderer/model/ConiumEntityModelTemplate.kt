package com.github.cao.awa.conium.entity.template.renderer.model

import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsValue
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.MODEL
import com.google.gson.JsonElement
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumEntityModelTemplate(private val model: (EntityRendererFactory.Context) -> ConiumEntityModel) : ConiumEntityTemplate(MODEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEntityModelTemplate {
            return ConiumEntityModelTemplate { context ->
                element.asJsonObject.let { jsonObject ->
                    ConiumEntityModel.create(context, jsonObject)
                }
            }
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.migrate(ConiumEntitySettingsValue.clientMigrateKey).clientModel = this.model
    }
}
