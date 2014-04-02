package mysql;

import java.util.ArrayList;
import java.util.HashMap;

import parser.ParseException;

public class DeleteSubschemaCommand extends Command {
	public String tableName;
	
	public DeleteSubschemaCommand(String tableName)
	{
		this.tableName = tableName;
	}
	
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		Schema schema = mng.getSchema(this.tableName);
		Table table = mng.getTable(this.tableName);
		if (schema == null || table == null) {
			throw new ParseException("No table named "+this.tableName);
		}
		schema.visibleAttributes = new ArrayList<Attribute>();
		schema.visibleAttrs = new HashMap<String, Integer>();
		table.schema.visibleAttributes = new ArrayList<Attribute>();
		table.schema.visibleAttrs = new HashMap<String, Integer>();
		System.out.println("Subschema deleted successfully");
	}
}
