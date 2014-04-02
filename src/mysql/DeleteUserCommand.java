package mysql;

import parser.ParseException;

public class DeleteUserCommand extends Command {
	public String userName;
	public DeleteUserCommand(String userName)
	{
		this.userName = userName;
	}
	
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		User user = mng.getUser(this.userName);
		if (user==null) {
			throw new ParseException("Username " + this.userName + " does not exist");
		}
		if (user.equals(mng.currentUser))
		{
			throw new ParseException("You cannot delete yourself");
		}
		mng.users.remove(user);
		System.out.println("User deleted successfully");
	}
}
