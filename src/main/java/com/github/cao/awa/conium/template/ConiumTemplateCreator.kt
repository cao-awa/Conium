package com.github.cao.awa.conium.template

import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

@JvmRecord
data class ConiumTemplateCreator(
    val name: String,
    val subtype: String,
    val factor: ConiumTemplateFactor,
    val isBedrock: Boolean
) {
    val notBedrock: Boolean get() = !this.isBedrock

    fun createResult(element: JsonElement, registryLookup: WrapperLookup): Result<ConiumTemplate<*, *>> = this.factor.createResult(element, registryLookup)

    fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumTemplate<*, *> = this.factor.create(element, registryLookup)
}
