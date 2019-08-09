import select.SelectBaseVisitor;
import select.SelectParser.CmpOpContext;
import select.SelectParser.CommonNameContext;
import select.SelectParser.DecimalLiteralContext;
import select.SelectParser.LogicExpContext;
import select.SelectParser.LogicOpContext;
import select.SelectParser.QueryContext;
import select.SelectParser.SelectEleContext;
import select.SelectParser.SelectElesContext;
import select.SelectParser.StatContext;
import select.SelectParser.TableSrcContext;
import select.SelectParser.TextLiteralContext;
import select.SelectParser.ValContext;
import select.SelectParser.WhereContext;

public class EvalVisitor extends SelectBaseVisitor<String> {

  private String table;

  @Override
  public String visitStat(StatContext ctx) {
    super.visitStat(ctx);

    return table;
  }

  @Override
  public String visitQuery(QueryContext ctx) {
    return super.visitQuery(ctx);
  }

  @Override
  public String visitSelectEles(SelectElesContext ctx) {
    return super.visitSelectEles(ctx);
  }

  @Override
  public String visitSelectEle(SelectEleContext ctx) {
    return super.visitSelectEle(ctx);
  }

  @Override
  public String visitTableSrc(TableSrcContext ctx) {
    table = super.visitTableSrc(ctx);
    return table;
  }

  @Override
  public String visitWhere(WhereContext ctx) {
    return super.visitWhere(ctx);
  }

  @Override
  public String visitLogicExp(LogicExpContext ctx) {
    return super.visitLogicExp(ctx);
  }

  @Override
  public String visitVal(ValContext ctx) {
    return super.visitVal(ctx);
  }

  @Override
  public String visitDecimalLiteral(DecimalLiteralContext ctx) {
    return super.visitDecimalLiteral(ctx);
  }

  @Override
  public String visitTextLiteral(TextLiteralContext ctx) {
    return super.visitTextLiteral(ctx);
  }

  @Override
  public String visitLogicOp(LogicOpContext ctx) {
    return super.visitLogicOp(ctx);
  }

  @Override
  public String visitCmpOp(CmpOpContext ctx) {
    return super.visitCmpOp(ctx);
  }

  @Override
  public String visitCommonName(CommonNameContext ctx) {
    return ctx.getText();
  }
}
