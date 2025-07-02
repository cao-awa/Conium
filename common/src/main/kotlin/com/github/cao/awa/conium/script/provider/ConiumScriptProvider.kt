package com.github.cao.awa.conium.script.provider

abstract class ConiumScriptProvider {
    abstract fun eval(code: String)
}