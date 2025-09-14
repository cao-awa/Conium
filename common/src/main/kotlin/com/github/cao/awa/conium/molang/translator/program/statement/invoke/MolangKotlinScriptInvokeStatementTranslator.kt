package com.github.cao.awa.conium.molang.translator.program.statement.invoke

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import java.lang.StringBuilder

class MolangKotlinScriptInvokeStatementTranslator: MolangKotlinScriptTranslator<MolangInvokeStatement>(), MolangInvokeStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangInvokeStatement) {
        postTranslate(MolangTranslateElement.REFERENCE, ast.reference)
        builder.append("(")
        postTranslate(MolangTranslateElement.INVOKE_PARAMS, ast.params)
        builder.append(")")
    }
}