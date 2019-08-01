import java.util.Arrays;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

public class Main {

  public static void main(String[] args) {
    String exp = Arrays.stream(args).collect(Collectors.joining(""));
    CharStream input = new ANTLRInputStream(exp + "\n");
    CalcLexer lexer = new CalcLexer(input);
    CommonTokenStream stream = new CommonTokenStream(lexer);
    CalcParser parser = new CalcParser(stream);
    ParserRuleContext ctx = parser.stat();
    Integer code = new EvalVistor().visit(ctx);
    System.exit(code);
  }
}
