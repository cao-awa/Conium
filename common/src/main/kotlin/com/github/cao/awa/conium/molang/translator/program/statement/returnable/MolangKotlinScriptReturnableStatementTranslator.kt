package com.github.cao.awa.conium.molang.translator.program.statement.returnable

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.returnable.MolangReturnableStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.constant.bool.MolangBoolean
import com.github.cao.awa.conium.molang.tree.program.constant.nulls.MolangNull
import com.github.cao.awa.conium.molang.tree.program.constant.number.MolangNumber
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.conium.molang.tree.program.statement.assignment.MolangAssignmentStatement
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import java.lang.StringBuilder

class MolangKotlinScriptReturnableStatementTranslator: MolangKotlinScriptTranslator<MolangReturnableStatement>(), MolangReturnableStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangReturnableStatement) {
        when (ast) {
            is MolangInvokeStatement -> postTranslate(MolangTranslateElement.INVOKE_STATEMENT, ast)
            is MolangAssignmentStatement -> postTranslate(MolangTranslateElement.ASSIGNMENT_STATEMENT, ast)
            is MolangCalculateStatement -> postTranslate(MolangTranslateElement.CALCULATE_STATEMENT, ast)
            is MolangNumber -> postTranslate(MolangTranslateElement.NUMBER, ast)
            is MolangString -> postTranslate(MolangTranslateElement.STRING, ast)
            is MolangNull -> postTranslate(MolangTranslateElement.NULL, ast)
            is MolangBoolean -> postTranslate(MolangTranslateElement.BOOLEAN, ast)
        }
    }
}