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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		ID=18, TEXT_STRING=19, ID_LITERAL=20, DECIMAL_LITERAL=21, WS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"ID", "TEXT_STRING", "ID_LITERAL", "DECIMAL_LITERAL", "HEX_DIGIT", "DEC_DIGIT", 
			"LETTER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'select'", "'from'", "'*'", "','", "'where'", "'not'", "'in'", 
			"'('", "')'", "'and'", "'or'", "'='", "'>'", "'<'", "'<='", "'>='", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "ID", "TEXT_STRING", "ID_LITERAL", 
			"DECIMAL_LITERAL", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00a2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\7\23n\n\23\f\23\16\23"+
		"q\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24{\n\24\f\24\16\24"+
		"~\13\24\3\24\3\24\3\25\3\25\3\25\5\25\u0085\n\25\3\25\3\25\3\25\7\25\u008a"+
		"\n\25\f\25\16\25\u008d\13\25\5\25\u008f\n\25\3\26\6\26\u0092\n\26\r\26"+
		"\16\26\u0093\3\27\3\27\3\30\3\30\3\31\3\31\3\32\6\32\u009d\n\32\r\32\16"+
		"\32\u009e\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\63"+
		"\30\3\2\n\6\2&&C\\aac|\7\2&&\62;C\\aac|\3\2))\4\2BBaa\4\2\62;CH\3\2\62"+
		";\4\2C\\c|\5\2\13\f\17\17\"\"\2\u00aa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5<\3\2\2\2\7A\3\2\2\2\tC\3"+
		"\2\2\2\13E\3\2\2\2\rK\3\2\2\2\17O\3\2\2\2\21R\3\2\2\2\23T\3\2\2\2\25V"+
		"\3\2\2\2\27Z\3\2\2\2\31]\3\2\2\2\33_\3\2\2\2\35a\3\2\2\2\37c\3\2\2\2!"+
		"f\3\2\2\2#i\3\2\2\2%k\3\2\2\2\'r\3\2\2\2)\u008e\3\2\2\2+\u0091\3\2\2\2"+
		"-\u0095\3\2\2\2/\u0097\3\2\2\2\61\u0099\3\2\2\2\63\u009c\3\2\2\2\65\66"+
		"\7u\2\2\66\67\7g\2\2\678\7n\2\289\7g\2\29:\7e\2\2:;\7v\2\2;\4\3\2\2\2"+
		"<=\7h\2\2=>\7t\2\2>?\7q\2\2?@\7o\2\2@\6\3\2\2\2AB\7,\2\2B\b\3\2\2\2CD"+
		"\7.\2\2D\n\3\2\2\2EF\7y\2\2FG\7j\2\2GH\7g\2\2HI\7t\2\2IJ\7g\2\2J\f\3\2"+
		"\2\2KL\7p\2\2LM\7q\2\2MN\7v\2\2N\16\3\2\2\2OP\7k\2\2PQ\7p\2\2Q\20\3\2"+
		"\2\2RS\7*\2\2S\22\3\2\2\2TU\7+\2\2U\24\3\2\2\2VW\7c\2\2WX\7p\2\2XY\7f"+
		"\2\2Y\26\3\2\2\2Z[\7q\2\2[\\\7t\2\2\\\30\3\2\2\2]^\7?\2\2^\32\3\2\2\2"+
		"_`\7@\2\2`\34\3\2\2\2ab\7>\2\2b\36\3\2\2\2cd\7>\2\2de\7?\2\2e \3\2\2\2"+
		"fg\7@\2\2gh\7?\2\2h\"\3\2\2\2ij\7#\2\2j$\3\2\2\2ko\t\2\2\2ln\t\3\2\2m"+
		"l\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2p&\3\2\2\2qo\3\2\2\2r|\7)\2\2s"+
		"t\7^\2\2t{\7^\2\2uv\7)\2\2v{\7)\2\2wx\7^\2\2x{\7)\2\2y{\n\4\2\2zs\3\2"+
		"\2\2zu\3\2\2\2zw\3\2\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177"+
		"\3\2\2\2~|\3\2\2\2\177\u0080\7)\2\2\u0080(\3\2\2\2\u0081\u008f\7,\2\2"+
		"\u0082\u0085\t\5\2\2\u0083\u0085\5\61\31\2\u0084\u0082\3\2\2\2\u0084\u0083"+
		"\3\2\2\2\u0085\u008b\3\2\2\2\u0086\u008a\5\61\31\2\u0087\u008a\5/\30\2"+
		"\u0088\u008a\7a\2\2\u0089\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u0088"+
		"\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0081\3\2\2\2\u008e\u0084\3\2"+
		"\2\2\u008f*\3\2\2\2\u0090\u0092\5/\30\2\u0091\u0090\3\2\2\2\u0092\u0093"+
		"\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094,\3\2\2\2\u0095"+
		"\u0096\t\6\2\2\u0096.\3\2\2\2\u0097\u0098\t\7\2\2\u0098\60\3\2\2\2\u0099"+
		"\u009a\t\b\2\2\u009a\62\3\2\2\2\u009b\u009d\t\t\2\2\u009c\u009b\3\2\2"+
		"\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\b\32\2\2\u00a1\64\3\2\2\2\f\2oz|\u0084\u0089\u008b"+
		"\u008e\u0093\u009e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}