package com.github.cao.awa.conium.script.field

abstract class SharedField {
    abstract fun toStatement(exportName: String): String
}
