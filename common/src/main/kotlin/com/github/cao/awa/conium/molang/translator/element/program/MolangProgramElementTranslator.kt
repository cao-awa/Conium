package com.github.cao.awa.conium.molang.translator.element.program

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.tree.MolangProgram
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.base.StructuringElementTranslator

interface MolangProgramElementTranslator : StructuringElementTranslator<MolangProgram> {
    fun translateEach(translator: StructuringTranslator<MolangProgram>) {
        val ast = translator.ast()
        val builder = translator.builder()

        translator.translator(MolangTranslateElement.Companion.STATEMENT, { next: StructuringTranslator<MolangStatement> ->
            for (ast in ast.statements) {
                next.postTranslate(builder, ast, translator)
                translateLineWrap(translator)
            }
        })
    }
}