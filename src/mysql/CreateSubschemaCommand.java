package mysql;

import java.util.List;

public class CreateSubschemaCommand extends Command {
	String tableName;
	List<String> attrNames;
	
	public CreateSubschemaCommand(String tableName, List<String> attrNames)
	{
		this.tableName = tableName;
		this.attrNames = attrNames;
	}
	
	@Override
	public void execute()
	{
		System.out.println(this.attrNames);
	}
}
