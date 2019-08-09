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
	 * Visit a parse tree produced by {@link SelectParser#selectEles}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectEles(SelectParser.SelectElesContext ctx);
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
	 * Visit a parse tree produced by {@link SelectParser#logicExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExp(SelectParser.LogicExpContext ctx);
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