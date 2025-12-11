package com.github.cao.awa.conium.exception

import kotlin.jvm.Throws

object Exceptions {
    /**
     * Throw an illegal argument exception with message or additional with causing exception.
     *
     * @param message the exception message
     * @param cause the causing exception
     * @param R placeholder type, never got a result actually
     */
    @Throws(IllegalArgumentException::class)
    fun <R> illegalArgument(message: String, cause: Throwable? = null): R {
        if (cause == null) {
            throw IllegalArgumentException(message)
        } else {
            throw IllegalArgumentException(message, cause)
        }
    }
}