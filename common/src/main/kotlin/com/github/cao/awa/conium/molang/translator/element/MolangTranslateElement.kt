package com.github.cao.awa.conium.molang.translator.element

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.molang.tree.MolangProgram
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.statement.ret.MolangReturnStatement
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.translator.structuring.translate.element.TranslateElementData
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangTranslateElement {
    companion object {
        val elements: MutableMap<Class<*>, TranslateElementData<*>> = CollectionFactor.hashMap()

        val PROGRAM = create(MolangProgram::class.java)
        val STATEMENT = create(MolangStatement::class.java)
        val RETURN_STATEMENT = create(MolangReturnStatement::class.java)
        val RETURNABLE_STATEMENT = create(MolangReturnableStatement::class.java)
        val INVOKE_STATEMENT = create(MolangInvokeStatement::class.java)
        val CALCULATE_STATEMENT = create(MolangCalculateStatement::class.java)

        fun <X : StructuringAst> create(ast: Class<X>): TranslateElementData<X> {
            return TranslateElementData(ast).also { data: TranslateElementData<X> ->
                this.elements.put(ast, data)
            }
        }

        fun <X : StructuringAst> byType(type: Class<X>): TranslateElementData<X> = this.elements[type].doCast()
    }
}