// Generated from D:/Codes/Java/Conium/grammar/molang/Molang.g4 by ANTLR 4.13.2
package com.github.cao.awa.conium.molang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MolangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MolangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MolangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MolangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#invokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeStatement(MolangParser.InvokeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(MolangParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MolangParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#defineStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineStatement(MolangParser.DefineStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#defineReturnableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineReturnableStatement(MolangParser.DefineReturnableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculatableResultPresenting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculatableResultPresenting(MolangParser.CalculatableResultPresentingContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateStatementWithParen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateStatementWithParen(MolangParser.CalculateStatementWithParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateLeftStatementWithParen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateLeftStatementWithParen(MolangParser.CalculateLeftStatementWithParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateStatement(MolangParser.CalculateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateStatementWithTotalParen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateStatementWithTotalParen(MolangParser.CalculateStatementWithTotalParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateLeft}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateLeft(MolangParser.CalculateLeftContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#extraCalculateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtraCalculateStatement(MolangParser.ExtraCalculateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#calculateStatementRight}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculateStatementRight(MolangParser.CalculateStatementRightContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#invokeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeParam(MolangParser.InvokeParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#multiInvokeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiInvokeParam(MolangParser.MultiInvokeParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MolangParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(MolangParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(MolangParser.PointContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#leftBrace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftBrace(MolangParser.LeftBraceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#rightBrace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightBrace(MolangParser.RightBraceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#leftAngleBracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftAngleBracket(MolangParser.LeftAngleBracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#rightAngleBracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightAngleBracket(MolangParser.RightAngleBracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#leftBracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftBracket(MolangParser.LeftBracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#rightBracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightBracket(MolangParser.RightBracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#leftParenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftParenthesis(MolangParser.LeftParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#rightParenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightParenthesis(MolangParser.RightParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#leftParen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftParen(MolangParser.LeftParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#rightParen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightParen(MolangParser.RightParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(MolangParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#fullNameOrIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullNameOrIdentifier(MolangParser.FullNameOrIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MolangParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#fullName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullName(MolangParser.FullNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MolangParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(MolangParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#additionAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionAssignment(MolangParser.AdditionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#subtractionAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractionAssignment(MolangParser.SubtractionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#multiplicationAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationAssignment(MolangParser.MultiplicationAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#divisionAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionAssignment(MolangParser.DivisionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#powAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowAssignment(MolangParser.PowAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#plus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(MolangParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#minus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(MolangParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(MolangParser.MultiplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#divide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(MolangParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#pow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(MolangParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(MolangParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(MolangParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(MolangParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(MolangParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#breakingAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakingAnd(MolangParser.BreakingAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#breakingOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakingOr(MolangParser.BreakingOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(MolangParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#lessThan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThan(MolangParser.LessThanContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#moreThan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreThan(MolangParser.MoreThanContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(MolangParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#comparingAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparingAnd(MolangParser.ComparingAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#comparingOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparingOr(MolangParser.ComparingOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#comparing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparing(MolangParser.ComparingContext ctx);
	/**
	 * Visit a parse tree produced by {@link MolangParser#null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(MolangParser.NullContext ctx);
}