package com.github.cao.awa.conium.template.builder.factor

import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.builder.factor.ConiumTemplateFactor
import com.google.gson.JsonElement

@JvmRecord
data class ConiumTemplateCreator(
    val name: String,
    val subtype: String,
    val factor: ConiumTemplateFactor,
    val isBedrock: Boolean
) {
    val notBedrock: Boolean get() = !this.isBedrock

    fun createResult(element: JsonElement): Result<ConiumTemplate<*, *>> = this.factor.createResult(element)

    fun create(element: JsonElement): ConiumTemplate<*, *> = this.factor.create(element)
}