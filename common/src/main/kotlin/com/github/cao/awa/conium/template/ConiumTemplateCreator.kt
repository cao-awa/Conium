package com.github.cao.awa.conium.template

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
