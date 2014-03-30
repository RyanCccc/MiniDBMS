package mysql;

import mysql.User.Type;

public class CreateUserCommand extends Command {
	String userName;
	User.Type userType;
	
	public CreateUserCommand(String userName, Type userType)
	{
		this.userName = userName;
		this.userType = userType;
	}
	
	@Override
	public void execute()
	{
		DBManager mng = DBManager.getDBManager();
		User user = new User(this.userName, this.userType);
		mng.users.add(user);
	}
}
