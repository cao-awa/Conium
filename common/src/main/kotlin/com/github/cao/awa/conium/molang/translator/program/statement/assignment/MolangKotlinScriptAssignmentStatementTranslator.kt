package com.github.cao.awa.conium.molang.translator.program.statement.assignment

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.assignment.MolangAssignmentStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.assignment.MolangAssignmentStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import java.lang.StringBuilder

class MolangKotlinScriptAssignmentStatementTranslator: MolangKotlinScriptTranslator<MolangAssignmentStatement>(), MolangAssignmentStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangAssignmentStatement) {
        postTranslate(MolangTranslateElement.REFERENCE, ast.target)
        builder.append("=")
        postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, ast.value)
    }
}