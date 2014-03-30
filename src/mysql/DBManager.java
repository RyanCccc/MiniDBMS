package mysql;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
	private static DBManager me;
	public List<Table> tables = new ArrayList<Table>();
	public List<Schema> schemas = new ArrayList<Schema>();
	public static DBManager getDBManager()
	{
		if (me==null) {
			me = new DBManager();
		}
		return me;
	}
	
	public Schema getSchema(String tableName)
	{
		for(Schema schema : this.schemas)
		{
			if (schema.tableName.equals(tableName)) {
				return schema;
			}
		}
		return null;
	}
}
