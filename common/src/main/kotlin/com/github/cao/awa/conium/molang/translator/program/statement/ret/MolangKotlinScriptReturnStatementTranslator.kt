package com.github.cao.awa.conium.molang.translator.program.statement.ret

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.ret.MolangReturnElementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.MolangKotlinScriptStatementTranslator
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.statement.ret.MolangReturnStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import java.lang.StringBuilder

class MolangKotlinScriptReturnStatementTranslator: MolangKotlinScriptTranslator<MolangReturnStatement>(), MolangReturnElementTranslator {

    override fun translate(builder: StringBuilder, ast: MolangReturnStatement) {

    }
}