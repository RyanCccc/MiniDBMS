package mysql;

import java.util.ArrayList;
import java.util.List;

public class Table {
	public String tableName;
	public List<Tuple> tuples;
	public Table(String tableName)
	{
		this.tableName = tableName;
		tuples = new ArrayList<Tuple>();
	}
}
