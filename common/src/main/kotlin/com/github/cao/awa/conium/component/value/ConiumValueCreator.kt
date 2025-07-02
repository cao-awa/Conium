package com.github.cao.awa.conium.component.value

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.google.gson.JsonElement

fun interface ConiumValueCreator<T> {
    fun createValue(element: JsonElement): T

    fun <X> castValue(value: X): T {
        if (value is JsonElement) {
            return createValue(value)
        }
        return value.doCast()
    }
}
