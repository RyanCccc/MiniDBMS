package mysql;

public class HelpTableCommand extends Command {
	@Override
	public void execute()
	{
		DBManager mng = DBManager.getDBManager();
		for(Schema schema : mng.schemas)
		{
			System.out.println(schema.tableName);
		}
	}
}
