package mysql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tuple implements Serializable {
	public List<Value> values;
	public Tuple(List<Value> values)
	{
		this.values = values;
	}
	
	public Tuple mergeTuple(Tuple tuple, Schema schema)
	{
		List<Value> newValues = new ArrayList<Value>(this.values);
		for(Attribute attr : schema.attributes)
		{
			newValues.add(tuple.values.get(schema.attrs.get(attr.name)));
		}
		return new Tuple(newValues);
	}
	
	@Override
	public String toString()
	{
		String str = "";
		for (Value value : this.values) {
			str += value + "\t";
		}
		return str;
	}
	
	public String toStringByIndex(List<Integer> indexList)
	{
		String str = "";
		for (Integer index : indexList) {
			str += this.values.get(index) + "\t";
		}
		return str;
	}
}
