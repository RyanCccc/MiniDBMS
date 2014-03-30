package mysql;

public class DeleteCommand extends Command {
	public String tableName;
	public String condition;
	public DeleteCommand(String tableName, String condition)
	{
		this.tableName = tableName;
		this.condition = condition;
	}
	
	@Override
	public void execute()
	{
		
	}
}
