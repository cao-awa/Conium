// Generated from D:/Codes/Java/Conium/grammar/molang/Molang.g4 by ANTLR 4.13.2
package com.github.cao.awa.conium.molang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MolangParser}.
 */
public interface MolangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MolangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MolangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MolangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#invokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterInvokeStatement(MolangParser.InvokeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#invokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitInvokeStatement(MolangParser.InvokeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(MolangParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(MolangParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MolangParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MolangParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#defineStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefineStatement(MolangParser.DefineStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#defineStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefineStatement(MolangParser.DefineStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#defineReturnableStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefineReturnableStatement(MolangParser.DefineReturnableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#defineReturnableStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefineReturnableStatement(MolangParser.DefineReturnableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculatableResultPresenting}.
	 * @param ctx the parse tree
	 */
	void enterCalculatableResultPresenting(MolangParser.CalculatableResultPresentingContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculatableResultPresenting}.
	 * @param ctx the parse tree
	 */
	void exitCalculatableResultPresenting(MolangParser.CalculatableResultPresentingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateStatementWithParen}.
	 * @param ctx the parse tree
	 */
	void enterCalculateStatementWithParen(MolangParser.CalculateStatementWithParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateStatementWithParen}.
	 * @param ctx the parse tree
	 */
	void exitCalculateStatementWithParen(MolangParser.CalculateStatementWithParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateLeftStatementWithParen}.
	 * @param ctx the parse tree
	 */
	void enterCalculateLeftStatementWithParen(MolangParser.CalculateLeftStatementWithParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateLeftStatementWithParen}.
	 * @param ctx the parse tree
	 */
	void exitCalculateLeftStatementWithParen(MolangParser.CalculateLeftStatementWithParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateStatement}.
	 * @param ctx the parse tree
	 */
	void enterCalculateStatement(MolangParser.CalculateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateStatement}.
	 * @param ctx the parse tree
	 */
	void exitCalculateStatement(MolangParser.CalculateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateStatementWithTotalParen}.
	 * @param ctx the parse tree
	 */
	void enterCalculateStatementWithTotalParen(MolangParser.CalculateStatementWithTotalParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateStatementWithTotalParen}.
	 * @param ctx the parse tree
	 */
	void exitCalculateStatementWithTotalParen(MolangParser.CalculateStatementWithTotalParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateLeft}.
	 * @param ctx the parse tree
	 */
	void enterCalculateLeft(MolangParser.CalculateLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateLeft}.
	 * @param ctx the parse tree
	 */
	void exitCalculateLeft(MolangParser.CalculateLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#extraCalculateStatement}.
	 * @param ctx the parse tree
	 */
	void enterExtraCalculateStatement(MolangParser.ExtraCalculateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#extraCalculateStatement}.
	 * @param ctx the parse tree
	 */
	void exitExtraCalculateStatement(MolangParser.ExtraCalculateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#calculateStatementRight}.
	 * @param ctx the parse tree
	 */
	void enterCalculateStatementRight(MolangParser.CalculateStatementRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#calculateStatementRight}.
	 * @param ctx the parse tree
	 */
	void exitCalculateStatementRight(MolangParser.CalculateStatementRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#invokeParam}.
	 * @param ctx the parse tree
	 */
	void enterInvokeParam(MolangParser.InvokeParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#invokeParam}.
	 * @param ctx the parse tree
	 */
	void exitInvokeParam(MolangParser.InvokeParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#multiInvokeParam}.
	 * @param ctx the parse tree
	 */
	void enterMultiInvokeParam(MolangParser.MultiInvokeParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#multiInvokeParam}.
	 * @param ctx the parse tree
	 */
	void exitMultiInvokeParam(MolangParser.MultiInvokeParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MolangParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MolangParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(MolangParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(MolangParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(MolangParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(MolangParser.PointContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#leftBrace}.
	 * @param ctx the parse tree
	 */
	void enterLeftBrace(MolangParser.LeftBraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#leftBrace}.
	 * @param ctx the parse tree
	 */
	void exitLeftBrace(MolangParser.LeftBraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#rightBrace}.
	 * @param ctx the parse tree
	 */
	void enterRightBrace(MolangParser.RightBraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#rightBrace}.
	 * @param ctx the parse tree
	 */
	void exitRightBrace(MolangParser.RightBraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#leftAngleBracket}.
	 * @param ctx the parse tree
	 */
	void enterLeftAngleBracket(MolangParser.LeftAngleBracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#leftAngleBracket}.
	 * @param ctx the parse tree
	 */
	void exitLeftAngleBracket(MolangParser.LeftAngleBracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#rightAngleBracket}.
	 * @param ctx the parse tree
	 */
	void enterRightAngleBracket(MolangParser.RightAngleBracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#rightAngleBracket}.
	 * @param ctx the parse tree
	 */
	void exitRightAngleBracket(MolangParser.RightAngleBracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#leftBracket}.
	 * @param ctx the parse tree
	 */
	void enterLeftBracket(MolangParser.LeftBracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#leftBracket}.
	 * @param ctx the parse tree
	 */
	void exitLeftBracket(MolangParser.LeftBracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#rightBracket}.
	 * @param ctx the parse tree
	 */
	void enterRightBracket(MolangParser.RightBracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#rightBracket}.
	 * @param ctx the parse tree
	 */
	void exitRightBracket(MolangParser.RightBracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#leftParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterLeftParenthesis(MolangParser.LeftParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#leftParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitLeftParenthesis(MolangParser.LeftParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#rightParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterRightParenthesis(MolangParser.RightParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#rightParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitRightParenthesis(MolangParser.RightParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#leftParen}.
	 * @param ctx the parse tree
	 */
	void enterLeftParen(MolangParser.LeftParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#leftParen}.
	 * @param ctx the parse tree
	 */
	void exitLeftParen(MolangParser.LeftParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#rightParen}.
	 * @param ctx the parse tree
	 */
	void enterRightParen(MolangParser.RightParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#rightParen}.
	 * @param ctx the parse tree
	 */
	void exitRightParen(MolangParser.RightParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(MolangParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(MolangParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#fullNameOrIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterFullNameOrIdentifier(MolangParser.FullNameOrIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#fullNameOrIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitFullNameOrIdentifier(MolangParser.FullNameOrIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MolangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MolangParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#fullName}.
	 * @param ctx the parse tree
	 */
	void enterFullName(MolangParser.FullNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#fullName}.
	 * @param ctx the parse tree
	 */
	void exitFullName(MolangParser.FullNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(MolangParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(MolangParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(MolangParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(MolangParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#additionAssignment}.
	 * @param ctx the parse tree
	 */
	void enterAdditionAssignment(MolangParser.AdditionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#additionAssignment}.
	 * @param ctx the parse tree
	 */
	void exitAdditionAssignment(MolangParser.AdditionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#subtractionAssignment}.
	 * @param ctx the parse tree
	 */
	void enterSubtractionAssignment(MolangParser.SubtractionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#subtractionAssignment}.
	 * @param ctx the parse tree
	 */
	void exitSubtractionAssignment(MolangParser.SubtractionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#multiplicationAssignment}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationAssignment(MolangParser.MultiplicationAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#multiplicationAssignment}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationAssignment(MolangParser.MultiplicationAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#divisionAssignment}.
	 * @param ctx the parse tree
	 */
	void enterDivisionAssignment(MolangParser.DivisionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#divisionAssignment}.
	 * @param ctx the parse tree
	 */
	void exitDivisionAssignment(MolangParser.DivisionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#powAssignment}.
	 * @param ctx the parse tree
	 */
	void enterPowAssignment(MolangParser.PowAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#powAssignment}.
	 * @param ctx the parse tree
	 */
	void exitPowAssignment(MolangParser.PowAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(MolangParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(MolangParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(MolangParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(MolangParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(MolangParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(MolangParser.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#divide}.
	 * @param ctx the parse tree
	 */
	void enterDivide(MolangParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#divide}.
	 * @param ctx the parse tree
	 */
	void exitDivide(MolangParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#pow}.
	 * @param ctx the parse tree
	 */
	void enterPow(MolangParser.PowContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#pow}.
	 * @param ctx the parse tree
	 */
	void exitPow(MolangParser.PowContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(MolangParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(MolangParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(MolangParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(MolangParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(MolangParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(MolangParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(MolangParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(MolangParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#breakingAnd}.
	 * @param ctx the parse tree
	 */
	void enterBreakingAnd(MolangParser.BreakingAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#breakingAnd}.
	 * @param ctx the parse tree
	 */
	void exitBreakingAnd(MolangParser.BreakingAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#breakingOr}.
	 * @param ctx the parse tree
	 */
	void enterBreakingOr(MolangParser.BreakingOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#breakingOr}.
	 * @param ctx the parse tree
	 */
	void exitBreakingOr(MolangParser.BreakingOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(MolangParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(MolangParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#lessThan}.
	 * @param ctx the parse tree
	 */
	void enterLessThan(MolangParser.LessThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#lessThan}.
	 * @param ctx the parse tree
	 */
	void exitLessThan(MolangParser.LessThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#moreThan}.
	 * @param ctx the parse tree
	 */
	void enterMoreThan(MolangParser.MoreThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#moreThan}.
	 * @param ctx the parse tree
	 */
	void exitMoreThan(MolangParser.MoreThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#equals}.
	 * @param ctx the parse tree
	 */
	void enterEquals(MolangParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#equals}.
	 * @param ctx the parse tree
	 */
	void exitEquals(MolangParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#comparingAnd}.
	 * @param ctx the parse tree
	 */
	void enterComparingAnd(MolangParser.ComparingAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#comparingAnd}.
	 * @param ctx the parse tree
	 */
	void exitComparingAnd(MolangParser.ComparingAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#comparingOr}.
	 * @param ctx the parse tree
	 */
	void enterComparingOr(MolangParser.ComparingOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#comparingOr}.
	 * @param ctx the parse tree
	 */
	void exitComparingOr(MolangParser.ComparingOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#comparing}.
	 * @param ctx the parse tree
	 */
	void enterComparing(MolangParser.ComparingContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#comparing}.
	 * @param ctx the parse tree
	 */
	void exitComparing(MolangParser.ComparingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MolangParser#null}.
	 * @param ctx the parse tree
	 */
	void enterNull(MolangParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by {@link MolangParser#null}.
	 * @param ctx the parse tree
	 */
	void exitNull(MolangParser.NullContext ctx);
}