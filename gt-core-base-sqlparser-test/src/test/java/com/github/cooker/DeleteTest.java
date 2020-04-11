package com.github.cooker;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import org.junit.Test;

/**
 * grant
 * 3/4/2020 2:32 PM
 * 描述：
 */
public class DeleteTest {

    @Test
    public void findTable() throws JSQLParserException {
        Statement st = CCJSqlParserUtil.parse("delete from aa where id = 12 or a=ss");
        Delete delete = (Delete) st;
        Table table = delete.getTable();
        Expression expression = delete.getWhere();
        ExpressionDeParser expressionDeParser = new ExpressionDeParser();
        expression.accept(expressionDeParser);
        System.out.println(table.getName());
    }
}
