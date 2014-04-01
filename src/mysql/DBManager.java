package mysql;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
	private static DBManager me;
	public List<Table> tables = new ArrayList<Table>();
	public List<Schema> schemas = new ArrayList<Schema>();
	public List<User> users = new ArrayList<User>();
	public User currentUser;
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
	
	public Table getTable(String tableName)
	{
		for(Table table : this.tables)
		{
			if (table.tableName.equals(tableName)) {
				return table;
			}
		}
		return null;
	}
	
	public User getUser(String username)
	{
		for(User user : this.users){
			if (user.userName.equals(username)) {
				return user;
			}
		}
		return null;
	}
}
