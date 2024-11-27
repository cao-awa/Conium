package com.github.cao.awa.conium.entity.template.pushable

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsValue
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.Entity.PUSHABLE
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumEntityPushableTemplate(
    private val pushableByEntity: Boolean,
    private val pushableByPiston: Boolean,
    private val pushableByFluids: Boolean,
    name: String
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup, name: String): ConiumEntityPushableTemplate = element.objectOrBoolean(
            {
                ConiumEntityPushableTemplate(
                    it["is_pushable"]?.asBoolean ?: ConiumEntitySettingsValue.pushable,
                    it["is_pushable_by_piston"]?.asBoolean ?: ConiumEntitySettingsValue.pushableByPiston,
                    it["is_pushable_by_fluid"]?.asBoolean ?: ConiumEntitySettingsValue.pushableByFluids,
                    name
                )
            }
        ) {
            // Setting it be true or false when not specified.
            ConiumEntityPushableTemplate(
                it,
                it,
                it,
                name
            )
        }!!

        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEntityPushableTemplate = create(element, registryLookup, PUSHABLE)
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.pushable = this.pushableByEntity
        settings.pushableByPiston = this.pushableByPiston
        settings.pushableByFluids = this.pushableByFluids
    }
}
