package com.github.cao.awa.conium.molang.translator.element.program.statement

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.tree.program.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.program.statement.ret.MolangReturnStatement
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.base.StructuringElementTranslator

interface MolangStatementElementTranslator: StructuringElementTranslator<MolangStatement> {
    fun translateStatement(translator: StructuringTranslator<MolangStatement>) {
        val ast = translator.ast()
        val builder = translator.builder()

        when (ast) {
            is MolangReturnableStatement -> translator.postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, ast)
            is MolangReturnStatement -> translator.postTranslate(MolangTranslateElement.RETURN_STATEMENT, ast)
        }
    }
}