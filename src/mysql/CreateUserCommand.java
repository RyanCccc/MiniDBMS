package mysql;

import parser.ParseException;
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
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		for(User user : mng.users){
			if (user.userName.equals(this.userName)) {
				throw new ParseException("User name " + this.userName + " already exists");
			}
		}
		User user = new User(this.userName, this.userType);
		mng.users.add(user);
		System.out.println("User created successfully");
	}
}
