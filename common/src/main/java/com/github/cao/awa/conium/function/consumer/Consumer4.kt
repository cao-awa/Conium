package com.github.cao.awa.conium.function.consumer

@FunctionalInterface
fun interface Consumer4<I1, I2, I3, I4> {
    fun accept(i1: I1, i2: I2, i3: I3, i4: I4)
}
