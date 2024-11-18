package com.github.cao.awa.conium.parameter

@FunctionalInterface
fun interface ParameterSelective1<R, P> : ParameterSelective {
    fun arise(p1: P): R

    operator fun invoke(p1: P) = arise(p1)
}
