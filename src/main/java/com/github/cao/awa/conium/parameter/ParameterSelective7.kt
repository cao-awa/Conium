package com.github.cao.awa.conium.parameter

@FunctionalInterface
fun interface ParameterSelective7<R, P1, P2, P3, P4, P5, P6, P7> : ParameterSelective {
    fun arise(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): R

    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = arise(p1, p2, p3, p4, p5, p6, p7)
}
