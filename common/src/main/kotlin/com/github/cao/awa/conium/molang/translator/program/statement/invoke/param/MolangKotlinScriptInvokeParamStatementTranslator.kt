package com.github.cao.awa.conium.molang.translator.program.statement.invoke.param

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.param.MolangInvokeParamElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParam
import java.lang.StringBuilder

class MolangKotlinScriptInvokeParamStatementTranslator: MolangKotlinScriptTranslator<MolangInvokeParam>(), MolangInvokeParamElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangInvokeParam) {
        postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, ast.param)
    }
}