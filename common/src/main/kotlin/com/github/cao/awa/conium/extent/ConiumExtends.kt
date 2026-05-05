package com.github.cao.awa.conium.extend

import java.util.function.Consumer
import kotlin.script.experimental.jvmhost.jsr223.configureProvidedPropertiesFromJsr223Context

object ConiumExtends {
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
}