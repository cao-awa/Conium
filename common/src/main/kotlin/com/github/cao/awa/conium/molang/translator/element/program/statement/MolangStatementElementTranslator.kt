package com.github.cao.awa.conium.molang.translator.element.program.statement

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.statement.ret.MolangReturnStatement
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.base.StructuringElementTranslator

interface MolangStatementElementTranslator: StructuringElementTranslator<MolangStatement> {
    fun translateStatement(translator: StructuringTranslator<MolangStatement>) {
        val ast = translator.ast()
        val builder = translator.builder()

        when (ast) {
            is MolangReturnableStatement -> {
                translator.translator(MolangTranslateElement.RETURNABLE_STATEMENT).translate(builder, ast)
            }
            is MolangReturnStatement -> {
                translator.translator(MolangTranslateElement.RETURN_STATEMENT).translate(builder, ast)
            }
        }
    }
}