package com.github.cao.awa.conium.molang.translator.program.statement.ret

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.ret.MolangReturnStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.ret.MolangReturnStatement
import java.lang.StringBuilder

class MolangKotlinScriptReturnStatementTranslator: MolangKotlinScriptTranslator<MolangReturnStatement>(), MolangReturnStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangReturnStatement) {

    }
}