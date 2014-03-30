package mysql;

import java.util.List;

public class Schema {
	public String tableName;
	public List<Attribute> attributes;
	public List<String> primaryKeys;
	public List<ForeignKey> foreignKeys;
	public Schema(String tableName, List<Attribute> attributes, List<String> primaryKeys, List<ForeignKey> foreignKeys){
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
}