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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, ID=22, TEXT_STRING=23, ID_LITERAL=24, 
		DECIMAL_LITERAL=25, WS=26;
	public static final int
		RULE_stat = 0, RULE_query = 1, RULE_selectElse = 2, RULE_selectEle = 3, 
		RULE_tableSrc = 4, RULE_where = 5, RULE_logicExp = 6, RULE_inOp = 7, RULE_orderBy = 8, 
		RULE_limit = 9, RULE_offset = 10, RULE_size = 11, RULE_val = 12, RULE_decimalLiteral = 13, 
		RULE_textLiteral = 14, RULE_logicOp = 15, RULE_cmpOp = 16, RULE_commonName = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"stat", "query", "selectElse", "selectEle", "tableSrc", "where", "logicExp", 
			"inOp", "orderBy", "limit", "offset", "size", "val", "decimalLiteral", 
			"textLiteral", "logicOp", "cmpOp", "commonName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'select'", "'from'", "'*'", "','", "'where'", "'('", "')'", "'not in'", 
			"'in'", "'order by'", "'desc'", "'asc'", "'limit'", "'and'", "'or'", 
			"'='", "'>'", "'<'", "'<='", "'>='", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "ID", "TEXT_STRING", 
			"ID_LITERAL", "DECIMAL_LITERAL", "WS"
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
			setState(36);
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
		public SelectElseContext selectElse() {
			return getRuleContext(SelectElseContext.class,0);
		}
		public TableSrcContext tableSrc() {
			return getRuleContext(TableSrcContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
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
			setState(38);
			match(T__0);
			setState(39);
			selectElse();
			setState(40);
			match(T__1);
			setState(41);
			tableSrc();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(42);
				where();
				}
			}

			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(45);
				orderBy();
				}
			}

			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(48);
				limit();
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

	public static class SelectElseContext extends ParserRuleContext {
		public Token star;
		public List<SelectEleContext> selectEle() {
			return getRuleContexts(SelectEleContext.class);
		}
		public SelectEleContext selectEle(int i) {
			return getRuleContext(SelectEleContext.class,i);
		}
		public SelectElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitSelectElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElseContext selectElse() throws RecognitionException {
		SelectElseContext _localctx = new SelectElseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(51);
				((SelectElseContext)_localctx).star = match(T__2);
				}
				break;
			case ID:
				{
				setState(52);
				selectEle();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(55);
				match(T__3);
				setState(56);
				selectEle();
				}
				}
				setState(61);
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
			setState(62);
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
			setState(64);
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
			setState(66);
			match(T__4);
			setState(67);
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
		public LogicExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExp; }
	 
		public LogicExpContext() { }
		public void copyFrom(LogicExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OtherExprContext extends LogicExpContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public InOpContext inOp() {
			return getRuleContext(InOpContext.class,0);
		}
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public OtherExprContext(LogicExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitOtherExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentsExprContext extends LogicExpContext {
		public LogicExpContext logicExp() {
			return getRuleContext(LogicExpContext.class,0);
		}
		public ParentsExprContext(LogicExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitParentsExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprContext extends LogicExpContext {
		public List<LogicExpContext> logicExp() {
			return getRuleContexts(LogicExpContext.class);
		}
		public LogicExpContext logicExp(int i) {
			return getRuleContext(LogicExpContext.class,i);
		}
		public LogicOpContext logicOp() {
			return getRuleContext(LogicOpContext.class,0);
		}
		public ExprContext(LogicExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CmpExprContext extends LogicExpContext {
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public CmpOpContext cmpOp() {
			return getRuleContext(CmpOpContext.class,0);
		}
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public CmpExprContext(LogicExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitCmpExpr(this);
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
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new CmpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(70);
				commonName();
				setState(71);
				cmpOp();
				setState(72);
				val();
				}
				break;
			case 2:
				{
				_localctx = new OtherExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				commonName();
				setState(75);
				inOp();
				setState(76);
				match(T__5);
				setState(77);
				val();
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(78);
					match(T__3);
					setState(79);
					val();
					}
					}
					setState(84);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(85);
				match(T__6);
				}
				break;
			case 3:
				{
				_localctx = new ParentsExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				match(T__5);
				setState(88);
				logicExp(0);
				setState(89);
				match(T__6);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(new LogicExpContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicExp);
					setState(93);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(94);
					logicOp();
					setState(95);
					logicExp(5);
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class InOpContext extends ParserRuleContext {
		public InOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitInOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InOpContext inOp() throws RecognitionException {
		InOpContext _localctx = new InOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_inOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
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

	public static class OrderByContext extends ParserRuleContext {
		public Token sort;
		public CommonNameContext commonName() {
			return getRuleContext(CommonNameContext.class,0);
		}
		public OrderByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderBy; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitOrderBy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByContext orderBy() throws RecognitionException {
		OrderByContext _localctx = new OrderByContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_orderBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__9);
			setState(105);
			commonName();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10 || _la==T__11) {
				{
				{
				setState(106);
				((OrderByContext)_localctx).sort = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
					((OrderByContext)_localctx).sort = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(111);
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

	public static class LimitContext extends ParserRuleContext {
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public List<SizeContext> size() {
			return getRuleContexts(SizeContext.class);
		}
		public SizeContext size(int i) {
			return getRuleContext(SizeContext.class,i);
		}
		public LimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitContext limit() throws RecognitionException {
		LimitContext _localctx = new LimitContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_limit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__12);
			setState(113);
			offset();
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(114);
				match(T__3);
				setState(115);
				size();
				}
				}
				setState(120);
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

	public static class OffsetContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(SelectParser.DECIMAL_LITERAL, 0); }
		public OffsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offset; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitOffset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OffsetContext offset() throws RecognitionException {
		OffsetContext _localctx = new OffsetContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_offset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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

	public static class SizeContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(SelectParser.DECIMAL_LITERAL, 0); }
		public SizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_size; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelectVisitor ) return ((SelectVisitor<? extends T>)visitor).visitSize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SizeContext size() throws RecognitionException {
		SizeContext _localctx = new SizeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_size);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
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
		enterRule(_localctx, 24, RULE_val);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				commonName();
				}
				break;
			case TEXT_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				textLiteral();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
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
		enterRule(_localctx, 26, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
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
		enterRule(_localctx, 28, RULE_textLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
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
		enterRule(_localctx, 30, RULE_logicOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			_la = _input.LA(1);
			if ( !(_la==T__13 || _la==T__14) ) {
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
		enterRule(_localctx, 32, RULE_cmpOp);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(T__15);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(T__16);
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				match(T__17);
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				match(T__18);
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				match(T__19);
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				match(T__20);
				setState(142);
				match(T__15);
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
		enterRule(_localctx, 34, RULE_commonName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u0096\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\5\3\61\n\3\3\3\5\3"+
		"\64\n\3\3\4\3\4\5\48\n\4\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bS\n\b\f"+
		"\b\16\bV\13\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b^\n\b\3\b\3\b\3\b\3\b\7\bd\n"+
		"\b\f\b\16\bg\13\b\3\t\3\t\3\n\3\n\3\n\7\nn\n\n\f\n\16\nq\13\n\3\13\3\13"+
		"\3\13\3\13\7\13w\n\13\f\13\16\13z\13\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\5\16\u0083\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u0092\n\22\3\23\3\23\3\23\2\3\16\24\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$\2\5\3\2\n\13\3\2\r\16\3\2\20\21\2\u0095\2&\3"+
		"\2\2\2\4(\3\2\2\2\6\67\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\fD\3\2\2\2\16]\3"+
		"\2\2\2\20h\3\2\2\2\22j\3\2\2\2\24r\3\2\2\2\26{\3\2\2\2\30}\3\2\2\2\32"+
		"\u0082\3\2\2\2\34\u0084\3\2\2\2\36\u0086\3\2\2\2 \u0088\3\2\2\2\"\u0091"+
		"\3\2\2\2$\u0093\3\2\2\2&\'\5\4\3\2\'\3\3\2\2\2()\7\3\2\2)*\5\6\4\2*+\7"+
		"\4\2\2+-\5\n\6\2,.\5\f\7\2-,\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/\61\5\22\n"+
		"\2\60/\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\64\5\24\13\2\63\62\3\2\2"+
		"\2\63\64\3\2\2\2\64\5\3\2\2\2\658\7\5\2\2\668\5\b\5\2\67\65\3\2\2\2\67"+
		"\66\3\2\2\28=\3\2\2\29:\7\6\2\2:<\5\b\5\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2"+
		"\2=>\3\2\2\2>\7\3\2\2\2?=\3\2\2\2@A\5$\23\2A\t\3\2\2\2BC\5$\23\2C\13\3"+
		"\2\2\2DE\7\7\2\2EF\5\16\b\2F\r\3\2\2\2GH\b\b\1\2HI\5$\23\2IJ\5\"\22\2"+
		"JK\5\32\16\2K^\3\2\2\2LM\5$\23\2MN\5\20\t\2NO\7\b\2\2OT\5\32\16\2PQ\7"+
		"\6\2\2QS\5\32\16\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2V"+
		"T\3\2\2\2WX\7\t\2\2X^\3\2\2\2YZ\7\b\2\2Z[\5\16\b\2[\\\7\t\2\2\\^\3\2\2"+
		"\2]G\3\2\2\2]L\3\2\2\2]Y\3\2\2\2^e\3\2\2\2_`\f\6\2\2`a\5 \21\2ab\5\16"+
		"\b\7bd\3\2\2\2c_\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\17\3\2\2\2ge\3"+
		"\2\2\2hi\t\2\2\2i\21\3\2\2\2jk\7\f\2\2ko\5$\23\2ln\t\3\2\2ml\3\2\2\2n"+
		"q\3\2\2\2om\3\2\2\2op\3\2\2\2p\23\3\2\2\2qo\3\2\2\2rs\7\17\2\2sx\5\26"+
		"\f\2tu\7\6\2\2uw\5\30\r\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\25"+
		"\3\2\2\2zx\3\2\2\2{|\7\33\2\2|\27\3\2\2\2}~\7\33\2\2~\31\3\2\2\2\177\u0083"+
		"\5$\23\2\u0080\u0083\5\36\20\2\u0081\u0083\5\34\17\2\u0082\177\3\2\2\2"+
		"\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\33\3\2\2\2\u0084\u0085"+
		"\7\33\2\2\u0085\35\3\2\2\2\u0086\u0087\7\31\2\2\u0087\37\3\2\2\2\u0088"+
		"\u0089\t\4\2\2\u0089!\3\2\2\2\u008a\u0092\7\22\2\2\u008b\u0092\7\23\2"+
		"\2\u008c\u0092\7\24\2\2\u008d\u0092\7\25\2\2\u008e\u0092\7\26\2\2\u008f"+
		"\u0090\7\27\2\2\u0090\u0092\7\22\2\2\u0091\u008a\3\2\2\2\u0091\u008b\3"+
		"\2\2\2\u0091\u008c\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u008e\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092#\3\2\2\2\u0093\u0094\7\30\2\2\u0094%\3\2\2\2\16-"+
		"\60\63\67=T]eox\u0082\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}