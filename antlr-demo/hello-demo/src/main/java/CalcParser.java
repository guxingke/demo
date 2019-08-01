// Generated from Calc.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcParser extends Parser {

  static {
    RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
  public static final int
      T__0 = 1, T__1 = 2, NEWLINE = 3, INT = 4, WS = 5, MUL = 6, DIV = 7, ADD = 8, SUB = 9;
  public static final int
      RULE_stat = 0, RULE_expr = 1;

  private static String[] makeRuleNames() {
    return new String[]{
        "stat", "expr"
    };
  }

  public static final String[] ruleNames = makeRuleNames();

  private static String[] makeLiteralNames() {
    return new String[]{
        null, "'('", "')'", null, null, null, "'*'", "'/'", "'+'", "'-'"
    };
  }

  private static final String[] _LITERAL_NAMES = makeLiteralNames();

  private static String[] makeSymbolicNames() {
    return new String[]{
        null, null, null, "NEWLINE", "INT", "WS", "MUL", "DIV", "ADD", "SUB"
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
  public String getGrammarFileName() {
    return "Calc.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public CalcParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  public static class StatContext extends ParserRuleContext {

    public StatContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_stat;
    }

    public StatContext() {
    }

    public void copyFrom(StatContext ctx) {
      super.copyFrom(ctx);
    }
  }

  public static class BlankContext extends StatContext {

    public TerminalNode NEWLINE() {
      return getToken(CalcParser.NEWLINE, 0);
    }

    public BlankContext(StatContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitBlank(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class PrintExprContext extends StatContext {

    public ExprContext expr() {
      return getRuleContext(ExprContext.class, 0);
    }

    public TerminalNode NEWLINE() {
      return getToken(CalcParser.NEWLINE, 0);
    }

    public PrintExprContext(StatContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitPrintExpr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final StatContext stat() throws RecognitionException {
    StatContext _localctx = new StatContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_stat);
    try {
      setState(8);
      _errHandler.sync(this);
      switch (_input.LA(1)) {
        case T__0:
        case INT:
          _localctx = new PrintExprContext(_localctx);
          enterOuterAlt(_localctx, 1);
        {
          setState(4);
          expr(0);
          setState(5);
          match(NEWLINE);
        }
        break;
        case NEWLINE:
          _localctx = new BlankContext(_localctx);
          enterOuterAlt(_localctx, 2);
        {
          setState(7);
          match(NEWLINE);
        }
        break;
        default:
          throw new NoViableAltException(this);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExprContext extends ParserRuleContext {

    public ExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_expr;
    }

    public ExprContext() {
    }

    public void copyFrom(ExprContext ctx) {
      super.copyFrom(ctx);
    }
  }

  public static class MulDivContext extends ExprContext {

    public Token op;

    public List<ExprContext> expr() {
      return getRuleContexts(ExprContext.class);
    }

    public ExprContext expr(int i) {
      return getRuleContext(ExprContext.class, i);
    }

    public TerminalNode MUL() {
      return getToken(CalcParser.MUL, 0);
    }

    public TerminalNode DIV() {
      return getToken(CalcParser.DIV, 0);
    }

    public MulDivContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitMulDiv(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AddSubContext extends ExprContext {

    public Token op;

    public List<ExprContext> expr() {
      return getRuleContexts(ExprContext.class);
    }

    public ExprContext expr(int i) {
      return getRuleContext(ExprContext.class, i);
    }

    public TerminalNode ADD() {
      return getToken(CalcParser.ADD, 0);
    }

    public TerminalNode SUB() {
      return getToken(CalcParser.SUB, 0);
    }

    public AddSubContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitAddSub(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IntContext extends ExprContext {

    public TerminalNode INT() {
      return getToken(CalcParser.INT, 0);
    }

    public IntContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitInt(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ParentsContext extends ExprContext {

    public ExprContext expr() {
      return getRuleContext(ExprContext.class, 0);
    }

    public ParentsContext(ExprContext ctx) {
      copyFrom(ctx);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof CalcVisitor) {
        return ((CalcVisitor<? extends T>) visitor).visitParents(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public final ExprContext expr() throws RecognitionException {
    return expr(0);
  }

  private ExprContext expr(int _p) throws RecognitionException {
    ParserRuleContext _parentctx = _ctx;
    int _parentState = getState();
    ExprContext _localctx = new ExprContext(_ctx, _parentState);
    ExprContext _prevctx = _localctx;
    int _startState = 2;
    enterRecursionRule(_localctx, 2, RULE_expr, _p);
    int _la;
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
        setState(16);
        _errHandler.sync(this);
        switch (_input.LA(1)) {
          case INT: {
            _localctx = new IntContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(11);
            match(INT);
          }
          break;
          case T__0: {
            _localctx = new ParentsContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(12);
            match(T__0);
            setState(13);
            expr(0);
            setState(14);
            match(T__1);
          }
          break;
          default:
            throw new NoViableAltException(this);
        }
        _ctx.stop = _input.LT(-1);
        setState(26);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            if (_parseListeners != null) {
              triggerExitRuleEvent();
            }
            _prevctx = _localctx;
            {
              setState(24);
              _errHandler.sync(this);
              switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                case 1: {
                  _localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(18);
                  if (!(precpred(_ctx, 4))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                  }
                  setState(19);
                  ((MulDivContext) _localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if (!(_la == MUL || _la == DIV)) {
                    ((MulDivContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                  } else {
                    if (_input.LA(1) == Token.EOF) {
                      matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                  }
                  setState(20);
                  expr(5);
                }
                break;
                case 2: {
                  _localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expr);
                  setState(21);
                  if (!(precpred(_ctx, 3))) {
                    throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                  }
                  setState(22);
                  ((AddSubContext) _localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if (!(_la == ADD || _la == SUB)) {
                    ((AddSubContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                  } else {
                    if (_input.LA(1) == Token.EOF) {
                      matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                  }
                  setState(23);
                  expr(4);
                }
                break;
              }
            }
          }
          setState(28);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
        }
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      unrollRecursionContexts(_parentctx);
    }
    return _localctx;
  }

  public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
    switch (ruleIndex) {
      case 1:
        return expr_sempred((ExprContext) _localctx, predIndex);
    }
    return true;
  }

  private boolean expr_sempred(ExprContext _localctx, int predIndex) {
    switch (predIndex) {
      case 0:
        return precpred(_ctx, 4);
      case 1:
        return precpred(_ctx, 3);
    }
    return true;
  }

  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13 \4\2\t\2\4\3\t" +
          "\3\3\2\3\2\3\2\3\2\5\2\13\n\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\23\n\3\3\3\3" +
          "\3\3\3\3\3\3\3\3\3\7\3\33\n\3\f\3\16\3\36\13\3\3\3\2\3\4\4\2\4\2\4\3\2" +
          "\b\t\3\2\n\13\2!\2\n\3\2\2\2\4\22\3\2\2\2\6\7\5\4\3\2\7\b\7\5\2\2\b\13" +
          "\3\2\2\2\t\13\7\5\2\2\n\6\3\2\2\2\n\t\3\2\2\2\13\3\3\2\2\2\f\r\b\3\1\2" +
          "\r\23\7\6\2\2\16\17\7\3\2\2\17\20\5\4\3\2\20\21\7\4\2\2\21\23\3\2\2\2" +
          "\22\f\3\2\2\2\22\16\3\2\2\2\23\34\3\2\2\2\24\25\f\6\2\2\25\26\t\2\2\2" +
          "\26\33\5\4\3\7\27\30\f\5\2\2\30\31\t\3\2\2\31\33\5\4\3\6\32\24\3\2\2\2" +
          "\32\27\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\5\3\2\2\2" +
          "\36\34\3\2\2\2\6\n\22\32\34";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}