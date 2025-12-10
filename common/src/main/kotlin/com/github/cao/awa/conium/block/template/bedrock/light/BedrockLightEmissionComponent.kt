package com.github.cao.awa.conium.block.template.bedrock.light

import com.github.cao.awa.conium.block.template.luminance.ConiumLuminanceTemplate
import com.github.cao.awa.conium.block.template.luminance.ConiumLuminanceTemplate.Companion.validateLuminance
import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents.LIGHT_EMISSION
import com.google.gson.JsonElement

object BedrockLightEmissionComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumLuminanceTemplate = ConiumLuminanceTemplate(validateLuminance(element.asInt), LIGHT_EMISSION)
}
