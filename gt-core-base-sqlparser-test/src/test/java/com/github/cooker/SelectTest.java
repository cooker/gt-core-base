package com.github.cooker;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;


/**
 * grant
 * 3/4/2020 2:31 PM
 * 描述：
 */
public class SelectTest {

    @Test
    public void findTable() throws JSQLParserException {
        String sql = "select * from haha,ww left JOIN aa";
        Select st = (Select)CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = (PlainSelect) st.getSelectBody();
        Table table = (Table) plainSelect.getFromItem();
        table.setName("hxxx");
        table.setAlias(new Alias("t"));
        TablesNamesFinder finder = new TablesNamesFinder();
        finder.getTableList(st).forEach(System.out::println);
        System.out.println(st.toString());
    }
}
