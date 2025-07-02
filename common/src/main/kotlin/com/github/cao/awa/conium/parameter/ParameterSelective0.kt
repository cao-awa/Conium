package com.github.cao.awa.conium.parameter

@FunctionalInterface
fun interface ParameterSelective0<R> : ParameterSelective {
    fun arise(): R

    operator fun invoke() = arise()
}
