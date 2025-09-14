// Generated from D:/Codes/Java/Conium/grammar/molang/Molang.g4 by ANTLR 4.13.2
package com.github.cao.awa.conium.molang.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MolangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LINE_COMMENT=1, BLOCK_COMMENT=2, WHITESPACES=3, String=4, Point=5, LeftBrace=6, 
		RightBrace=7, LeftAngleBracket=8, RightAngleBracket=9, LeftBracket=10, 
		RightBracket=11, LeftParenthesis=12, RightParenthesis=13, Comma=14, Semicolon=15, 
		Return=16, Identifier=17, FullName=18, Number=19, True=20, False=21, AdditionAssignment=22, 
		SubtractionAssignment=23, MultiplicationAssignment=24, DivisionAssignment=25, 
		PowAssignment=26, Plus=27, Minus=28, Multiply=29, Divide=30, Pow=31, AndSign=32, 
		OrSign=33, BreakingAndSign=34, BreakingOrSign=35, Not=36, Equals=37, Null=38;
	public static final int
		RULE_program = 0, RULE_invokeStatement = 1, RULE_assignmentStatement = 2, 
		RULE_returnStatement = 3, RULE_defineStatement = 4, RULE_defineReturnableStatement = 5, 
		RULE_calculatableResultPresenting = 6, RULE_calculateStatementWithParen = 7, 
		RULE_calculateLeftStatementWithParen = 8, RULE_calculateStatement = 9, 
		RULE_calculateStatementWithTotalParen = 10, RULE_calculateLeft = 11, RULE_extraCalculateStatement = 12, 
		RULE_calculateStatementRight = 13, RULE_invokeParam = 14, RULE_multiInvokeParam = 15, 
		RULE_constant = 16, RULE_string = 17, RULE_point = 18, RULE_leftBrace = 19, 
		RULE_rightBrace = 20, RULE_leftAngleBracket = 21, RULE_rightAngleBracket = 22, 
		RULE_leftBracket = 23, RULE_rightBracket = 24, RULE_leftParenthesis = 25, 
		RULE_rightParenthesis = 26, RULE_leftParen = 27, RULE_rightParen = 28, 
		RULE_return = 29, RULE_fullNameOrIdentifier = 30, RULE_identifier = 31, 
		RULE_fullName = 32, RULE_number = 33, RULE_bool = 34, RULE_additionAssignment = 35, 
		RULE_subtractionAssignment = 36, RULE_multiplicationAssignment = 37, RULE_divisionAssignment = 38, 
		RULE_powAssignment = 39, RULE_plus = 40, RULE_minus = 41, RULE_multiply = 42, 
		RULE_divide = 43, RULE_pow = 44, RULE_operator = 45, RULE_arithmetic = 46, 
		RULE_and = 47, RULE_or = 48, RULE_breakingAnd = 49, RULE_breakingOr = 50, 
		RULE_not = 51, RULE_lessThan = 52, RULE_moreThan = 53, RULE_equals = 54, 
		RULE_comparingAnd = 55, RULE_comparingOr = 56, RULE_comparing = 57, RULE_null = 58;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "invokeStatement", "assignmentStatement", "returnStatement", 
			"defineStatement", "defineReturnableStatement", "calculatableResultPresenting", 
			"calculateStatementWithParen", "calculateLeftStatementWithParen", "calculateStatement", 
			"calculateStatementWithTotalParen", "calculateLeft", "extraCalculateStatement", 
			"calculateStatementRight", "invokeParam", "multiInvokeParam", "constant", 
			"string", "point", "leftBrace", "rightBrace", "leftAngleBracket", "rightAngleBracket", 
			"leftBracket", "rightBracket", "leftParenthesis", "rightParenthesis", 
			"leftParen", "rightParen", "return", "fullNameOrIdentifier", "identifier", 
			"fullName", "number", "bool", "additionAssignment", "subtractionAssignment", 
			"multiplicationAssignment", "divisionAssignment", "powAssignment", "plus", 
			"minus", "multiply", "divide", "pow", "operator", "arithmetic", "and", 
			"or", "breakingAnd", "breakingOr", "not", "lessThan", "moreThan", "equals", 
			"comparingAnd", "comparingOr", "comparing", "null"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'.'", "'{'", "'}'", "'<'", "'>'", "'['", 
			"']'", "'('", "')'", "','", "';'", "'return'", null, null, null, "'true'", 
			"'false'", "'+='", "'-='", "'*='", "'/='", "'^='", "'+'", "'-'", "'*'", 
			"'/'", "'^'", "'&'", "'|'", "'&&'", "'||'", "'!'", "'='", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LINE_COMMENT", "BLOCK_COMMENT", "WHITESPACES", "String", "Point", 
			"LeftBrace", "RightBrace", "LeftAngleBracket", "RightAngleBracket", "LeftBracket", 
			"RightBracket", "LeftParenthesis", "RightParenthesis", "Comma", "Semicolon", 
			"Return", "Identifier", "FullName", "Number", "True", "False", "AdditionAssignment", 
			"SubtractionAssignment", "MultiplicationAssignment", "DivisionAssignment", 
			"PowAssignment", "Plus", "Minus", "Multiply", "Divide", "Pow", "AndSign", 
			"OrSign", "BreakingAndSign", "BreakingOrSign", "Not", "Equals", "Null"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Molang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MolangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<DefineStatementContext> defineStatement() {
			return getRuleContexts(DefineStatementContext.class);
		}
		public DefineStatementContext defineStatement(int i) {
			return getRuleContext(DefineStatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				defineStatement();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 458752L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvokeStatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public LeftParenContext leftParen() {
			return getRuleContext(LeftParenContext.class,0);
		}
		public RightParenContext rightParen() {
			return getRuleContext(RightParenContext.class,0);
		}
		public InvokeParamContext invokeParam() {
			return getRuleContext(InvokeParamContext.class,0);
		}
		public MultiInvokeParamContext multiInvokeParam() {
			return getRuleContext(MultiInvokeParamContext.class,0);
		}
		public InvokeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invokeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterInvokeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitInvokeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitInvokeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvokeStatementContext invokeStatement() throws RecognitionException {
		InvokeStatementContext _localctx = new InvokeStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_invokeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			identifier();
			setState(124);
			leftParen();
			{
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274881974288L) != 0)) {
				{
				setState(125);
				invokeParam();
				}
			}

			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(128);
				multiInvokeParam();
				}
				break;
			}
			}
			setState(131);
			rightParen();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends ParserRuleContext {
		public FullNameOrIdentifierContext fullNameOrIdentifier() {
			return getRuleContext(FullNameOrIdentifierContext.class,0);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public DefineReturnableStatementContext defineReturnableStatement() {
			return getRuleContext(DefineReturnableStatementContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			fullNameOrIdentifier();
			setState(134);
			equals();
			setState(135);
			defineReturnableStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public DefineReturnableStatementContext defineReturnableStatement() {
			return getRuleContext(DefineReturnableStatementContext.class,0);
		}
		public FullNameOrIdentifierContext fullNameOrIdentifier() {
			return getRuleContext(FullNameOrIdentifierContext.class,0);
		}
		public TerminalNode Number() { return getToken(MolangParser.Number, 0); }
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			return_();
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(138);
				defineReturnableStatement();
				}
				break;
			case 2:
				{
				setState(139);
				fullNameOrIdentifier();
				}
				break;
			case 3:
				{
				setState(140);
				match(Number);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefineStatementContext extends ParserRuleContext {
		public InvokeStatementContext invokeStatement() {
			return getRuleContext(InvokeStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MolangParser.Semicolon, 0); }
		public DefineStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterDefineStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitDefineStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitDefineStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineStatementContext defineStatement() throws RecognitionException {
		DefineStatementContext _localctx = new DefineStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defineStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(143);
				invokeStatement();
				}
				break;
			case 2:
				{
				setState(144);
				assignmentStatement();
				}
				break;
			case 3:
				{
				setState(145);
				returnStatement();
				}
				break;
			}
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Semicolon) {
				{
				setState(148);
				match(Semicolon);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefineReturnableStatementContext extends ParserRuleContext {
		public InvokeStatementContext invokeStatement() {
			return getRuleContext(InvokeStatementContext.class,0);
		}
		public FullNameOrIdentifierContext fullNameOrIdentifier() {
			return getRuleContext(FullNameOrIdentifierContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public CalculateStatementContext calculateStatement() {
			return getRuleContext(CalculateStatementContext.class,0);
		}
		public DefineReturnableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineReturnableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterDefineReturnableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitDefineReturnableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitDefineReturnableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineReturnableStatementContext defineReturnableStatement() throws RecognitionException {
		DefineReturnableStatementContext _localctx = new DefineReturnableStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defineReturnableStatement);
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				invokeStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				fullNameOrIdentifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				number();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(154);
				string();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(155);
				bool();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(156);
				calculateStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculatableResultPresentingContext extends ParserRuleContext {
		public CalculateStatementWithParenContext calculateStatementWithParen() {
			return getRuleContext(CalculateStatementWithParenContext.class,0);
		}
		public InvokeStatementContext invokeStatement() {
			return getRuleContext(InvokeStatementContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FullNameContext fullName() {
			return getRuleContext(FullNameContext.class,0);
		}
		public CalculatableResultPresentingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculatableResultPresenting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculatableResultPresenting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculatableResultPresenting(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculatableResultPresenting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculatableResultPresentingContext calculatableResultPresenting() throws RecognitionException {
		CalculatableResultPresentingContext _localctx = new CalculatableResultPresentingContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_calculatableResultPresenting);
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				calculateStatementWithParen();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				invokeStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				constant();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(162);
				identifier();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(163);
				fullName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateStatementWithParenContext extends ParserRuleContext {
		public LeftParenContext leftParen() {
			return getRuleContext(LeftParenContext.class,0);
		}
		public RightParenContext rightParen() {
			return getRuleContext(RightParenContext.class,0);
		}
		public CalculateLeftStatementWithParenContext calculateLeftStatementWithParen() {
			return getRuleContext(CalculateLeftStatementWithParenContext.class,0);
		}
		public List<ExtraCalculateStatementContext> extraCalculateStatement() {
			return getRuleContexts(ExtraCalculateStatementContext.class);
		}
		public ExtraCalculateStatementContext extraCalculateStatement(int i) {
			return getRuleContext(ExtraCalculateStatementContext.class,i);
		}
		public CalculateStatementWithParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateStatementWithParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateStatementWithParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateStatementWithParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateStatementWithParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateStatementWithParenContext calculateStatementWithParen() throws RecognitionException {
		CalculateStatementWithParenContext _localctx = new CalculateStatementWithParenContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_calculateStatementWithParen);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			leftParen();
			{
			setState(167);
			calculateLeftStatementWithParen();
			}
			setState(168);
			rightParen();
			setState(172);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(169);
					extraCalculateStatement();
					}
					} 
				}
				setState(174);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateLeftStatementWithParenContext extends ParserRuleContext {
		public CalculateStatementContext calculateStatement() {
			return getRuleContext(CalculateStatementContext.class,0);
		}
		public CalculatableResultPresentingContext calculatableResultPresenting() {
			return getRuleContext(CalculatableResultPresentingContext.class,0);
		}
		public CalculateLeftStatementWithParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateLeftStatementWithParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateLeftStatementWithParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateLeftStatementWithParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateLeftStatementWithParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateLeftStatementWithParenContext calculateLeftStatementWithParen() throws RecognitionException {
		CalculateLeftStatementWithParenContext _localctx = new CalculateLeftStatementWithParenContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_calculateLeftStatementWithParen);
		try {
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				calculateStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				calculatableResultPresenting();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateStatementContext extends ParserRuleContext {
		public CalculateLeftContext calculateLeft() {
			return getRuleContext(CalculateLeftContext.class,0);
		}
		public List<ExtraCalculateStatementContext> extraCalculateStatement() {
			return getRuleContexts(ExtraCalculateStatementContext.class);
		}
		public ExtraCalculateStatementContext extraCalculateStatement(int i) {
			return getRuleContext(ExtraCalculateStatementContext.class,i);
		}
		public CalculateStatementWithTotalParenContext calculateStatementWithTotalParen() {
			return getRuleContext(CalculateStatementWithTotalParenContext.class,0);
		}
		public CalculateStatementWithParenContext calculateStatementWithParen() {
			return getRuleContext(CalculateStatementWithParenContext.class,0);
		}
		public CalculateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateStatementContext calculateStatement() throws RecognitionException {
		CalculateStatementContext _localctx = new CalculateStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_calculateStatement);
		int _la;
		try {
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(179);
				calculateLeft();
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274873713408L) != 0)) {
					{
					{
					setState(180);
					extraCalculateStatement();
					}
					}
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(186);
				calculateStatementWithTotalParen();
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274873713408L) != 0)) {
					{
					{
					setState(187);
					extraCalculateStatement();
					}
					}
					setState(192);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				calculateStatementWithParen();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateStatementWithTotalParenContext extends ParserRuleContext {
		public LeftParenContext leftParen() {
			return getRuleContext(LeftParenContext.class,0);
		}
		public CalculateLeftContext calculateLeft() {
			return getRuleContext(CalculateLeftContext.class,0);
		}
		public RightParenContext rightParen() {
			return getRuleContext(RightParenContext.class,0);
		}
		public List<ExtraCalculateStatementContext> extraCalculateStatement() {
			return getRuleContexts(ExtraCalculateStatementContext.class);
		}
		public ExtraCalculateStatementContext extraCalculateStatement(int i) {
			return getRuleContext(ExtraCalculateStatementContext.class,i);
		}
		public CalculateStatementWithTotalParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateStatementWithTotalParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateStatementWithTotalParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateStatementWithTotalParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateStatementWithTotalParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateStatementWithTotalParenContext calculateStatementWithTotalParen() throws RecognitionException {
		CalculateStatementWithTotalParenContext _localctx = new CalculateStatementWithTotalParenContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_calculateStatementWithTotalParen);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			leftParen();
			setState(197);
			calculateLeft();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274873713408L) != 0)) {
				{
				{
				setState(198);
				extraCalculateStatement();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			rightParen();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateLeftContext extends ParserRuleContext {
		public CalculatableResultPresentingContext calculatableResultPresenting() {
			return getRuleContext(CalculatableResultPresentingContext.class,0);
		}
		public CalculateLeftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateLeft; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateLeft(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateLeft(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateLeftContext calculateLeft() throws RecognitionException {
		CalculateLeftContext _localctx = new CalculateLeftContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_calculateLeft);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			calculatableResultPresenting();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtraCalculateStatementContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public CalculatableResultPresentingContext calculatableResultPresenting() {
			return getRuleContext(CalculatableResultPresentingContext.class,0);
		}
		public ExtraCalculateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extraCalculateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterExtraCalculateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitExtraCalculateStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitExtraCalculateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtraCalculateStatementContext extraCalculateStatement() throws RecognitionException {
		ExtraCalculateStatementContext _localctx = new ExtraCalculateStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_extraCalculateStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			operator();
			setState(209);
			calculatableResultPresenting();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CalculateStatementRightContext extends ParserRuleContext {
		public InvokeStatementContext invokeStatement() {
			return getRuleContext(InvokeStatementContext.class,0);
		}
		public FullNameOrIdentifierContext fullNameOrIdentifier() {
			return getRuleContext(FullNameOrIdentifierContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public CalculateStatementRightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculateStatementRight; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterCalculateStatementRight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitCalculateStatementRight(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitCalculateStatementRight(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculateStatementRightContext calculateStatementRight() throws RecognitionException {
		CalculateStatementRightContext _localctx = new CalculateStatementRightContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_calculateStatementRight);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(211);
				invokeStatement();
				}
				break;
			case 2:
				{
				setState(212);
				fullNameOrIdentifier();
				}
				break;
			case 3:
				{
				setState(213);
				number();
				}
				break;
			case 4:
				{
				setState(214);
				string();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvokeParamContext extends ParserRuleContext {
		public DefineReturnableStatementContext defineReturnableStatement() {
			return getRuleContext(DefineReturnableStatementContext.class,0);
		}
		public InvokeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invokeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterInvokeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitInvokeParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitInvokeParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvokeParamContext invokeParam() throws RecognitionException {
		InvokeParamContext _localctx = new InvokeParamContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_invokeParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			defineReturnableStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiInvokeParamContext extends ParserRuleContext {
		public List<TerminalNode> Comma() { return getTokens(MolangParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MolangParser.Comma, i);
		}
		public List<InvokeParamContext> invokeParam() {
			return getRuleContexts(InvokeParamContext.class);
		}
		public InvokeParamContext invokeParam(int i) {
			return getRuleContext(InvokeParamContext.class,i);
		}
		public MultiInvokeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiInvokeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterMultiInvokeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitMultiInvokeParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitMultiInvokeParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiInvokeParamContext multiInvokeParam() throws RecognitionException {
		MultiInvokeParamContext _localctx = new MultiInvokeParamContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_multiInvokeParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(219);
				match(Comma);
				setState(220);
				invokeParam();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public TerminalNode Null() { return getToken(MolangParser.Null, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				{
				setState(226);
				string();
				}
				break;
			case Number:
				{
				setState(227);
				number();
				}
				break;
			case True:
			case False:
				{
				setState(228);
				bool();
				}
				break;
			case Null:
				{
				setState(229);
				match(Null);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(MolangParser.String, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(String);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PointContext extends ParserRuleContext {
		public TerminalNode Point() { return getToken(MolangParser.Point, 0); }
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitPoint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(Point);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftBraceContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MolangParser.LeftBrace, 0); }
		public LeftBraceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftBrace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLeftBrace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLeftBrace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLeftBrace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftBraceContext leftBrace() throws RecognitionException {
		LeftBraceContext _localctx = new LeftBraceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_leftBrace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(LeftBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightBraceContext extends ParserRuleContext {
		public TerminalNode RightBrace() { return getToken(MolangParser.RightBrace, 0); }
		public RightBraceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightBrace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterRightBrace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitRightBrace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitRightBrace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightBraceContext rightBrace() throws RecognitionException {
		RightBraceContext _localctx = new RightBraceContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_rightBrace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftAngleBracketContext extends ParserRuleContext {
		public TerminalNode LeftAngleBracket() { return getToken(MolangParser.LeftAngleBracket, 0); }
		public LeftAngleBracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftAngleBracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLeftAngleBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLeftAngleBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLeftAngleBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftAngleBracketContext leftAngleBracket() throws RecognitionException {
		LeftAngleBracketContext _localctx = new LeftAngleBracketContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_leftAngleBracket);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(LeftAngleBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightAngleBracketContext extends ParserRuleContext {
		public TerminalNode RightAngleBracket() { return getToken(MolangParser.RightAngleBracket, 0); }
		public RightAngleBracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightAngleBracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterRightAngleBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitRightAngleBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitRightAngleBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightAngleBracketContext rightAngleBracket() throws RecognitionException {
		RightAngleBracketContext _localctx = new RightAngleBracketContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rightAngleBracket);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(RightAngleBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftBracketContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MolangParser.LeftBracket, 0); }
		public LeftBracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftBracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLeftBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLeftBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLeftBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftBracketContext leftBracket() throws RecognitionException {
		LeftBracketContext _localctx = new LeftBracketContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_leftBracket);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(LeftBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightBracketContext extends ParserRuleContext {
		public TerminalNode RightBracket() { return getToken(MolangParser.RightBracket, 0); }
		public RightBracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightBracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterRightBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitRightBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitRightBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightBracketContext rightBracket() throws RecognitionException {
		RightBracketContext _localctx = new RightBracketContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_rightBracket);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftParenthesisContext extends ParserRuleContext {
		public TerminalNode LeftParenthesis() { return getToken(MolangParser.LeftParenthesis, 0); }
		public LeftParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftParenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLeftParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLeftParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLeftParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftParenthesisContext leftParenthesis() throws RecognitionException {
		LeftParenthesisContext _localctx = new LeftParenthesisContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_leftParenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(LeftParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightParenthesisContext extends ParserRuleContext {
		public TerminalNode RightParenthesis() { return getToken(MolangParser.RightParenthesis, 0); }
		public RightParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightParenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterRightParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitRightParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitRightParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightParenthesisContext rightParenthesis() throws RecognitionException {
		RightParenthesisContext _localctx = new RightParenthesisContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_rightParenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(RightParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftParenContext extends ParserRuleContext {
		public TerminalNode LeftParenthesis() { return getToken(MolangParser.LeftParenthesis, 0); }
		public LeftParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLeftParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLeftParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLeftParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftParenContext leftParen() throws RecognitionException {
		LeftParenContext _localctx = new LeftParenContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_leftParen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(LeftParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightParenContext extends ParserRuleContext {
		public TerminalNode RightParenthesis() { return getToken(MolangParser.RightParenthesis, 0); }
		public RightParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterRightParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitRightParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitRightParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightParenContext rightParen() throws RecognitionException {
		RightParenContext _localctx = new RightParenContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_rightParen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(RightParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MolangParser.Return, 0); }
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(Return);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FullNameOrIdentifierContext extends ParserRuleContext {
		public TerminalNode FullName() { return getToken(MolangParser.FullName, 0); }
		public TerminalNode Identifier() { return getToken(MolangParser.Identifier, 0); }
		public FullNameOrIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullNameOrIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterFullNameOrIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitFullNameOrIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitFullNameOrIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullNameOrIdentifierContext fullNameOrIdentifier() throws RecognitionException {
		FullNameOrIdentifierContext _localctx = new FullNameOrIdentifierContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_fullNameOrIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_la = _input.LA(1);
			if ( !(_la==Identifier || _la==FullName) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MolangParser.Identifier, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FullNameContext extends ParserRuleContext {
		public TerminalNode FullName() { return getToken(MolangParser.FullName, 0); }
		public FullNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterFullName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitFullName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitFullName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullNameContext fullName() throws RecognitionException {
		FullNameContext _localctx = new FullNameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_fullName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(FullName);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(MolangParser.Number, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(Number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ParserRuleContext {
		public TerminalNode True() { return getToken(MolangParser.True, 0); }
		public TerminalNode False() { return getToken(MolangParser.False, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_la = _input.LA(1);
			if ( !(_la==True || _la==False) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditionAssignmentContext extends ParserRuleContext {
		public TerminalNode AdditionAssignment() { return getToken(MolangParser.AdditionAssignment, 0); }
		public AdditionAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterAdditionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitAdditionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitAdditionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionAssignmentContext additionAssignment() throws RecognitionException {
		AdditionAssignmentContext _localctx = new AdditionAssignmentContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_additionAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(AdditionAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubtractionAssignmentContext extends ParserRuleContext {
		public TerminalNode SubtractionAssignment() { return getToken(MolangParser.SubtractionAssignment, 0); }
		public SubtractionAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtractionAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterSubtractionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitSubtractionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitSubtractionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtractionAssignmentContext subtractionAssignment() throws RecognitionException {
		SubtractionAssignmentContext _localctx = new SubtractionAssignmentContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_subtractionAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(SubtractionAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationAssignmentContext extends ParserRuleContext {
		public TerminalNode MultiplicationAssignment() { return getToken(MolangParser.MultiplicationAssignment, 0); }
		public MultiplicationAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicationAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterMultiplicationAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitMultiplicationAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitMultiplicationAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicationAssignmentContext multiplicationAssignment() throws RecognitionException {
		MultiplicationAssignmentContext _localctx = new MultiplicationAssignmentContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_multiplicationAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(MultiplicationAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DivisionAssignmentContext extends ParserRuleContext {
		public TerminalNode DivisionAssignment() { return getToken(MolangParser.DivisionAssignment, 0); }
		public DivisionAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divisionAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterDivisionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitDivisionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitDivisionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivisionAssignmentContext divisionAssignment() throws RecognitionException {
		DivisionAssignmentContext _localctx = new DivisionAssignmentContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_divisionAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(DivisionAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowAssignmentContext extends ParserRuleContext {
		public TerminalNode PowAssignment() { return getToken(MolangParser.PowAssignment, 0); }
		public PowAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterPowAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitPowAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitPowAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowAssignmentContext powAssignment() throws RecognitionException {
		PowAssignmentContext _localctx = new PowAssignmentContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_powAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(PowAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlusContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(MolangParser.Plus, 0); }
		public PlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusContext plus() throws RecognitionException {
		PlusContext _localctx = new PlusContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_plus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(Plus);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MinusContext extends ParserRuleContext {
		public TerminalNode Minus() { return getToken(MolangParser.Minus, 0); }
		public MinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinusContext minus() throws RecognitionException {
		MinusContext _localctx = new MinusContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_minus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(Minus);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplyContext extends ParserRuleContext {
		public TerminalNode Multiply() { return getToken(MolangParser.Multiply, 0); }
		public MultiplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterMultiply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitMultiply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitMultiply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplyContext multiply() throws RecognitionException {
		MultiplyContext _localctx = new MultiplyContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_multiply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(Multiply);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DivideContext extends ParserRuleContext {
		public TerminalNode Divide() { return getToken(MolangParser.Divide, 0); }
		public DivideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterDivide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitDivide(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitDivide(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivideContext divide() throws RecognitionException {
		DivideContext _localctx = new DivideContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_divide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(Divide);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowContext extends ParserRuleContext {
		public TerminalNode Pow() { return getToken(MolangParser.Pow, 0); }
		public PowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitPow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowContext pow() throws RecognitionException {
		PowContext _localctx = new PowContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_pow);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(Pow);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ComparingContext comparing() {
			return getRuleContext(ComparingContext.class,0);
		}
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_operator);
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AdditionAssignment:
			case SubtractionAssignment:
			case MultiplicationAssignment:
			case DivisionAssignment:
			case PowAssignment:
			case Plus:
			case Minus:
			case Multiply:
			case Divide:
			case Pow:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				arithmetic();
				}
				break;
			case LeftAngleBracket:
			case RightAngleBracket:
			case AndSign:
			case OrSign:
			case BreakingAndSign:
			case BreakingOrSign:
			case Equals:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				comparing();
				}
				break;
			case Not:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
				not();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticContext extends ParserRuleContext {
		public TerminalNode AdditionAssignment() { return getToken(MolangParser.AdditionAssignment, 0); }
		public TerminalNode SubtractionAssignment() { return getToken(MolangParser.SubtractionAssignment, 0); }
		public TerminalNode MultiplicationAssignment() { return getToken(MolangParser.MultiplicationAssignment, 0); }
		public TerminalNode DivisionAssignment() { return getToken(MolangParser.DivisionAssignment, 0); }
		public TerminalNode PowAssignment() { return getToken(MolangParser.PowAssignment, 0); }
		public TerminalNode Plus() { return getToken(MolangParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MolangParser.Minus, 0); }
		public TerminalNode Multiply() { return getToken(MolangParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(MolangParser.Divide, 0); }
		public TerminalNode Pow() { return getToken(MolangParser.Pow, 0); }
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4290772992L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndContext extends ParserRuleContext {
		public TerminalNode AndSign() { return getToken(MolangParser.AndSign, 0); }
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_and);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(AndSign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrContext extends ParserRuleContext {
		public TerminalNode OrSign() { return getToken(MolangParser.OrSign, 0); }
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_or);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(OrSign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakingAndContext extends ParserRuleContext {
		public TerminalNode BreakingAndSign() { return getToken(MolangParser.BreakingAndSign, 0); }
		public BreakingAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakingAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterBreakingAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitBreakingAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitBreakingAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakingAndContext breakingAnd() throws RecognitionException {
		BreakingAndContext _localctx = new BreakingAndContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_breakingAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(BreakingAndSign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakingOrContext extends ParserRuleContext {
		public TerminalNode BreakingOrSign() { return getToken(MolangParser.BreakingOrSign, 0); }
		public BreakingOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakingOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterBreakingOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitBreakingOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitBreakingOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakingOrContext breakingOr() throws RecognitionException {
		BreakingOrContext _localctx = new BreakingOrContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_breakingOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(BreakingOrSign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends ParserRuleContext {
		public TerminalNode Not() { return getToken(MolangParser.Not, 0); }
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_not);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(Not);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LessThanContext extends ParserRuleContext {
		public TerminalNode LeftAngleBracket() { return getToken(MolangParser.LeftAngleBracket, 0); }
		public LessThanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lessThan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterLessThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitLessThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitLessThan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LessThanContext lessThan() throws RecognitionException {
		LessThanContext _localctx = new LessThanContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_lessThan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(LeftAngleBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoreThanContext extends ParserRuleContext {
		public TerminalNode RightAngleBracket() { return getToken(MolangParser.RightAngleBracket, 0); }
		public MoreThanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moreThan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterMoreThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitMoreThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitMoreThan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoreThanContext moreThan() throws RecognitionException {
		MoreThanContext _localctx = new MoreThanContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_moreThan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(RightAngleBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualsContext extends ParserRuleContext {
		public TerminalNode Equals() { return getToken(MolangParser.Equals, 0); }
		public EqualsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualsContext equals() throws RecognitionException {
		EqualsContext _localctx = new EqualsContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_equals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(Equals);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparingAndContext extends ParserRuleContext {
		public AndContext and() {
			return getRuleContext(AndContext.class,0);
		}
		public BreakingAndContext breakingAnd() {
			return getRuleContext(BreakingAndContext.class,0);
		}
		public ComparingAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparingAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterComparingAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitComparingAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitComparingAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparingAndContext comparingAnd() throws RecognitionException {
		ComparingAndContext _localctx = new ComparingAndContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_comparingAnd);
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AndSign:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				and();
				}
				break;
			case BreakingAndSign:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				breakingAnd();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparingOrContext extends ParserRuleContext {
		public OrContext or() {
			return getRuleContext(OrContext.class,0);
		}
		public BreakingOrContext breakingOr() {
			return getRuleContext(BreakingOrContext.class,0);
		}
		public ComparingOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparingOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterComparingOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitComparingOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitComparingOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparingOrContext comparingOr() throws RecognitionException {
		ComparingOrContext _localctx = new ComparingOrContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_comparingOr);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OrSign:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				or();
				}
				break;
			case BreakingOrSign:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				breakingOr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparingContext extends ParserRuleContext {
		public ComparingAndContext comparingAnd() {
			return getRuleContext(ComparingAndContext.class,0);
		}
		public ComparingOrContext comparingOr() {
			return getRuleContext(ComparingOrContext.class,0);
		}
		public MoreThanContext moreThan() {
			return getRuleContext(MoreThanContext.class,0);
		}
		public LessThanContext lessThan() {
			return getRuleContext(LessThanContext.class,0);
		}
		public TerminalNode Equals() { return getToken(MolangParser.Equals, 0); }
		public ComparingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterComparing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitComparing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitComparing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparingContext comparing() throws RecognitionException {
		ComparingContext _localctx = new ComparingContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_comparing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AndSign:
			case BreakingAndSign:
				{
				setState(319);
				comparingAnd();
				}
				break;
			case OrSign:
			case BreakingOrSign:
				{
				setState(320);
				comparingOr();
				}
				break;
			case RightAngleBracket:
				{
				setState(321);
				moreThan();
				}
				break;
			case LeftAngleBracket:
				{
				setState(322);
				lessThan();
				}
				break;
			case Equals:
				{
				setState(323);
				match(Equals);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NullContext extends ParserRuleContext {
		public TerminalNode Null() { return getToken(MolangParser.Null, 0); }
		public NullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_null; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MolangListener ) ((MolangListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MolangVisitor ) return ((MolangVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullContext null_() throws RecognitionException {
		NullContext _localctx = new NullContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_null);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(Null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u0149\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0001\u0000\u0004\u0000"+
		"x\b\u0000\u000b\u0000\f\u0000y\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u007f\b\u0001\u0001\u0001\u0003\u0001\u0082\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u008e\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u0093\b\u0004\u0001\u0004\u0003\u0004"+
		"\u0096\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u009e\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u00a5\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007\u00ab\b\u0007\n\u0007\f\u0007\u00ae"+
		"\t\u0007\u0001\b\u0001\b\u0003\b\u00b2\b\b\u0001\t\u0001\t\u0005\t\u00b6"+
		"\b\t\n\t\f\t\u00b9\t\t\u0001\t\u0001\t\u0005\t\u00bd\b\t\n\t\f\t\u00c0"+
		"\t\t\u0001\t\u0003\t\u00c3\b\t\u0001\n\u0001\n\u0001\n\u0005\n\u00c8\b"+
		"\n\n\n\f\n\u00cb\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d8\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0005\u000f\u00de\b\u000f\n\u000f"+
		"\f\u000f\u00e1\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00e7\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"(\u0001(\u0001)\u0001)\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0001"+
		"-\u0001-\u0001-\u0003-\u0124\b-\u0001.\u0001.\u0001/\u0001/\u00010\u0001"+
		"0\u00011\u00011\u00012\u00012\u00013\u00013\u00014\u00014\u00015\u0001"+
		"5\u00016\u00016\u00017\u00017\u00037\u013a\b7\u00018\u00018\u00038\u013e"+
		"\b8\u00019\u00019\u00019\u00019\u00019\u00039\u0145\b9\u0001:\u0001:\u0001"+
		":\u0000\u0000;\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprt"+
		"\u0000\u0003\u0001\u0000\u0011\u0012\u0001\u0000\u0014\u0015\u0001\u0000"+
		"\u0016\u001f\u0134\u0000w\u0001\u0000\u0000\u0000\u0002{\u0001\u0000\u0000"+
		"\u0000\u0004\u0085\u0001\u0000\u0000\u0000\u0006\u0089\u0001\u0000\u0000"+
		"\u0000\b\u0092\u0001\u0000\u0000\u0000\n\u009d\u0001\u0000\u0000\u0000"+
		"\f\u00a4\u0001\u0000\u0000\u0000\u000e\u00a6\u0001\u0000\u0000\u0000\u0010"+
		"\u00b1\u0001\u0000\u0000\u0000\u0012\u00c2\u0001\u0000\u0000\u0000\u0014"+
		"\u00c4\u0001\u0000\u0000\u0000\u0016\u00ce\u0001\u0000\u0000\u0000\u0018"+
		"\u00d0\u0001\u0000\u0000\u0000\u001a\u00d7\u0001\u0000\u0000\u0000\u001c"+
		"\u00d9\u0001\u0000\u0000\u0000\u001e\u00df\u0001\u0000\u0000\u0000 \u00e6"+
		"\u0001\u0000\u0000\u0000\"\u00e8\u0001\u0000\u0000\u0000$\u00ea\u0001"+
		"\u0000\u0000\u0000&\u00ec\u0001\u0000\u0000\u0000(\u00ee\u0001\u0000\u0000"+
		"\u0000*\u00f0\u0001\u0000\u0000\u0000,\u00f2\u0001\u0000\u0000\u0000."+
		"\u00f4\u0001\u0000\u0000\u00000\u00f6\u0001\u0000\u0000\u00002\u00f8\u0001"+
		"\u0000\u0000\u00004\u00fa\u0001\u0000\u0000\u00006\u00fc\u0001\u0000\u0000"+
		"\u00008\u00fe\u0001\u0000\u0000\u0000:\u0100\u0001\u0000\u0000\u0000<"+
		"\u0102\u0001\u0000\u0000\u0000>\u0104\u0001\u0000\u0000\u0000@\u0106\u0001"+
		"\u0000\u0000\u0000B\u0108\u0001\u0000\u0000\u0000D\u010a\u0001\u0000\u0000"+
		"\u0000F\u010c\u0001\u0000\u0000\u0000H\u010e\u0001\u0000\u0000\u0000J"+
		"\u0110\u0001\u0000\u0000\u0000L\u0112\u0001\u0000\u0000\u0000N\u0114\u0001"+
		"\u0000\u0000\u0000P\u0116\u0001\u0000\u0000\u0000R\u0118\u0001\u0000\u0000"+
		"\u0000T\u011a\u0001\u0000\u0000\u0000V\u011c\u0001\u0000\u0000\u0000X"+
		"\u011e\u0001\u0000\u0000\u0000Z\u0123\u0001\u0000\u0000\u0000\\\u0125"+
		"\u0001\u0000\u0000\u0000^\u0127\u0001\u0000\u0000\u0000`\u0129\u0001\u0000"+
		"\u0000\u0000b\u012b\u0001\u0000\u0000\u0000d\u012d\u0001\u0000\u0000\u0000"+
		"f\u012f\u0001\u0000\u0000\u0000h\u0131\u0001\u0000\u0000\u0000j\u0133"+
		"\u0001\u0000\u0000\u0000l\u0135\u0001\u0000\u0000\u0000n\u0139\u0001\u0000"+
		"\u0000\u0000p\u013d\u0001\u0000\u0000\u0000r\u0144\u0001\u0000\u0000\u0000"+
		"t\u0146\u0001\u0000\u0000\u0000vx\u0003\b\u0004\u0000wv\u0001\u0000\u0000"+
		"\u0000xy\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z\u0001\u0001\u0000\u0000\u0000{|\u0003>\u001f\u0000|~\u0003"+
		"6\u001b\u0000}\u007f\u0003\u001c\u000e\u0000~}\u0001\u0000\u0000\u0000"+
		"~\u007f\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080"+
		"\u0082\u0003\u001e\u000f\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u00038\u001c\u0000\u0084\u0003\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0003<\u001e\u0000\u0086\u0087\u0003l6\u0000\u0087\u0088\u0003\n\u0005"+
		"\u0000\u0088\u0005\u0001\u0000\u0000\u0000\u0089\u008d\u0003:\u001d\u0000"+
		"\u008a\u008e\u0003\n\u0005\u0000\u008b\u008e\u0003<\u001e\u0000\u008c"+
		"\u008e\u0005\u0013\u0000\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008d"+
		"\u008b\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e"+
		"\u0007\u0001\u0000\u0000\u0000\u008f\u0093\u0003\u0002\u0001\u0000\u0090"+
		"\u0093\u0003\u0004\u0002\u0000\u0091\u0093\u0003\u0006\u0003\u0000\u0092"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092"+
		"\u0091\u0001\u0000\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000\u0094"+
		"\u0096\u0005\u000f\u0000\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0001\u0000\u0000\u0000\u0096\t\u0001\u0000\u0000\u0000\u0097\u009e"+
		"\u0003\u0002\u0001\u0000\u0098\u009e\u0003<\u001e\u0000\u0099\u009e\u0003"+
		"B!\u0000\u009a\u009e\u0003\"\u0011\u0000\u009b\u009e\u0003D\"\u0000\u009c"+
		"\u009e\u0003\u0012\t\u0000\u009d\u0097\u0001\u0000\u0000\u0000\u009d\u0098"+
		"\u0001\u0000\u0000\u0000\u009d\u0099\u0001\u0000\u0000\u0000\u009d\u009a"+
		"\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u000b\u0001\u0000\u0000\u0000\u009f\u00a5"+
		"\u0003\u000e\u0007\u0000\u00a0\u00a5\u0003\u0002\u0001\u0000\u00a1\u00a5"+
		"\u0003 \u0010\u0000\u00a2\u00a5\u0003>\u001f\u0000\u00a3\u00a5\u0003@"+
		" \u0000\u00a4\u009f\u0001\u0000\u0000\u0000\u00a4\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\r\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u00036\u001b\u0000\u00a7\u00a8\u0003\u0010\b\u0000\u00a8"+
		"\u00ac\u00038\u001c\u0000\u00a9\u00ab\u0003\u0018\f\u0000\u00aa\u00a9"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ae\u0001\u0000\u0000\u0000\u00ac\u00aa"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u000f"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00af\u00b2"+
		"\u0003\u0012\t\u0000\u00b0\u00b2\u0003\f\u0006\u0000\u00b1\u00af\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b2\u0011\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b7\u0003\u0016\u000b\u0000\u00b4\u00b6\u0003"+
		"\u0018\f\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00c3\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000"+
		"\u0000\u0000\u00ba\u00be\u0003\u0014\n\u0000\u00bb\u00bd\u0003\u0018\f"+
		"\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000"+
		"\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c3\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c3\u0003\u000e\u0007\u0000\u00c2\u00b3\u0001\u0000\u0000"+
		"\u0000\u00c2\u00ba\u0001\u0000\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c3\u0013\u0001\u0000\u0000\u0000\u00c4\u00c5\u00036\u001b\u0000"+
		"\u00c5\u00c9\u0003\u0016\u000b\u0000\u00c6\u00c8\u0003\u0018\f\u0000\u00c7"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c8\u00cb\u0001\u0000\u0000\u0000\u00c9"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cc"+
		"\u00cd\u00038\u001c\u0000\u00cd\u0015\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0003\f\u0006\u0000\u00cf\u0017\u0001\u0000\u0000\u0000\u00d0\u00d1\u0003"+
		"Z-\u0000\u00d1\u00d2\u0003\f\u0006\u0000\u00d2\u0019\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d8\u0003\u0002\u0001\u0000\u00d4\u00d8\u0003<\u001e\u0000"+
		"\u00d5\u00d8\u0003B!\u0000\u00d6\u00d8\u0003\"\u0011\u0000\u00d7\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d4\u0001\u0000\u0000\u0000\u00d7\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d8\u001b"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0003\n\u0005\u0000\u00da\u001d\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0005\u000e\u0000\u0000\u00dc\u00de\u0003"+
		"\u001c\u000e\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e1\u0001"+
		"\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e0\u001f\u0001\u0000\u0000\u0000\u00e1\u00df\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e7\u0003\"\u0011\u0000\u00e3\u00e7\u0003B"+
		"!\u0000\u00e4\u00e7\u0003D\"\u0000\u00e5\u00e7\u0005&\u0000\u0000\u00e6"+
		"\u00e2\u0001\u0000\u0000\u0000\u00e6\u00e3\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7"+
		"!\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005\u0004\u0000\u0000\u00e9#\u0001"+
		"\u0000\u0000\u0000\u00ea\u00eb\u0005\u0005\u0000\u0000\u00eb%\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ed\u0005\u0006\u0000\u0000\u00ed\'\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0005\u0007\u0000\u0000\u00ef)\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f1\u0005\b\u0000\u0000\u00f1+\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0005\t\u0000\u0000\u00f3-\u0001\u0000\u0000\u0000\u00f4\u00f5"+
		"\u0005\n\u0000\u0000\u00f5/\u0001\u0000\u0000\u0000\u00f6\u00f7\u0005"+
		"\u000b\u0000\u0000\u00f71\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005\f"+
		"\u0000\u0000\u00f93\u0001\u0000\u0000\u0000\u00fa\u00fb\u0005\r\u0000"+
		"\u0000\u00fb5\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005\f\u0000\u0000"+
		"\u00fd7\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\r\u0000\u0000\u00ff"+
		"9\u0001\u0000\u0000\u0000\u0100\u0101\u0005\u0010\u0000\u0000\u0101;\u0001"+
		"\u0000\u0000\u0000\u0102\u0103\u0007\u0000\u0000\u0000\u0103=\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0005\u0011\u0000\u0000\u0105?\u0001\u0000\u0000"+
		"\u0000\u0106\u0107\u0005\u0012\u0000\u0000\u0107A\u0001\u0000\u0000\u0000"+
		"\u0108\u0109\u0005\u0013\u0000\u0000\u0109C\u0001\u0000\u0000\u0000\u010a"+
		"\u010b\u0007\u0001\u0000\u0000\u010bE\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0005\u0016\u0000\u0000\u010dG\u0001\u0000\u0000\u0000\u010e\u010f\u0005"+
		"\u0017\u0000\u0000\u010fI\u0001\u0000\u0000\u0000\u0110\u0111\u0005\u0018"+
		"\u0000\u0000\u0111K\u0001\u0000\u0000\u0000\u0112\u0113\u0005\u0019\u0000"+
		"\u0000\u0113M\u0001\u0000\u0000\u0000\u0114\u0115\u0005\u001a\u0000\u0000"+
		"\u0115O\u0001\u0000\u0000\u0000\u0116\u0117\u0005\u001b\u0000\u0000\u0117"+
		"Q\u0001\u0000\u0000\u0000\u0118\u0119\u0005\u001c\u0000\u0000\u0119S\u0001"+
		"\u0000\u0000\u0000\u011a\u011b\u0005\u001d\u0000\u0000\u011bU\u0001\u0000"+
		"\u0000\u0000\u011c\u011d\u0005\u001e\u0000\u0000\u011dW\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0005\u001f\u0000\u0000\u011fY\u0001\u0000\u0000\u0000"+
		"\u0120\u0124\u0003\\.\u0000\u0121\u0124\u0003r9\u0000\u0122\u0124\u0003"+
		"f3\u0000\u0123\u0120\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000"+
		"\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0124[\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0007\u0002\u0000\u0000\u0126]\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0005 \u0000\u0000\u0128_\u0001\u0000\u0000\u0000\u0129\u012a\u0005"+
		"!\u0000\u0000\u012aa\u0001\u0000\u0000\u0000\u012b\u012c\u0005\"\u0000"+
		"\u0000\u012cc\u0001\u0000\u0000\u0000\u012d\u012e\u0005#\u0000\u0000\u012e"+
		"e\u0001\u0000\u0000\u0000\u012f\u0130\u0005$\u0000\u0000\u0130g\u0001"+
		"\u0000\u0000\u0000\u0131\u0132\u0005\b\u0000\u0000\u0132i\u0001\u0000"+
		"\u0000\u0000\u0133\u0134\u0005\t\u0000\u0000\u0134k\u0001\u0000\u0000"+
		"\u0000\u0135\u0136\u0005%\u0000\u0000\u0136m\u0001\u0000\u0000\u0000\u0137"+
		"\u013a\u0003^/\u0000\u0138\u013a\u0003b1\u0000\u0139\u0137\u0001\u0000"+
		"\u0000\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u013ao\u0001\u0000\u0000"+
		"\u0000\u013b\u013e\u0003`0\u0000\u013c\u013e\u0003d2\u0000\u013d\u013b"+
		"\u0001\u0000\u0000\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013eq\u0001"+
		"\u0000\u0000\u0000\u013f\u0145\u0003n7\u0000\u0140\u0145\u0003p8\u0000"+
		"\u0141\u0145\u0003j5\u0000\u0142\u0145\u0003h4\u0000\u0143\u0145\u0005"+
		"%\u0000\u0000\u0144\u013f\u0001\u0000\u0000\u0000\u0144\u0140\u0001\u0000"+
		"\u0000\u0000\u0144\u0141\u0001\u0000\u0000\u0000\u0144\u0142\u0001\u0000"+
		"\u0000\u0000\u0144\u0143\u0001\u0000\u0000\u0000\u0145s\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0005&\u0000\u0000\u0147u\u0001\u0000\u0000\u0000\u0015"+
		"y~\u0081\u008d\u0092\u0095\u009d\u00a4\u00ac\u00b1\u00b7\u00be\u00c2\u00c9"+
		"\u00d7\u00df\u00e6\u0123\u0139\u013d\u0144";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}