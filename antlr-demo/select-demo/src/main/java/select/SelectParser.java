// Generated from Select.g4 by ANTLR 4.7.2

package select;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SelectParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, TEXT_STRING=17, 
		ID_LITERAL=18, DECIMAL_LITERAL=19, WS=20;
	public static final int
		RULE_stat = 0, RULE_query = 1, RULE_selectEles = 2, RULE_selectEle = 3, 
		RULE_tableSrc = 4, RULE_where = 5, RULE_logicExp = 6, RULE_val = 7, RULE_decimalLiteral = 8, 
		RULE_textLiteral = 9, RULE_logicOp = 10, RULE_cmpOp = 11, RULE_commonName = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"stat", "query", "selectEles", "selectEle", "tableSrc", "where", "logicExp", 
			"val", "decimalLiteral", "textLiteral", "logicOp", "cmpOp", "commonName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'select'", "'from'", "'*'", "','", "'where'", "'not'", "'in'", 
			"'('", "')'", "'and'", "'or'", "'='", "'>'", "'<'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "ID", "TEXT_STRING", "ID_LITERAL", "DECIMAL_LITERAL", 
			"WS"
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
	public String getGrammarFileName() { return "Select.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SelectParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StatContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			query();
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

	public static class QueryContext extends ParserRuleContext {
		public SelectElesContext selectEles() {
			return getRuleContext(SelectElesContext.class,0);
		}
		public TableSrcContext tableSrc() {
			return getRuleContext(TableSrcContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			selectEles();
			setState(30);
			match(T__1);
			setState(31);
			tableSrc();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(32);
				where();
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

	public static class SelectElesContext extends ParserRuleContext {
		public Token star;
		public List<SelectEleContext> selectEle() {
			return getRuleContexts(SelectEleContext.class);
		}
		public SelectEleContext selectEle(int i) {
			return getRuleContext(SelectEleContext.class,i);
		}
		public SelectElesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectEles; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitSelectEles(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElesContext selectEles() throws RecognitionException {
		SelectElesContext _localctx = new SelectElesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectEles);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(35);
				((SelectElesContext)_localctx).star = match(T__2);
				}
				break;
			case ID:
				{
				setState(36);
				selectEle();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(39);
				match(T__3);
				setState(40);
				selectEle();
				}
				}
				setState(45);
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

	public static class SelectEleContext extends ParserRuleContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public SelectEleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectEle; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitSelectEle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectEleContext selectEle() throws RecognitionException {
		SelectEleContext _localctx = new SelectEleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectEle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			commonName();
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

	public static class TableSrcContext extends ParserRuleContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public TableSrcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSrc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitTableSrc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSrcContext tableSrc() throws RecognitionException {
		TableSrcContext _localctx = new TableSrcContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tableSrc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			commonName();
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

	public static class WhereContext extends ParserRuleContext {
		public LogicExpContext logicExp() {
			return getRuleContext(LogicExpContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__4);
			setState(51);
			logicExp(0);
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

	public static class LogicExpContext extends ParserRuleContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public CmpOpContext cmpOp() {
			return getRuleContext(CmpOpContext.class,0);
		}
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public List<LogicExpContext> logicExp() {
			return getRuleContexts(LogicExpContext.class);
		}
		public LogicExpContext logicExp(int i) {
			return getRuleContext(LogicExpContext.class,i);
		}
		public LogicOpContext logicOp() {
			return getRuleContext(LogicOpContext.class,0);
		}
		public LogicExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitLogicExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExpContext logicExp() throws RecognitionException {
		return logicExp(0);
	}

	private LogicExpContext logicExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicExpContext _localctx = new LogicExpContext(_ctx, _parentState);
		LogicExpContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_logicExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(54);
				commonName();
				setState(55);
				cmpOp();
				setState(56);
				val();
				}
				break;
			case 2:
				{
				setState(58);
				commonName();
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(59);
					match(T__5);
					}
				}

				setState(62);
				match(T__6);
				setState(63);
				match(T__7);
				setState(64);
				val();
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(65);
					match(T__3);
					setState(66);
					val();
					}
					}
					setState(71);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(72);
				match(T__8);
				}
				break;
			case 3:
				{
				setState(74);
				match(T__7);
				setState(75);
				logicExp(0);
				setState(76);
				match(T__8);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(86);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logicExp);
					setState(80);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(81);
					logicOp();
					setState(82);
					logicExp(5);
					}
					} 
				}
				setState(88);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValContext extends ParserRuleContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public TextLiteralContext textLiteral() {
			return getRuleContext(TextLiteralContext.class,0);
		}
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_val);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				commonName();
				}
				break;
			case TEXT_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				textLiteral();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				decimalLiteral();
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

	public static class DecimalLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(SelectParser.DECIMAL_LITERAL, 0); }
		public DecimalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalLiteralContext decimalLiteral() throws RecognitionException {
		DecimalLiteralContext _localctx = new DecimalLiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(DECIMAL_LITERAL);
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

	public static class TextLiteralContext extends ParserRuleContext {
		public TerminalNode TEXT_STRING() { return getToken(SelectParser.TEXT_STRING, 0); }
		public TextLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitTextLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextLiteralContext textLiteral() throws RecognitionException {
		TextLiteralContext _localctx = new TextLiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_textLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(TEXT_STRING);
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

	public static class LogicOpContext extends ParserRuleContext {
		public LogicOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitLogicOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicOpContext logicOp() throws RecognitionException {
		LogicOpContext _localctx = new LogicOpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logicOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
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

	public static class CmpOpContext extends ParserRuleContext {
		public CmpOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmpOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitCmpOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmpOpContext cmpOp() throws RecognitionException {
		CmpOpContext _localctx = new CmpOpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmpOp);
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(T__11);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(T__12);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__13);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				match(T__13);
				setState(104);
				match(T__11);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(105);
				match(T__12);
				setState(106);
				match(T__11);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(107);
				match(T__13);
				setState(108);
				match(T__12);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(109);
				match(T__14);
				setState(110);
				match(T__11);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(111);
				match(T__13);
				setState(112);
				match(T__11);
				setState(113);
				match(T__12);
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

	public static class CommonNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SelectParser.ID, 0); }
		public CommonNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitCommonName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommonNameContext commonName() throws RecognitionException {
		CommonNameContext _localctx = new CommonNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_commonName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(ID);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return logicExp_sempred((LogicExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean logicExp_sempred(LogicExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26y\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3\4"+
		"\5\4(\n\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b?\n\b\3\b\3\b\3\b\3\b\3\b\7\bF\n\b\f\b"+
		"\16\bI\13\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bQ\n\b\3\b\3\b\3\b\3\b\7\bW\n\b"+
		"\f\b\16\bZ\13\b\3\t\3\t\3\t\5\t_\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\ru\n\r\3\16\3\16\3"+
		"\16\2\3\16\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2\f\r\2|\2\34\3\2"+
		"\2\2\4\36\3\2\2\2\6\'\3\2\2\2\b\60\3\2\2\2\n\62\3\2\2\2\f\64\3\2\2\2\16"+
		"P\3\2\2\2\20^\3\2\2\2\22`\3\2\2\2\24b\3\2\2\2\26d\3\2\2\2\30t\3\2\2\2"+
		"\32v\3\2\2\2\34\35\5\4\3\2\35\3\3\2\2\2\36\37\7\3\2\2\37 \5\6\4\2 !\7"+
		"\4\2\2!#\5\n\6\2\"$\5\f\7\2#\"\3\2\2\2#$\3\2\2\2$\5\3\2\2\2%(\7\5\2\2"+
		"&(\5\b\5\2\'%\3\2\2\2\'&\3\2\2\2(-\3\2\2\2)*\7\6\2\2*,\5\b\5\2+)\3\2\2"+
		"\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\7\3\2\2\2/-\3\2\2\2\60\61\5\32\16\2"+
		"\61\t\3\2\2\2\62\63\5\32\16\2\63\13\3\2\2\2\64\65\7\7\2\2\65\66\5\16\b"+
		"\2\66\r\3\2\2\2\678\b\b\1\289\5\32\16\29:\5\30\r\2:;\5\20\t\2;Q\3\2\2"+
		"\2<>\5\32\16\2=?\7\b\2\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7\t\2\2AB\7\n"+
		"\2\2BG\5\20\t\2CD\7\6\2\2DF\5\20\t\2EC\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3"+
		"\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\13\2\2KQ\3\2\2\2LM\7\n\2\2MN\5\16\b\2N"+
		"O\7\13\2\2OQ\3\2\2\2P\67\3\2\2\2P<\3\2\2\2PL\3\2\2\2QX\3\2\2\2RS\f\6\2"+
		"\2ST\5\26\f\2TU\5\16\b\7UW\3\2\2\2VR\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2"+
		"\2\2Y\17\3\2\2\2ZX\3\2\2\2[_\5\32\16\2\\_\5\24\13\2]_\5\22\n\2^[\3\2\2"+
		"\2^\\\3\2\2\2^]\3\2\2\2_\21\3\2\2\2`a\7\25\2\2a\23\3\2\2\2bc\7\23\2\2"+
		"c\25\3\2\2\2de\t\2\2\2e\27\3\2\2\2fu\7\16\2\2gu\7\17\2\2hu\7\20\2\2ij"+
		"\7\20\2\2ju\7\16\2\2kl\7\17\2\2lu\7\16\2\2mn\7\20\2\2nu\7\17\2\2op\7\21"+
		"\2\2pu\7\16\2\2qr\7\20\2\2rs\7\16\2\2su\7\17\2\2tf\3\2\2\2tg\3\2\2\2t"+
		"h\3\2\2\2ti\3\2\2\2tk\3\2\2\2tm\3\2\2\2to\3\2\2\2tq\3\2\2\2u\31\3\2\2"+
		"\2vw\7\22\2\2w\33\3\2\2\2\13#\'->GPX^t";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}