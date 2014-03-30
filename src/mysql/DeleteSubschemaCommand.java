package mysql;

public class DeleteSubschemaCommand extends Command {
	public String tableName;
	
	public DeleteSubschemaCommand(String tableName)
	{
		this.tableName = tableName;
	}
	
	@Override
	public void execute()
	{
		
	}
}
