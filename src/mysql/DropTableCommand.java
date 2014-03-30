package mysql;

import java.util.List;

import parser.ParseException;

public class DropTableCommand extends Command {
	public String tableName;
	public DropTableCommand(String tableName){
		this.tableName = tableName;
	}
	@Override
	public void execute() throws ParseException
	{
		List<Schema> schemas = DBManager.getDBManager().schemas;
		Schema removeSchema = null;
		for (Schema schema : schemas) {
			if (schema.tableName.equals(this.tableName)) {
				removeSchema = schema;
				break;
			}
		}
		if (removeSchema == null) {
			throw new ParseException("No table called "+this.tableName);
		}
		schemas.remove(removeSchema);
	}
}
