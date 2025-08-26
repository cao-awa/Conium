package com.github.cao.awa.conium.extend

fun <R> Result<R>.ifException(action: (Throwable) -> Unit) {
    exceptionOrNull()?.also {
        action(it)
    }
}

fun <R> Result<R>.ifException(defaultValue: R, action: (Throwable) -> R): R {
    return exceptionOrNull()?.let {
        action(it)
    } ?: defaultValue
}