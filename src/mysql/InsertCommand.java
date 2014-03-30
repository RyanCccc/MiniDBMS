package mysql;

import java.util.List;

import javax.script.ScriptException;

import parser.ParseException;
import util.Eval;

public class InsertCommand extends Command {
	public String tableName;
	public List<Value> values;
	public InsertCommand(String tableName, List<Value> values)
	{
		this.tableName = tableName;
		this.values = values;
	}
	
	@Override
	public void execute() throws ParseException
	{
		DBManager mng = DBManager.getDBManager();
		Table table = mng.getTable(this.tableName);
		Schema schema = mng.getSchema(this.tableName);
		if (table==null) {
			throw new ParseException("No table named "+this.tableName);
		}
		if (values.size()!=schema.attributes.size()) {
			throw new ParseException("Attributes number not equal");
		}
		for(int i=0 ; i<values.size(); i++)
		{
			Attribute attribute = schema.attributes.get(i);
			Value value = values.get(i);
			if (value.type != attribute.type) {
				throw new ParseException("Attributes type not equal");
			}
			if (values.get(i).type == Attribute.Type.CHAR) {
				String val = (String)value.getValue();
				if (val.length()>attribute.length) {
					throw new ParseException("Attribute value " + val + " exceed max length " + attribute.length);
				}
			}
			if (!attribute.constraint.equals("")) {
				String exp = attribute.constraint.replace(attribute.name, value.getValue().toString());
				try {
					if(!Eval.checkExp(exp)){
						throw new ParseException("Value " + value.getValue().toString() + " doesn't satisfy constraint " + attribute.constraint);
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		table.tuples.add(new Tuple(values));
	}
}
