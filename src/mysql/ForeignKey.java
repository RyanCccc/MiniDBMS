package mysql;

import java.io.Serializable;

public class ForeignKey implements Serializable{
	public String refTableName;
	public String localAttrName;
	public String refAttrName;
	public ForeignKey(String refTableName, String localAttrName, String refAttrName) {
		this.refTableName = refTableName;
		this.localAttrName = localAttrName;
		this.refAttrName = refAttrName;
	}
}
