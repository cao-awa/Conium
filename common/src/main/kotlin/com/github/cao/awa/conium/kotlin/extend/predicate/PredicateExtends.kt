package com.github.cao.awa.conium.kotlin.extend.predicate

import java.util.function.Predicate

infix fun <T> Predicate<T>.test(target: T): Boolean = test(target)