
public class EvalVistor extends CalcBaseVisitor<Integer> {

  @Override
  public Integer visitPrintExpr(CalcParser.PrintExprContext ctx) {
    Integer val = visit(ctx.expr());
    System.out.println(val);
    return 0;
  }

  @Override
  public Integer visitMulDiv(CalcParser.MulDivContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));

    boolean mul = ctx.op.getType() == CalcParser.MUL;
    return mul ? left * right : left / right;
  }

  @Override
  public Integer visitAddSub(CalcParser.AddSubContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));

    boolean add = ctx.op.getType() == CalcParser.ADD;
    return add ? left + right : left - right;
  }

  @Override
  public Integer visitInt(CalcParser.IntContext ctx) {
    return Integer.valueOf(ctx.INT().getText());
  }

  @Override
  public Integer visitParents(CalcParser.ParentsContext ctx) {
    return visit(ctx.expr());
  }
}
