package com.github.cao.awa.conium.extent.caster

import com.github.cao.awa.translator.structuring.cast.Caster

fun <X, R> X.cast(): R = Caster.cast(this)
