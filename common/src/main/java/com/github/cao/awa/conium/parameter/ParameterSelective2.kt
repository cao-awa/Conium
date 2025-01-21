package com.github.cao.awa.conium.parameter

@FunctionalInterface
fun interface ParameterSelective2<R, P1, P2> : ParameterSelective {
    fun arise(p1: P1, p2: P2): R

    operator fun invoke(p1: P1, p2: P2) = arise(p1, p2)
}
