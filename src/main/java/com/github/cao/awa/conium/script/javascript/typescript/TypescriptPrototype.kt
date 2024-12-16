package com.github.cao.awa.conium.script.javascript.typescript

import com.github.cao.awa.conium.parameter.ParameterSelective2
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@ApiStatus.Experimental
class TypescriptPrototype<T>(private val delegate: T) {
    // Test callable
    val yyy: ParameterSelective2<Unit, Any, Any> = ParameterSelective2 { p1, p2 ->
        println("Called yyy: $p1 and $p2")
    }
}

val <T> T.prototype: TypescriptPrototype<T> get() = prototype()

fun <T> T.prototype(): TypescriptPrototype<T> {
    return TypescriptPrototype(this)
}

fun main() {
    val xxx = "awa"

    xxx.prototype.yyy.call("param1", "param2")
}

// Don't care this, for test only.
// Will named 'call' or required name in produce environment.
fun ParameterSelective2<Unit, Any, Any>.call(p1: Any, p2: Any) {
    arise(p1, p2)
}

