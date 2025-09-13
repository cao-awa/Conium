package com.github.cao.awa.conium.molang.translator.program.statement

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.MolangStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import java.lang.StringBuilder

class MolangKotlinScriptStatementTranslator: MolangKotlinScriptTranslator<MolangStatement>(), MolangStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangStatement) {
        translateStatement(this)
    }
}