package mysql;

import java.util.List;

import parser.ParseException;

public class CreateSubschemaCommand extends Command {
	String tableName;
	List<String> attrNames;
	
	public CreateSubschemaCommand(String tableName, List<String> attrNames)
	{
		this.tableName = tableName;
		this.attrNames = attrNames;
	}
	
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		Schema schema = mng.getSchema(this.tableName);
		if (schema == null) {
			throw new ParseException("No table named "+this.tableName);
		}
		schema.addVisibleAttrs(this.attrNames);
		System.out.println("Subschema created successfully");
	}
}
