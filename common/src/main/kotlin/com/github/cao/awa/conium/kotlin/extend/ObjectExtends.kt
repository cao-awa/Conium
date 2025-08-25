package com.github.cao.awa.conium.kotlin.extend

infix fun Any?.equals(target: Any?): Boolean {
    return if (this == null) {
        target == null
    } else {
        this == target
    }
}

inline fun <reified T> T?.whenNotNull(action: (T) -> Unit) {
    this?.let(action)
}

inline fun <reified T, reified R> T?.whenNotNull(defaultResult: R, action: (T) -> R): R {
    return this?.let(action) ?: defaultResult
}