package mysql;

import parser.ParseException;

public class QuitCommand extends Command{
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		if (mng.currentUser.userType != User.Type.USER_B) {
			mng.saveUsers();
			mng.saveSchema();
			mng.saveTables();
		}
	}
}
