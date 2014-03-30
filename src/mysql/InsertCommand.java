package mysql;

import java.util.List;

public class InsertCommand extends Command {
	public String tableName;
	public List<Value> values;
	public InsertCommand(String tableName, List<Value> values)
	{
		this.tableName = tableName;
		this.values = values;
	}
	
	@Override
	public void execute()
	{
		for(Value v : this.values)
		{
			System.out.println(v.getValue());
		}
	}
}
