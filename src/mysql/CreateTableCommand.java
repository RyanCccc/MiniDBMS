package mysql;

import java.util.List;

import parser.ParseException;

public class CreateTableCommand extends Command {
	public String tableName;
	public List<Attribute> attributes;
	public List<String> primaryKeys;
	public List<ForeignKey> foreignKeys;
	public CreateTableCommand(String tableName, List<Attribute> attributes, List<String> primaryKeys, List<ForeignKey> foreignKeys){
		this.tableName = tableName;
		this.attributes = attributes;
		this.primaryKeys = primaryKeys;
		
		this.foreignKeys = foreignKeys;
	}
	
	public boolean isKeyInAttr(String keyName)
	{
		boolean foundKey = false;
		for(Attribute attr : attributes)
		{
			if (attr.name.equals(keyName)) {
				foundKey = true;
				break;
			}
		}
		return foundKey;
	}
	
	@Override
	public void execute() throws ParseException
	{
		for(String keyName : primaryKeys)
		{
			if (!isKeyInAttr(keyName)) {
				throw new ParseException("No key for primary key"+keyName);
			}
		}
		for (ForeignKey foreignKey : this.foreignKeys) {
			if (!isKeyInAttr(foreignKey.localAttrName)) {
				throw new ParseException("No key for foreign key"+foreignKey.localAttrName);
			}
			Schema schema = DBManager.getDBManager().getSchema(foreignKey.refTableName);
			if (schema==null) {
				throw new ParseException("No Table " + foreignKey.refTableName + " for foreign key"+foreignKey.localAttrName);
			}
			if (!schema.isKeyInAttr(foreignKey.refAttrName)) {
				throw new ParseException("No Referred attributes " + foreignKey.refAttrName + " for foreign key"+foreignKey.localAttrName);
			}
		}
		Schema schema = new Schema(this.tableName, this.attributes, this.primaryKeys, this.foreignKeys);
		DBManager.getDBManager().schemas.add(schema);
	}
}
