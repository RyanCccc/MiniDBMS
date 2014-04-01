package mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.ParseException;

public class Schema {
	public String tableName;
	public List<Attribute> attributes;
	public List<Attribute> visibleAttributes;
	public List<String> primaryKeys;
	public List<ForeignKey> foreignKeys;
	public Map<String, Integer> attrs;
	public Map<String, Integer> visibleAttrs;
	public Schema(String tableName, List<Attribute> attributes, List<String> primaryKeys, List<ForeignKey> foreignKeys){
		this.tableName = tableName;
		this.attributes = attributes;
		this.visibleAttributes = new ArrayList<Attribute>();
		this.primaryKeys = primaryKeys;
		this.foreignKeys = foreignKeys;
		this.attrs = new HashMap<>();
		for(int i=0; i<attributes.size(); i++)
		{
			this.attrs.put(attributes.get(i).name, Integer.valueOf(i));
		}
	}
	public boolean isKeyInAttr(String keyName)
	{
		return attrs.containsKey(keyName);
	}
	
	public int getIndexByAttrName(String attrName)
	{
		return this.attrs.get(attrName);
	}
	
	public boolean addVisibleAttrs(List<String> attrNames) throws ParseException
	{
		Map<String, Integer> tmpVisibleAttrs = new HashMap<String, Integer>();
		List<Attribute> tmpVisibleAttributes = new ArrayList<Attribute>();
		for(String attrName : attrNames)
		{
			if (!this.isKeyInAttr(attrName)) {
				throw new ParseException("No matching attribute name " + attrName);
			}else {
				int index = getIndexByAttrName(attrName);
				tmpVisibleAttrs.put(attrName, index);
				tmpVisibleAttributes.add(this.attributes.get(index));
			}
		}
		this.visibleAttributes = new ArrayList<Attribute>(tmpVisibleAttributes);
		this.visibleAttrs = new HashMap<String, Integer>(tmpVisibleAttrs);
		return true;
	}
	
	public boolean isForeignKey(String attrName)
	{
		for(ForeignKey foreignKey : this.foreignKeys)
		{
			if (attrName.equals(foreignKey.localAttrName)) {
				return true;
			}
		}
		return false;
	}
	
	public ForeignKey getForeignKey(String attrName)
	{
		for(ForeignKey foreignKey : this.foreignKeys)
		{
			if (attrName.equals(foreignKey.localAttrName)) {
				return foreignKey;
			}
		}
		return null;
	}
}
