package mysql;

import parser.ParseException;

public class DropTableCommand extends Command {
	public String tableName;
	public DropTableCommand(String tableName){
		this.tableName = tableName;
	}
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		Schema removeSchema =  mng.getSchema(this.tableName);
		Table removeTable = mng.getTable(this.tableName);
		if (removeSchema == null || removeTable == null) {
			throw new ParseException("No table called "+this.tableName);
		}
		
		//Check foreign key constraint
		for(Schema schema : mng.schemas)
		{
			if (schema.equals(removeSchema)) {
				continue;
			}
			for(ForeignKey otherForeignKey : schema.foreignKeys)
			{
				if (removeSchema.attrs.containsKey(otherForeignKey.refAttrName)) {
					throw new ParseException("Foreign key constraints violated");
				}
			}
		}
		
		mng.schemas.remove(removeSchema);
		mng.tables.remove(removeTable);
		System.out.println("Table dropped successfully");
	}
}
