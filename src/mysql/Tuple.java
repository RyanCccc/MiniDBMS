package mysql;

import java.util.ArrayList;
import java.util.List;

public class Tuple {
	public List<Value> values;
	public Tuple(List<Value> values)
	{
		this.values = values;
	}
	
	public Tuple mergeTuple(Tuple tuple)
	{
		List<Value> newValues = new ArrayList<Value>(this.values);
		newValues.addAll(tuple.values);
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
