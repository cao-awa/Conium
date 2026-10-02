package com.github.cao.awa.conium.exception

import kotlin.jvm.Throws

fun <R> notSupported(message: String = "Not supported"): R = Exceptions.notSupported(message)

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

    /**
     * Throw an illegal argument exception with message or additional with causing exception.
     *
     * @param message the exception message
     * @param cause the causing exception
     */
    @Throws(IllegalArgumentException::class)
    fun throwIllegalArgument(message: String, cause: Throwable? = null) {
        if (cause == null) {
            throw IllegalArgumentException(message)
        } else {
            throw IllegalArgumentException(message, cause)
        }
    }

    @Throws(UnsupportedOperationException::class)
    fun <R> notSupported(message: String = "Not supported"): R {
        throw UnsupportedOperationException(message)
    }
}
