package com.github.cao.awa.conium.template

import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

fun interface ConiumTemplateFactor {
    fun createResult(element: JsonElement): Result<ConiumTemplate<*, *>> = element.runCatching(::create)

    fun create(element: JsonElement): ConiumTemplate<*, *>
}
