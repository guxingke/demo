import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.RuleContext;
import select.SelectBaseVisitor;
import select.SelectParser.CmpExprContext;
import select.SelectParser.CommonNameContext;
import select.SelectParser.ExprContext;
import select.SelectParser.LimitContext;
import select.SelectParser.LogicOpContext;
import select.SelectParser.OrderByContext;
import select.SelectParser.OtherExprContext;
import select.SelectParser.ParentsExprContext;
import select.SelectParser.QueryContext;
import select.SelectParser.SelectEleContext;
import select.SelectParser.SelectElseContext;
import select.SelectParser.StatContext;
import select.SelectParser.TableSrcContext;
import select.SelectParser.WhereContext;

public class EvalVisitor extends SelectBaseVisitor<String> {

  private String table;
  private String project;
  private String cond;
  private String sort = "{_id:-1}";
  private int offset = 0;
  private int size = 100;

  @Override
  public String visitStat(StatContext ctx) {
    super.visitStat(ctx);
    if (project == null) {
      return String.format("db.%s.find(%s).sort(%s).skip(%d).limit(%d)", table, cond, sort, offset, size);
    } else {
      return String.format("db.%s.find(%s,%s).sort(%s).skip(%d).limit(%d)", table, cond, project, sort, offset, size);
    }
  }

  @Override
  public String visitQuery(QueryContext ctx) {
    return super.visitQuery(ctx);
  }

  @Override
  public String visitSelectElse(SelectElseContext ctx) {
    List<String> fields = ctx.selectEle().stream().map(super::visit).collect(Collectors.toList());
    String join = String.join(", ", fields);
    project = "{" + join + "}";
    return project;
  }

  @Override
  public String visitSelectEle(SelectEleContext ctx) {
    String text = ctx.getText();
    return String.format("%s:1", text);
  }

  @Override
  public String visitTableSrc(TableSrcContext ctx) {
    table = super.visitTableSrc(ctx);
    return table;
  }

  @Override
  public String visitWhere(WhereContext ctx) {
    cond = super.visitWhere(ctx);
    return cond;
  }

  @Override
  public String visitLogicOp(LogicOpContext ctx) {
    String s = super.visitLogicOp(ctx);
    return s;
  }

  @Override
  public String visitParentsExpr(ParentsExprContext ctx) {
    String s = super.visit(ctx.logicExp());
    return s;
  }

  @Override
  public String visitExpr(ExprContext ctx) {
    String op = ctx.logicOp().getText();
    String left = super.visit(ctx.logicExp(0));
    String right = super.visit(ctx.logicExp(1));
    return String.format("{$%s: [%s,%s]}", op, left, right);
  }

  @Override
  public String visitCmpExpr(CmpExprContext ctx) {
    String op = ctx.cmpOp().getText();
    String name = ctx.commonName().getText();
    String text = ctx.val().getText();

    switch (op) {
      case "=":
        return String.format("{%s: {$eq: %s}}", name, text);
      case ">":
        return String.format("{%s: {$gt: %s}}", name, text);
      case ">=":
        return String.format("{%s: {$gte: %s}}", name, text);
      case "<":
        return String.format("{%s: {$lt: %s}}", name, text);
      case "<=":
        return String.format("{%s: {$lte: %s}}", name, text);
      case "!=":
        return String.format("{%s: {$ne: %s}}", name, text);
      default:
        return String.format("{%s: {$eq: %s}}", name, text);
    }
  }

  @Override
  public String visitOtherExpr(OtherExprContext ctx) {
    String text = ctx.inOp().getText();
    String op = "$in";
    if (!text.equalsIgnoreCase("in")) {
      op = "$nin";
    }
    String filed = ctx.commonName().getText();
    String val = ctx.val().stream()
        .map(RuleContext::getText)
        .collect(Collectors.joining(",", "[", "]"));
    return String.format("{%s: {%s:%s}}", filed, op, val);
  }

  @Override
  public String visitOrderBy(OrderByContext ctx) {
    // default
    // .sort({ _id: -1})
    String filed = this.visit(ctx.getChild(1));
    int sortI = 1;
    if (ctx.getChildCount() > 2) {
      String visit = ctx.getChild(2).getText();
      if (Objects.equals(visit, "desc")) {
        sortI = -1;
      }
    }

    sort = String.format("{%s:%d}", filed, sortI);
    return sort;
  }

  @Override
  public String visitLimit(LimitContext ctx) {
    if (ctx.getChildCount() == 2) {
      size = Integer.parseInt(ctx.getChild(1).getText());
    } else {
      offset = Integer.parseInt(ctx.getChild(1).getText());
      size = Integer.parseInt(ctx.getChild(3).getText());
    }

    return "";
  }

  @Override
  public String visitCommonName(CommonNameContext ctx) {
    return ctx.getText();
  }
}
