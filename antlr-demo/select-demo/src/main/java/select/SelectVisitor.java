// Generated from Select.g4 by ANTLR 4.7.2

package select;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SelectParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SelectVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SelectParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(SelectParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(SelectParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#selectElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElse(SelectParser.SelectElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#selectEle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectEle(SelectParser.SelectEleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#tableSrc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSrc(SelectParser.TableSrcContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(SelectParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by the {@code otherExpr}
	 * labeled alternative in {@link SelectParser#logicExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherExpr(SelectParser.OtherExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentsExpr}
	 * labeled alternative in {@link SelectParser#logicExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentsExpr(SelectParser.ParentsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr}
	 * labeled alternative in {@link SelectParser#logicExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(SelectParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmpExpr}
	 * labeled alternative in {@link SelectParser#logicExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpExpr(SelectParser.CmpExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#orderBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderBy(SelectParser.OrderByContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(SelectParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#offset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffset(SelectParser.OffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSize(SelectParser.SizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal(SelectParser.ValContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#decimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(SelectParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#textLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextLiteral(SelectParser.TextLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#logicOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOp(SelectParser.LogicOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#cmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOp(SelectParser.CmpOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelectParser#commonName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonName(SelectParser.CommonNameContext ctx);
}