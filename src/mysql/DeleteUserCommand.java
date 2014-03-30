package mysql;

public class DeleteUserCommand extends Command {
	public String userName;
	public DeleteUserCommand(String userName)
	{
		this.userName = userName;
	}
	
	@Override
	public void execute()
	{
		
	}
}
