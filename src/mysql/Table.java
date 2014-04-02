package mysql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable{
	public String tableName;
	public List<Tuple> tuples;
	public Schema schema;
	public Table(String tableName)
	{
		this.tableName = tableName;
		tuples = new ArrayList<Tuple>();
	}
}
