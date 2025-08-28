package com.github.cao.awa.conium.template.builder.factor

import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonElement

fun interface ConiumTemplateFactor {
    fun createResult(element: JsonElement): Result<ConiumTemplate<*, *>> = element.runCatching(::create)

    fun create(element: JsonElement): ConiumTemplate<*, *>
}