package com.github.cao.awa.conium.exception.syntax

class SyntaxNotSupportedException: RuntimeException {
    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable): super(message, cause)

    constructor(cause: Throwable): super(cause)
}