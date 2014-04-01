package mysql;

import parser.ParseException;

public class HelpDescribeCommand extends Command {
	String tableName;
	public HelpDescribeCommand(String tableName)
	{
		this.tableName = tableName;
	}
	
	@Override
	public void execute() throws ParseException
	{
		Schema schema = DBManager.getDBManager().getSchema(this.tableName);
		if (schema == null) {
			throw new ParseException("No table named " + this.tableName);
		}
		String line;
		for(Attribute attr : schema.attributes)
		{
			line = attr.toString();
			if (schema.primaryKeys.contains(attr.name)) {
				line += " -- primary key";
			}
			if (schema.isForeignKey(attr.name)) {
				ForeignKey foreignKey = schema.getForeignKey(attr.name);
				line += " -- foreign key references " + foreignKey.refTableName + "(" + foreignKey.refAttrName + ")";
			}
			if (!attr.constraint.equals("")) {
				line += " -- " + attr.constraint;
			}
			System.out.println(line);
		}
	}
}
