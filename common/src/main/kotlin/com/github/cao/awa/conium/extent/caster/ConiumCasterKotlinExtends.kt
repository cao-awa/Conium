package com.github.cao.awa.conium.extent.caster

import com.github.kusa233.translator.structuring.cast.Caster

fun <X: Any, R> X.cast(): R = Caster.cast(this)
