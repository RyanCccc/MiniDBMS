package mysql;

import java.util.List;

public class ForeignKey {
	public String refTableName;
	public String localAttrName;
	public String refAttrName;
	public ForeignKey(String refTableName, String localAttrName, String refAttrName) {
		this.refTableName = refTableName;
		this.localAttrName = localAttrName;
		this.refAttrName = refAttrName;
	}
}
