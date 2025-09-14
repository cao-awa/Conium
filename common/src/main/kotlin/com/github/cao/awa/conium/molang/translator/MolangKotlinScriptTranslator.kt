package com.github.cao.awa.conium.molang.translator

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.program.MolangKotlinScriptProgramTranslator
import com.github.cao.awa.conium.molang.translator.program.constant.bool.MolangKotlinScriptBooleanTranslator
import com.github.cao.awa.conium.molang.translator.program.constant.nulls.MolangKotlinScriptNullTranslator
import com.github.cao.awa.conium.molang.translator.program.constant.number.MolangKotlinScriptNumberTranslator
import com.github.cao.awa.conium.molang.translator.program.constant.string.MolangKotlinScriptStringTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.MolangKotlinScriptStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.assignment.MolangKotlinScriptAssignmentStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.calculate.MolangKotlinScriptCalculateStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.invoke.MolangKotlinScriptInvokeStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.invoke.param.MolangKotlinScriptInvokeParamStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.invoke.param.MolangKotlinScriptInvokeParamsStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.reference.MolangKotlinScriptReferenceTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.ret.MolangKotlinScriptReturnStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.returnable.MolangKotlinScriptReturnableStatementTranslator
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

abstract class MolangKotlinScriptTranslator<T: StructuringAst>(): StructuringTranslator<T>() {
    companion object {
        const val CONIUM_PROVIDER: String = "conium"

        fun postRegister() {
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.PROGRAM, MolangKotlinScriptProgramTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.STATEMENT, MolangKotlinScriptStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.INVOKE_STATEMENT, MolangKotlinScriptInvokeStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.INVOKE_PARAM, MolangKotlinScriptInvokeParamStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.INVOKE_PARAMS, MolangKotlinScriptInvokeParamsStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.ASSIGNMENT_STATEMENT, MolangKotlinScriptAssignmentStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.RETURN_STATEMENT, MolangKotlinScriptReturnStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.RETURNABLE_STATEMENT, MolangKotlinScriptReturnableStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.CALCULATE_STATEMENT, MolangKotlinScriptCalculateStatementTranslator())

            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.REFERENCE, MolangKotlinScriptReferenceTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.STRING, MolangKotlinScriptStringTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.NUMBER, MolangKotlinScriptNumberTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.NULL, MolangKotlinScriptNullTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.BOOLEAN, MolangKotlinScriptBooleanTranslator())
        }
    }

    override fun target(): LanguageTranslateTarget = LanguageTranslateTarget.KOTLIN_SCRIPT
}