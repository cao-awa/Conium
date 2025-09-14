package com.github.cao.awa.conium.molang.translator.program.statement.calculate

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.calculate.MolangCalculateStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import java.lang.StringBuilder

class MolangKotlinScriptCalculateStatementTranslator: MolangKotlinScriptTranslator<MolangCalculateStatement>(), MolangCalculateStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangCalculateStatement) {

    }
}