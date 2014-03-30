package test;

import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.*;
import net.sf.jsqlparser.statement.*;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;


public class ParserTest {
	public static void main(String[] args) throws JSQLParserException{
//		Statement statement = CCJSqlParserUtil.parse("SELECT * FROM MY_TABLE1 JOIN MY_TABLE2 ON a=b");
//		Select selectStatement = (Select) statement;
//		TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//		List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
//		System.out.println(tableList);
//		CCJSqlParserUtil.parse("CREATE TABLE DEPARTMENT(deptid INT CHECK(deptid>0 AND deptid<100), dname CHAR(30), location CHAR(30), PRIMARY KEY(deptid));");
//		
//		List<ColumnDefinition> columnList = createStatement.getColumnDefinitions();
//		for (ColumnDefinition columnDefinition : columnList) {
//			System.out.println(columnDefinition.getColDataType().getDataType());
//			System.out.println(columnDefinition.getColDataType().getArgumentsStringList());
//			System.out.println(columnDefinition.getColumnSpecStrings());
//			System.out.println(columnDefinition.getColumnName());
//		}
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = "40+2 > 0";
	    try {
			System.out.println(engine.eval(foo));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
