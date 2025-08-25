package com.github.cao.awa.conium.kotlin.extend.manipulate

import com.github.cao.awa.sinuatum.function.exception.consumer.ExceptingConsumer
import com.github.cao.awa.sinuatum.function.exception.function.ExceptingFunction
import com.github.cao.awa.sinuatum.manipulate.Manipulate

fun <X, R> X.op(function: ExceptingFunction<X, R, Throwable>) = Manipulate.op(this, function)

fun <X> X.make(function: ExceptingConsumer<X, Throwable>) = Manipulate.make(this, function)

fun <X, R> X.doCast(): R = Manipulate.cast(this)
