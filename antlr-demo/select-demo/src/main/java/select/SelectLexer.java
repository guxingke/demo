// Generated from Select.g4 by ANTLR 4.7.2

package select;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SelectLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, TEXT_STRING=17, 
		ID_LITERAL=18, DECIMAL_LITERAL=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ID", "TEXT_STRING", 
			"ID_LITERAL", "DECIMAL_LITERAL", "HEX_DIGIT", "DEC_DIGIT", "LETTER", 
			"WS"
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


	public SelectLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Select.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0098\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\7"+
		"\21d\n\21\f\21\16\21g\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7"+
		"\22q\n\22\f\22\16\22t\13\22\3\22\3\22\3\23\3\23\3\23\5\23{\n\23\3\23\3"+
		"\23\3\23\7\23\u0080\n\23\f\23\16\23\u0083\13\23\5\23\u0085\n\23\3\24\6"+
		"\24\u0088\n\24\r\24\16\24\u0089\3\25\3\25\3\26\3\26\3\27\3\27\3\30\6\30"+
		"\u0093\n\30\r\30\16\30\u0094\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\2"+
		"+\2-\2/\26\3\2\n\6\2&&C\\aac|\7\2&&\62;C\\aac|\3\2))\4\2BBaa\4\2\62;C"+
		"H\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\2\u00a0\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2/\3\2\2\2\3\61\3\2\2\2\58\3\2\2\2\7=\3\2\2\2\t?\3\2\2\2\13A\3\2"+
		"\2\2\rG\3\2\2\2\17K\3\2\2\2\21N\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27V\3"+
		"\2\2\2\31Y\3\2\2\2\33[\3\2\2\2\35]\3\2\2\2\37_\3\2\2\2!a\3\2\2\2#h\3\2"+
		"\2\2%\u0084\3\2\2\2\'\u0087\3\2\2\2)\u008b\3\2\2\2+\u008d\3\2\2\2-\u008f"+
		"\3\2\2\2/\u0092\3\2\2\2\61\62\7u\2\2\62\63\7g\2\2\63\64\7n\2\2\64\65\7"+
		"g\2\2\65\66\7e\2\2\66\67\7v\2\2\67\4\3\2\2\289\7h\2\29:\7t\2\2:;\7q\2"+
		"\2;<\7o\2\2<\6\3\2\2\2=>\7,\2\2>\b\3\2\2\2?@\7.\2\2@\n\3\2\2\2AB\7y\2"+
		"\2BC\7j\2\2CD\7g\2\2DE\7t\2\2EF\7g\2\2F\f\3\2\2\2GH\7p\2\2HI\7q\2\2IJ"+
		"\7v\2\2J\16\3\2\2\2KL\7k\2\2LM\7p\2\2M\20\3\2\2\2NO\7*\2\2O\22\3\2\2\2"+
		"PQ\7+\2\2Q\24\3\2\2\2RS\7c\2\2ST\7p\2\2TU\7f\2\2U\26\3\2\2\2VW\7q\2\2"+
		"WX\7t\2\2X\30\3\2\2\2YZ\7?\2\2Z\32\3\2\2\2[\\\7@\2\2\\\34\3\2\2\2]^\7"+
		">\2\2^\36\3\2\2\2_`\7#\2\2` \3\2\2\2ae\t\2\2\2bd\t\3\2\2cb\3\2\2\2dg\3"+
		"\2\2\2ec\3\2\2\2ef\3\2\2\2f\"\3\2\2\2ge\3\2\2\2hr\7)\2\2ij\7^\2\2jq\7"+
		"^\2\2kl\7)\2\2lq\7)\2\2mn\7^\2\2nq\7)\2\2oq\n\4\2\2pi\3\2\2\2pk\3\2\2"+
		"\2pm\3\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2"+
		"\2uv\7)\2\2v$\3\2\2\2w\u0085\7,\2\2x{\t\5\2\2y{\5-\27\2zx\3\2\2\2zy\3"+
		"\2\2\2{\u0081\3\2\2\2|\u0080\5-\27\2}\u0080\5+\26\2~\u0080\7a\2\2\177"+
		"|\3\2\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2"+
		"\2\2\u0081\u0082\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0084"+
		"w\3\2\2\2\u0084z\3\2\2\2\u0085&\3\2\2\2\u0086\u0088\5+\26\2\u0087\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"(\3\2\2\2\u008b\u008c\t\6\2\2\u008c*\3\2\2\2\u008d\u008e\t\7\2\2\u008e"+
		",\3\2\2\2\u008f\u0090\t\b\2\2\u0090.\3\2\2\2\u0091\u0093\t\t\2\2\u0092"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\30\2\2\u0097\60\3\2\2\2\f\2epr"+
		"z\177\u0081\u0084\u0089\u0094\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}