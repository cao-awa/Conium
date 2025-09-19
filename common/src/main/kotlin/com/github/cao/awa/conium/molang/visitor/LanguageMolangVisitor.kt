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

    override fun visitProgram(ctx: MolangParser.ProgramContext): MolangProgram {
        val molang = MolangProgram(null)
        this.current = molang
        if (ctx.defineStatement() != null) {
            val defineStatement: List<MolangParser.DefineStatementContext> = ctx.defineStatement()
            for (statementContext in defineStatement) {
                if (statementContext.invokeStatement() != null) {
                    molang.statements.add(visitInvokeStatement(statementContext.invokeStatement()))
                }

                if (statementContext.returnStatement() != null) {
                    molang.statements.add(visitReturnStatement(statementContext.returnStatement()))
                }

                if (statementContext.assignmentStatement() != null) {
                    molang.statements.add(visitAssignmentStatement(statementContext.assignmentStatement()))
                }
            }
        }
        return molang
    }

    override fun visitAssignmentStatement(ctx: MolangParser.AssignmentStatementContext): MolangAssignmentStatement {
        return MolangAssignmentStatement(this.current!!).also { assignment ->
            assignment.target = visitFullNameOrIdentifier(ctx.fullNameOrIdentifier())

            assignment.value = visitDefineReturnableStatement(ctx.defineReturnableStatement())
        }
    }

    override fun visitReturnStatement(ctx: MolangParser.ReturnStatementContext): MolangReturnStatement {
        val ast = MolangReturnStatement(this.current!!)
        if (ctx.Number() != null) {
            ast.statement(visitDefineReturnableStatement(ctx.defineReturnableStatement()))
        }
        if (ctx.defineReturnableStatement() != null) {
            ast.statement(visitDefineReturnableStatement(ctx.defineReturnableStatement()))
        }
        if (ctx.fullNameOrIdentifier() != null) {
            ast.statement(visitFullNameOrIdentifier(ctx.fullNameOrIdentifier()))
        }
        return ast
    }

    override fun visitDefineReturnableStatement(ctx: MolangParser.DefineReturnableStatementContext): MolangReturnableStatement {
        var ast: MolangReturnableStatement? = null
        if (ctx.calculateStatement() != null) {
            ast = visitCalculateStatement(ctx.calculateStatement())
        }
        if (ctx.invokeStatement() != null) {
            ast = visitInvokeStatement(ctx.invokeStatement())
        }
        if (ctx.string() != null) {
            return visitString(ctx.string())
        }
        if (ctx.number() != null) {
            return visitNumber(ctx.number())
        }
        if (ctx.fullNameOrIdentifier() != null) {
            return visitFullNameOrIdentifier(ctx.fullNameOrIdentifier())
        }
        if (ctx.bool() != null) {
            return visitBool(ctx.bool())
        }
        return ast!!
    }

    override fun visitFullNameOrIdentifier(ctx: MolangParser.FullNameOrIdentifierContext): MolangReference {
        val ast = MolangReference(this.current!!)
        ast.name = ctx.text
        return ast
    }

    override fun visitIdentifier(ctx: MolangParser.IdentifierContext): MolangReference {
        return MolangReference(this.current!!).also { reference ->
            reference.name = ctx.text
        }
    }

    override fun visitFullName(ctx: MolangParser.FullNameContext): MolangReference {
        return MolangReference(this.current!!).also { reference ->
            reference.name = ctx.text
        }
    }

    override fun visitString(ctx: MolangParser.StringContext): MolangString {
        return MolangString(this.current!!).also { string ->
            string.value = ctx.text
        }
    }

    override fun visitNumber(ctx: MolangParser.NumberContext): MolangNumber {
        val ast = MolangNumber(this.current!!)
        ast.value = BigDecimal(ctx.text)
        return ast
    }

    override fun visitBool(ctx: MolangParser.BoolContext): MolangBoolean {
        val ast = MolangBoolean(this.current!!)
        ast.value = "true".equals(ctx.text)
        return ast
    }

    override fun visitDefineStatement(ctx: MolangParser.DefineStatementContext): MolangStatement? {
        if (ctx.invokeStatement() != null) {
            return visitInvokeStatement(ctx.invokeStatement())
        }
        return null
    }

    override fun visitCalculateStatement(ctx: MolangParser.CalculateStatementContext): MolangCalculateStatement {
        var ast = MolangCalculateStatement(this.current!!)

        if (ctx.calculateStatementWithTotalParen() != null) {
            ast = visitCalculateStatementWithTotalParen(ctx.calculateStatementWithTotalParen())
            ast.totalWithParen(true)
        } else {
            if (ctx.calculateStatementWithParen() != null) {
                ast = visitCalculateStatementWithParen(ctx.calculateStatementWithParen())
                ast.totalWithParen(true)
            }
        }

        if (ctx.extraCalculateStatement() != null) {
            val extras: List<MolangParser.ExtraCalculateStatementContext> = ctx.extraCalculateStatement()
            ast = visitExtraCalculateStatement(extras[0])

            if (extras.size > 1) {
                var index = 1
                while (index < extras.size) {
                    ast.extraRights().add(visitExtraCalculateStatement(extras[index]))
                    index++
                }
            }
        }

        ast.left(visitCalculateLeft(ctx.calculateLeft()))

        return ast
    }

    override fun visitCalculateStatementWithTotalParen(ctx: MolangParser.CalculateStatementWithTotalParenContext): MolangCalculateStatement {
        val ast = MolangCalculateStatement(this.current!!)

        ast.totalWithParen(true)

        ast.left(visitCalculateLeft(ctx.calculateLeft()))

        if (ctx.extraCalculateStatement() != null) {
            for (extraCalculateStatementContext in ctx.extraCalculateStatement()) {
                val symbol: MolangCalculateSymbol = visitOperator(extraCalculateStatementContext.operator())!!

                val theRight: MolangReturnableStatement = visitCalculatableResultPresenting(extraCalculateStatementContext.calculatableResultPresenting())!!

                if (ast.right() == null) {
                    ast.symbol(symbol)
                    ast.right(theRight)
                } else {
                    val extraAst = MolangCalculateStatement(this.current!!)
                    extraAst.symbol(symbol)
                    extraAst.right(theRight)

                    ast.extraRights().add(extraAst)
                }
            }
        }

        return ast
    }

    override fun visitExtraCalculateStatement(ctx: MolangParser.ExtraCalculateStatementContext): MolangCalculateStatement {
        val ast = MolangCalculateStatement(this.current!!)

        ast.symbol(visitOperator(ctx.operator()))

        val right: MolangReturnableStatement = visitCalculatableResultPresenting(ctx.calculatableResultPresenting())!!

        if (ast.right() == null) {
            ast.right(right)
        } else {
            ast.extraRights().add(right)
        }

        return ast
    }

    override fun visitCalculateLeft(ctx: MolangParser.CalculateLeftContext): MolangReturnableStatement? {
        return visitCalculatableResultPresenting(ctx.calculatableResultPresenting())
    }

    override fun visitCalculatableResultPresenting(ctx: MolangParser.CalculatableResultPresentingContext): MolangReturnableStatement? {
        if (ctx.invokeStatement() != null) {
            return visitInvokeStatement(ctx.invokeStatement())
        } else if (ctx.constant() != null) {
            return visitConstant(ctx.constant())
        } else if (ctx.calculateStatementWithParen() != null) {
            return visitCalculateStatementWithParen(ctx.calculateStatementWithParen())
        } else if (ctx.identifier() != null) {
            return MolangReference(this.current!!).also { reference ->
                reference.name = ctx.identifier().text
            }
        } else if (ctx.fullName() != null) {
            return MolangReference(this.current!!).also { reference ->
                reference.name = ctx.fullName().text
            }
        }
        return null
    }

    override fun visitCalculateStatementWithParen(ctx: MolangParser.CalculateStatementWithParenContext): MolangCalculateStatement {
        val ast = MolangCalculateStatement(this.current!!)

        ast.leftWithParen(true)

        ast.left(visitCalculateLeftStatementWithParen(ctx.calculateLeftStatementWithParen()))

        if (ctx.extraCalculateStatement() != null) {
            for (extraCalculateStatementContext in ctx.extraCalculateStatement()) {
                val symbol = visitOperator(extraCalculateStatementContext.operator())

                val theRight = visitCalculatableResultPresenting(extraCalculateStatementContext.calculatableResultPresenting())

                if (ast.right() == null) {
                    ast.symbol(symbol)
                    ast.right(theRight!!)
                } else {
                    val extraAst = MolangCalculateStatement(this.current!!)
                    extraAst.symbol(symbol)
                    extraAst.right(theRight!!)

                    ast.extraRights().add(extraAst)
                }
            }
        }
        return ast
    }

    override fun visitInvokeStatement(ctx: MolangParser.InvokeStatementContext): MolangInvokeStatement {
        val ast = MolangInvokeStatement(this.current!!)
        if (ctx.identifier() != null) {
            ast.reference = visitIdentifier(ctx.identifier())
        }
        if (ctx.invokeParam() != null) {
            ast.params.addParam(visitInvokeParam(ctx.invokeParam()))
        }
        if (ctx.multiInvokeParam() != null) {
            ast.addParams(visitMultiInvokeParam(ctx.multiInvokeParam()))
        }
        return ast
    }

    override fun visitInvokeParam(ctx: MolangParser.InvokeParamContext): MolangInvokeParam {
        return MolangInvokeParam(this.current!!).also { param ->
            if (ctx.defineReturnableStatement() != null) {
                param.param = visitDefineReturnableStatement(ctx.defineReturnableStatement())
            }
        }
    }

    override fun visitMultiInvokeParam(ctx: MolangParser.MultiInvokeParamContext): MolangInvokeParams {
        return ctx.invokeParam().let { invokeParams ->
            val params = MolangInvokeParams(this.current!!)

            for (invokeParamContext in invokeParams) {
                params.addParam(visitInvokeParam(invokeParamContext))
            }

            params
        }
    }

    override fun visitOperator(ctx: MolangParser.OperatorContext): MolangCalculateSymbol? {
        if (ctx.arithmetic() != null) {
            return visitArithmetic(ctx.arithmetic())
        }

        if (ctx.comparing() != null) {
            return visitComparing(ctx.comparing())
        }

        if (ctx.not() != null) {
            return MolangCalculateSymbol.NOT
        }

        return null
    }

    override fun visitComparing(ctx: MolangParser.ComparingContext): MolangCalculateSymbol? {
        if (ctx.comparingOr() != null) {
            return visitComparingOr(ctx.comparingOr())
        }

        if (ctx.comparingAnd() != null) {
            return visitComparingAnd(ctx.comparingAnd())
        }

        if (ctx.moreThan() != null) {
            return MolangCalculateSymbol.MORE_THAN
        }

        if (ctx.lessThan() != null) {
            return MolangCalculateSymbol.LESS_THAN
        }

        if (ctx.Equals() != null) {
            return MolangCalculateSymbol.EQUALS
        }

        return null
    }

    override fun visitComparingAnd(ctx: MolangParser.ComparingAndContext): MolangCalculateSymbol? {
//        if (ctx.and() != null) {
//            return MolangCalculateSymbol.AND
//        }
//
//        if (ctx.breakingAnd() != null) {
//            return MolangCalculateSymbol.BREAKING_AND
//        }

        return null
    }

    override fun visitComparingOr(ctx: MolangParser.ComparingOrContext): MolangCalculateSymbol? {
//        if (ctx.or() != null) {
//            return MolangCalculateSymbol.OR
//        }
//
//        if (ctx.breakingOr() != null) {
//            return MolangCalculateSymbol.BREAKING_OR
//        }

        return null
    }

//    override fun visitComparingOr(ctx: MolangParser.ComparingOrContext): MolangCalculateSymbol? {
//        if (ctx.or() != null) {
//            return MolangCalculateSymbol.OR
//        }
//
//        if (ctx.breakingOr() != null) {
//            return MolangCalculateSymbol.BREAKING_OR
//        }
//
//        return null
//    }

    override fun visitArithmetic(ctx: MolangParser.ArithmeticContext): MolangCalculateSymbol? {
        if (ctx.Plus() != null) {
            return MolangCalculateSymbol.PLUS
        }

        if (ctx.Minus() != null) {
            return MolangCalculateSymbol.MINUS
        }

        if (ctx.Multiply() != null) {
            return MolangCalculateSymbol.MULTIPLY
        }

        if (ctx.Divide() != null) {
            return MolangCalculateSymbol.DIVIDE
        }

//        if (ctx.Pow() != null) {
//            return MolangCalculateSymbol.POW
//        }
//
//        if (ctx.AdditionAssignment() != null) {
//            return MolangCalculateSymbol.ADDITION_ASSIGNMENT
//        }
//
//        if (ctx.SubtractionAssignment() != null) {
//            return MolangCalculateSymbol.SUBTRACTION_ASSIGNMENT
//        }
//
//        if (ctx.MultiplicationAssignment() != null) {
//            return MolangCalculateSymbol.MULTIPLICATION_ASSIGNMENT
//        }
//
//        if (ctx.DivisionAssignment() != null) {
//            return MolangCalculateSymbol.DIVISION_ASSIGNMENT
//        }
//
//        if (ctx.PowAssignment() != null) {
//            return MolangCalculateSymbol.POW_ASSIGNMENT
//        }

        return null
    }

    override fun visitCalculateLeftStatementWithParen(ctx: MolangParser.CalculateLeftStatementWithParenContext): MolangReturnableStatement? {
        if (ctx.calculateStatement() != null) {
            return visitCalculateStatement(ctx.calculateStatement())
        }
        return visitCalculatableResultPresenting(ctx.calculatableResultPresenting())
    }

    override fun visitConstant(ctx: MolangParser.ConstantContext): MolangConstant<*> {
        if (ctx.string() != null) {
            val string = MolangString(this.current!!)
            string.value = ctx.string().text.replace("'", "").replace("\"", "")
            return string
        }

        if (ctx.bool() != null) {
            val bool = MolangBoolean(this.current!!)
            bool.value = ctx.bool().True() != null
            return bool
        }

        if (ctx.number() != null) {
            val number = MolangNumber(this.current!!)
            number.value = BigDecimal(ctx.number().text)
            return number
        }

        if (ctx.Null() != null) {
            return MolangNull(this.current!!)
        }

        return visitChildren(ctx) as MolangConstant<*>
    }

}