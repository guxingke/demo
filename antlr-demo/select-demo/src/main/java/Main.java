import java.util.Arrays;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import select.SelectLexer;
import select.SelectParser;

public class Main {

  public static void main(String[] args) {
    String exp = Arrays.stream(args).collect(Collectors.joining(" "));
    CharStream input = new ANTLRInputStream(exp + "\n");
    SelectLexer lexer = new SelectLexer(input);
    CommonTokenStream stream = new CommonTokenStream(lexer);
    SelectParser parser = new SelectParser(stream);
    ParserRuleContext ctx = parser.stat();
    String code = new EvalVisitor().visit(ctx);
    System.out.println(code);
  }
}
