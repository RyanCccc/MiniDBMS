package mysql;

import java.io.Serializable;

public class Value implements Serializable{
	public Attribute.Type type;
	public String val;
	public Value(Attribute.Type type, String val){
		this.type = type;
		this.val = val;
	}
	
	public Object getValue()
	{
		if(this.type == Attribute.Type.CHAR)
			return this.val;
		else if (this.type == Attribute.Type.INT) {
			return Integer.valueOf(this.val);
		}else if (this.type == Attribute.Type.DECIMAL) {
			return Double.valueOf(this.val);
		}
		return null;
	}
	
	@Override
	public String toString(){
		return getValue().toString();
	}
}
