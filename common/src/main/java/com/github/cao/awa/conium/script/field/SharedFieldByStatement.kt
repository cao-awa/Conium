package com.github.cao.awa.conium.script.field

class SharedFieldByStatement(private val statement: String) : SharedField() {
    override fun toStatement(exportName: String): String = "${this.statement}\n"
}
