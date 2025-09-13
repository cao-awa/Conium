package com.github.cao.awa.conium.molang.translator.program.statement.invoke

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.MolangKotlinScriptStatementTranslator
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import java.lang.StringBuilder

class MolangKotlinScriptInvokeStatementTranslator: MolangKotlinScriptTranslator<MolangInvokeStatement>(), MolangInvokeStatementElementTranslator {

    override fun translate(builder: StringBuilder, ast: MolangInvokeStatement) {

    }
}