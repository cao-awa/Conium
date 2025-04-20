package com.github.cao.awa.conium.template

import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

fun interface ConiumTemplateFactor {
    fun createResult(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): Result<ConiumTemplate<*, *>> = runCatching { create(element, registryLookup) }

    fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumTemplate<*, *>
}
