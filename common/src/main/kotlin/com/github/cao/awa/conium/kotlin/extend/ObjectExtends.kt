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