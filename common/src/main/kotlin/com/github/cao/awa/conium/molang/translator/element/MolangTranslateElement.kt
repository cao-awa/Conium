package com.github.cao.awa.conium.molang.translator.element

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.molang.tree.program.MolangProgram
import com.github.cao.awa.conium.molang.tree.program.constant.bool.MolangBoolean
import com.github.cao.awa.conium.molang.tree.program.constant.nulls.MolangNull
import com.github.cao.awa.conium.molang.tree.program.constant.number.MolangNumber
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.conium.molang.tree.program.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.program.statement.assignment.MolangAssignmentStatement
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParams
import com.github.cao.awa.conium.molang.tree.program.statement.reference.MolangReference
import com.github.cao.awa.conium.molang.tree.program.statement.ret.MolangReturnStatement
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
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
        val INVOKE_PARAM = create(MolangInvokeParam::class.java)
        val INVOKE_PARAMS = create(MolangInvokeParams::class.java)
        val CALCULATE_STATEMENT = create(MolangCalculateStatement::class.java)
        val ASSIGNMENT_STATEMENT = create(MolangAssignmentStatement::class.java)

        val REFERENCE = create(MolangReference::class.java)
        val STRING = create(MolangString::class.java)
        val NUMBER = create(MolangNumber::class.java)
        val NULL = create(MolangNull::class.java)
        val BOOLEAN = create(MolangBoolean::class.java)

        fun <X : StructuringAst> create(ast: Class<X>): TranslateElementData<X> {
            return TranslateElementData(ast).also { data: TranslateElementData<X> ->
                this.elements.put(ast, data)
            }
        }

        fun <X : StructuringAst> byType(type: Class<X>): TranslateElementData<X> = this.elements[type].doCast()
    }
}