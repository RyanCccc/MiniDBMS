package mysql;

import java.util.*;

import javax.script.ScriptException;

import parser.ParseException;
import util.Eval;

public class SelectCommand extends Command {

	List<String> attrNames;
	List<String> tableNames;
	String condition;

	public SelectCommand(List<String> attrNames, List<String> tables,
			String condition) {
		this.attrNames = attrNames;
		this.tableNames = tables;
		this.condition = condition;
	}

	@Override
	public void execute() throws ParseException {
		DBManager mng = DBManager.getDBManager();
		List<String> allAttrNamesList = new ArrayList<String>();
		List<Table> tables = new ArrayList<Table>();
		for (String tableName : this.tableNames) {
			tables.add(mng.getTable(tableName));
		}
		List<Tuple> tuples = new ArrayList<Tuple>();
		tuples.add(new Tuple(new ArrayList<Value>()));
		for (Table table : tables) {
			for (Attribute attr : table.schema.attributes) {
				allAttrNamesList.add(attr.name);
			}
			List<Tuple> tmpTuples = new ArrayList<Tuple>();
			for (Tuple targetTuple : tuples) {
				for (Tuple tuple : table.tuples) {
					Tuple mergedTuple = targetTuple.mergeTuple(tuple);
					tmpTuples.add(mergedTuple);
				}
			}
			tuples = tmpTuples;
		}
		// for(Tuple tuple : tuples)
		// {
		// System.out.println(tuple);
		// }
		List<Integer> attrPos = new ArrayList<Integer>();

		if (this.attrNames == null) {
			for (int i = 0; i < allAttrNamesList.size(); i++) {
				attrPos.add(i);
			}
		} else {
			for (String attrName : this.attrNames) {
				int index = allAttrNamesList.indexOf(attrName);
				if (index == -1) {
					throw new ParseException("No attribute named " + attrName);
				}
				attrPos.add(index);
			}
		}

		List<String> attrNamesInCondition = Eval.getNames(this.condition);
		for (String attrName : this.attrNames) {
			System.out.print(attrName + "\t");
		}
		System.out.println();
		for (Tuple tuple : tuples) {
			String exp = this.condition;
			if (exp.equals("")) {
				System.out.println(tuple.toStringByIndex(attrPos));
			} else {
				for (String attrNameInCondition : attrNamesInCondition) {
					int index = allAttrNamesList.indexOf(attrNameInCondition
							.toUpperCase());
					if (index == -1) {
						throw new ParseException("No attribute named " + attrNameInCondition);
					}
					Value v = tuple.values.get(index);
					exp = exp.replace(attrNameInCondition, v.toString());
				}
				try {
					if (Eval.checkExp(exp)) {
						System.out.println(tuple.toStringByIndex(attrPos));
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					throw new ParseException("Type not equal");
				}
			}
		}
	}
}
