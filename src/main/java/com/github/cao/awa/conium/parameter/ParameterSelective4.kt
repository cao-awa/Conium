package com.github.cao.awa.conium.parameter

@FunctionalInterface
fun interface ParameterSelective4<R, P1, P2, P3, P4> : ParameterSelective {
    fun arise(p1: P1, p2: P2, p3: P3, p4: P4): R

    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4) = arise(p1, p2, p3, p4)
}
