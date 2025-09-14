package com.github.cao.awa.conium.molang.translator.program.statement.invoke.param

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.param.MolangInvokeParamElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.param.MolangInvokeParamsElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParams
import java.lang.StringBuilder

class MolangKotlinScriptInvokeParamsStatementTranslator: MolangKotlinScriptTranslator<MolangInvokeParams>(), MolangInvokeParamsElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangInvokeParams) {
        var count = 0
        for (param in ast.params) {
            count++
            postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, param.param)
            if (count != ast.params.size) {
                builder.append(",")
            }
        }
    }
}