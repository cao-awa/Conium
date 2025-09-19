package com.github.cao.awa.conium.molang.visitor

import com.github.cao.awa.conium.molang.antlr.MolangBaseVisitor
import com.github.cao.awa.conium.molang.antlr.MolangParser
import com.github.cao.awa.conium.molang.tree.program.MolangProgram
import com.github.cao.awa.conium.molang.tree.program.constant.MolangConstant
import com.github.cao.awa.conium.molang.tree.program.constant.bool.MolangBoolean
import com.github.cao.awa.conium.molang.tree.program.constant.nulls.MolangNull
import com.github.cao.awa.conium.molang.tree.program.constant.number.MolangNumber
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.conium.molang.tree.program.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.program.statement.assignment.MolangAssignmentStatement
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.MolangCalculateStatement
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.sign.MolangCalculateSymbol
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParams
import com.github.cao.awa.conium.molang.tree.program.statement.reference.MolangReference
import com.github.cao.awa.conium.molang.tree.program.statement.ret.MolangReturnStatement
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.math.BigDecimal

class LanguageMolangVisitor : MolangBaseVisitor<StructuringAst>() {
    private var current: StructuringAst? = null

    /**
     * Visits the root program node and builds the AST
     *
     * @param ctx The parse tree context for the program
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangProgram representing the entire program structure
     */
    override fun visitProgram(ctx: MolangParser.ProgramContext): MolangProgram {
        return MolangProgram(null).also { program ->
            this.current = program
            ctx.defineStatement()?.forEach { statementContext ->
                when {
                    statementContext.invokeStatement() != null ->
                        program.statements.add(visitInvokeStatement(statementContext.invokeStatement()))
                    statementContext.returnStatement() != null ->
                        program.statements.add(visitReturnStatement(statementContext.returnStatement()))
                    statementContext.assignmentStatement() != null ->
                        program.statements.add(visitAssignmentStatement(statementContext.assignmentStatement()))
                }
            }
        }
    }

    /**
     * Visits an assignment statement and creates corresponding AST node
     *
     * @param ctx The assignment statement context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangAssignmentStatement with target and value
     */
    override fun visitAssignmentStatement(ctx: MolangParser.AssignmentStatementContext): MolangAssignmentStatement {
        return MolangAssignmentStatement(this.current!!).apply {
            this.target = visitFullNameOrIdentifier(ctx.fullNameOrIdentifier())
            this.value = visitDefineReturnableStatement(ctx.defineReturnableStatement())
        }
    }

    /**
     * Visits a return statement and creates corresponding AST node
     *
     * @param ctx The return statement context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangReturnStatement with return value
     */
    override fun visitReturnStatement(ctx: MolangParser.ReturnStatementContext): MolangReturnStatement {
        return MolangReturnStatement(this.current!!).apply {
            ctx.defineReturnableStatement()?.let { statement(visitDefineReturnableStatement(it)) }
            ctx.fullNameOrIdentifier()?.let { statement(visitFullNameOrIdentifier(it)) }
        }
    }

    /**
     * Visits a returnable statement (expression that can be returned)
     *
     * @param ctx The returnable statement context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangReturnableStatement representing the expression
     */
    override fun visitDefineReturnableStatement(ctx: MolangParser.DefineReturnableStatementContext): MolangReturnableStatement {
        return when {
            ctx.calculateStatement() != null -> visitCalculateStatement(ctx.calculateStatement())
            ctx.invokeStatement() != null -> visitInvokeStatement(ctx.invokeStatement())
            ctx.string() != null -> visitString(ctx.string())
            ctx.number() != null -> visitNumber(ctx.number())
            ctx.fullNameOrIdentifier() != null -> visitFullNameOrIdentifier(ctx.fullNameOrIdentifier())
            ctx.bool() != null -> visitBool(ctx.bool())
            else -> throw IllegalArgumentException("Unknown returnable statement type")
        }
    }

    /**
     * Visits a reference (variable/function name)
     *
     * @param ctx The reference context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangReference with the name
     */
    override fun visitFullNameOrIdentifier(ctx: MolangParser.FullNameOrIdentifierContext): MolangReference {
        return MolangReference(this.current!!).also { it.name = ctx.text }
    }

    override fun visitIdentifier(ctx: MolangParser.IdentifierContext): MolangReference {
        return MolangReference(this.current!!).also { it.name = ctx.text }
    }

    override fun visitFullName(ctx: MolangParser.FullNameContext): MolangReference {
        return MolangReference(this.current!!).also { it.name = ctx.text }
    }

    /**
     * Visits a string literal
     * @param ctx The string context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangString with the string value
     */
    override fun visitString(ctx: MolangParser.StringContext): MolangString {
        return MolangString(current!!).also { it.value = ctx.text }
    }

    /**
     * Visits a numeric literal
     *
     * @param ctx The number context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangNumber with the numeric value
     */
    override fun visitNumber(ctx: MolangParser.NumberContext): MolangNumber {
        return MolangNumber(this.current!!).also { it.value = BigDecimal(ctx.text) }
    }

    /**
     * Visits a boolean literal
     *
     * @param ctx The boolean context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangBoolean with the boolean value
     */
    override fun visitBool(ctx: MolangParser.BoolContext): MolangBoolean {
        return MolangBoolean(this.current!!).also { it.value = ctx.text == "true" }
    }

    override fun visitDefineStatement(ctx: MolangParser.DefineStatementContext): MolangStatement? {
        return ctx.invokeStatement()?.let { visitInvokeStatement(it) }
    }

    /**
     * Visits a calculation statement and builds the expression tree
     *
     * @param ctx The calculation statement context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangCalculateStatement representing the calculation
     */
    override fun visitCalculateStatement(ctx: MolangParser.CalculateStatementContext): MolangCalculateStatement {
        val baseStatement = when {
            ctx.calculateStatementWithTotalParen() != null ->
                visitCalculateStatementWithTotalParen(ctx.calculateStatementWithTotalParen()).apply { totalWithParen(true) }
            ctx.calculateStatementWithParen() != null ->
                visitCalculateStatementWithParen(ctx.calculateStatementWithParen()).apply { totalWithParen(true) }
            else -> MolangCalculateStatement(this.current!!)
        }

        return baseStatement.apply {
            left(visitCalculateLeft(ctx.calculateLeft()))
            ctx.extraCalculateStatement()?.let { extras ->
                extras.firstOrNull()?.let { firstExtra ->
                    visitExtraCalculateStatement(firstExtra).also { first ->
                        symbol(first.symbol())
                        right(first.right()!!)
                    }
                }
                extras.drop(1).forEach { extra ->
                    extraRights().add(visitExtraCalculateStatement(extra))
                }
            }
        }
    }

    override fun visitCalculateStatementWithTotalParen(ctx: MolangParser.CalculateStatementWithTotalParenContext): MolangCalculateStatement {
        return MolangCalculateStatement(this.current!!).apply {
            totalWithParen(true)
            left(visitCalculateLeft(ctx.calculateLeft()))
            ctx.extraCalculateStatement()?.forEach { extraCtx ->
                val symbol = visitOperator(extraCtx.operator())
                val rightValue = visitCalculatableResultPresenting(extraCtx.calculatableResultPresenting())!!

                if (right() == null) {
                    symbol(symbol)
                    right(rightValue)
                } else {
                    MolangCalculateStatement(this@LanguageMolangVisitor.current!!).apply {
                        symbol(symbol)
                        right(rightValue)
                    }.also { extraRights().add(it) }
                }
            }
        }
    }

    override fun visitExtraCalculateStatement(ctx: MolangParser.ExtraCalculateStatementContext): MolangCalculateStatement {
        return MolangCalculateStatement(this.current!!).apply {
            symbol(visitOperator(ctx.operator()))
            right(visitCalculatableResultPresenting(ctx.calculatableResultPresenting())!!)
        }
    }

    override fun visitCalculateLeft(ctx: MolangParser.CalculateLeftContext): MolangReturnableStatement? {
        return visitCalculatableResultPresenting(ctx.calculatableResultPresenting())
    }

    /**
     * Visits a calculatable expression (operands in calculations)
     *
     * @param ctx The calculatable expression context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangReturnableStatement representing the operand
     */
    override fun visitCalculatableResultPresenting(ctx: MolangParser.CalculatableResultPresentingContext): MolangReturnableStatement? {
        return when {
            ctx.invokeStatement() != null -> visitInvokeStatement(ctx.invokeStatement())
            ctx.constant() != null -> visitConstant(ctx.constant())
            ctx.calculateStatementWithParen() != null -> visitCalculateStatementWithParen(ctx.calculateStatementWithParen())
            ctx.identifier() != null -> MolangReference(this.current!!).also { it.name = ctx.identifier().text }
            ctx.fullName() != null -> MolangReference(this.current!!).also { it.name = ctx.fullName().text }
            else -> null
        }
    }

    override fun visitCalculateStatementWithParen(ctx: MolangParser.CalculateStatementWithParenContext): MolangCalculateStatement {
        return MolangCalculateStatement(this.current!!).apply {
            leftWithParen(true)
            left(visitCalculateLeftStatementWithParen(ctx.calculateLeftStatementWithParen()))
            ctx.extraCalculateStatement()?.forEach { extraCtx ->
                val symbol = visitOperator(extraCtx.operator())
                val rightValue = visitCalculatableResultPresenting(extraCtx.calculatableResultPresenting())!!

                if (right() == null) {
                    symbol(symbol)
                    right(rightValue)
                } else {
                    MolangCalculateStatement(this@LanguageMolangVisitor.current!!).apply {
                        symbol(symbol)
                        right(rightValue)
                    }.also { extraRights().add(it) }
                }
            }
        }
    }

    /**
     * Visits a function invocation statement
     *
     * @param ctx The invocation context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangInvokeStatement with function reference and parameters
     */
    override fun visitInvokeStatement(ctx: MolangParser.InvokeStatementContext): MolangInvokeStatement {
        return MolangInvokeStatement(this.current!!).apply {
            ctx.identifier()?.let { this.reference = visitIdentifier(it) }
            ctx.invokeParam()?.let { this.params.addParam(visitInvokeParam(it)) }
            ctx.multiInvokeParam()?.let { addParams(visitMultiInvokeParam(it)) }
        }
    }

    override fun visitInvokeParam(ctx: MolangParser.InvokeParamContext): MolangInvokeParam {
        return MolangInvokeParam(this.current!!).apply {
            ctx.defineReturnableStatement()?.let { param = visitDefineReturnableStatement(it) }
        }
    }

    override fun visitMultiInvokeParam(ctx: MolangParser.MultiInvokeParamContext): MolangInvokeParams {
        return MolangInvokeParams(this.current!!).apply {
            ctx.invokeParam().forEach { addParam(visitInvokeParam(it)) }
        }
    }

    /**
     * Visits an operator and returns the corresponding symbol
     *
     * @param ctx The operator context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     *
     * @return MolangCalculateSymbol representing the operator
     */
    override fun visitOperator(ctx: MolangParser.OperatorContext): MolangCalculateSymbol? {
        return when {
            ctx.arithmetic() != null -> visitArithmetic(ctx.arithmetic())
            ctx.comparing() != null -> visitComparing(ctx.comparing())
            ctx.not() != null -> MolangCalculateSymbol.NOT
            else -> null
        }
    }

    override fun visitComparing(ctx: MolangParser.ComparingContext): MolangCalculateSymbol? {
        return when {
            ctx.comparingOr() != null -> visitComparingOr(ctx.comparingOr())
            ctx.comparingAnd() != null -> visitComparingAnd(ctx.comparingAnd())
            ctx.moreThan() != null -> MolangCalculateSymbol.MORE_THAN
            ctx.lessThan() != null -> MolangCalculateSymbol.LESS_THAN
            ctx.Equals() != null -> MolangCalculateSymbol.EQUALS
            else -> null
        }
    }

    override fun visitComparingAnd(ctx: MolangParser.ComparingAndContext): MolangCalculateSymbol? {
        // Implementation for AND operators would go here
        return null
    }

    override fun visitComparingOr(ctx: MolangParser.ComparingOrContext): MolangCalculateSymbol? {
        // Implementation for OR operators would go here
        return null
    }

    override fun visitArithmetic(ctx: MolangParser.ArithmeticContext): MolangCalculateSymbol? {
        return when {
            ctx.Plus() != null -> MolangCalculateSymbol.PLUS
            ctx.Minus() != null -> MolangCalculateSymbol.MINUS
            ctx.Multiply() != null -> MolangCalculateSymbol.MULTIPLY
            ctx.Divide() != null -> MolangCalculateSymbol.DIVIDE
            else -> null
        }
    }

    override fun visitCalculateLeftStatementWithParen(ctx: MolangParser.CalculateLeftStatementWithParenContext): MolangReturnableStatement? {
        return ctx.calculateStatement()?.let { visitCalculateStatement(it) }
            ?: visitCalculatableResultPresenting(ctx.calculatableResultPresenting())
    }

    /**
     * Visits a constant value (string, number, boolean, null)
     *
     * @param ctx The constant context
     *
     * @since 1.0.0
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @return MolangConstant with the appropriate value type
     */
    override fun visitConstant(ctx: MolangParser.ConstantContext): MolangConstant<*> {
        return when {
            ctx.string() != null -> MolangString(this.current!!).also {
                it.value = ctx.string().text.removeSurrounding("\"").removeSurrounding("'")
            }
            ctx.bool() != null -> MolangBoolean(this.current!!).also {
                it.value = ctx.bool().True() != null
            }
            ctx.number() != null -> MolangNumber(this.current!!).also {
                it.value = BigDecimal(ctx.number().text)
            }
            ctx.Null() != null -> MolangNull(this.current!!)
            else -> visitChildren(ctx) as MolangConstant<*>
        }
    }
}