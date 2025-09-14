package com.github.cao.awa.conium.molang.translator.program.statement.reference

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.assignment.MolangAssignmentStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.reference.MolangReferenceElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.assignment.MolangAssignmentStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.reference.MolangReference
import java.lang.StringBuilder

class MolangKotlinScriptReferenceTranslator: MolangKotlinScriptTranslator<MolangReference>(), MolangReferenceElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangReference) {
        builder.append(ast.name)
    }
}