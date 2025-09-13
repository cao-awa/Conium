package com.github.cao.awa.conium.molang.translator

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.program.MolangKotlinScriptProgramTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.MolangKotlinScriptStatementTranslator
import com.github.cao.awa.conium.molang.translator.program.statement.invoke.MolangKotlinScriptInvokeStatementTranslator
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.lang.StringBuilder

abstract class MolangKotlinScriptTranslator<T: StructuringAst>(): StructuringTranslator<T>() {
    companion object {
        const val CONIUM_PROVIDER: String = "conium"

        fun postTranslate() {
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.PROGRAM, MolangKotlinScriptProgramTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.STATEMENT, MolangKotlinScriptStatementTranslator())
            registerKotlinScript(CONIUM_PROVIDER, MolangTranslateElement.INVOKE_STATEMENT, MolangKotlinScriptInvokeStatementTranslator())
        }
    }

    override fun target(): LanguageTranslateTarget = LanguageTranslateTarget.KOTLIN_SCRIPT
}