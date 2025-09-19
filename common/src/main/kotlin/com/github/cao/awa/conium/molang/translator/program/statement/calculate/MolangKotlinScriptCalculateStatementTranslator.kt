package com.github.cao.awa.conium.molang.translator.program.statement.calculate

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.statement.calculate.MolangCalculateStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import java.lang.StringBuilder
import java.util.LinkedList

class MolangKotlinScriptCalculateStatementTranslator: MolangKotlinScriptTranslator<MolangCalculateStatement>(), MolangCalculateStatementElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangCalculateStatement) {
        val totalParen = ast.totalWithParen()
        val leftParen = ast.leftWithParen()
        val rightParen = ast.rightWithParen()

        if (totalParen) {
            builder.append("(")
        }

        if (leftParen) {
            builder.append("(")
        }

        ast.left()?.also { left: MolangReturnableStatement ->
            postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, left)
        }

        if (leftParen) {
            builder.append(")")
        }

        if (ast.symbol() != null) {
            builder.append(ast.symbol()!!.sign)
        }

        if (rightParen) {
            builder.append("(")
        }

        ast.right()?.also { right: MolangReturnableStatement ->
            postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, right)
        }

        ast.extraRights().also { rights: LinkedList<MolangReturnableStatement> ->
            for (right in rights) {
                postTranslate(MolangTranslateElement.RETURNABLE_STATEMENT, right)
            }
        }

        if (rightParen) {
            builder.append(")")
        }

        if (totalParen) {
            builder.append(")")
        }
    }
}