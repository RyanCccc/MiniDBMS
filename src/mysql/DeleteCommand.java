package mysql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import parser.ParseException;
import util.Eval;

public class DeleteCommand extends Command {
	public String tableName;
	public String condition;

	public DeleteCommand(String tableName, String condition) {
		this.tableName = tableName;
		this.condition = condition;
	}

	@Override
	public void execute() throws ParseException {
		DBManager mng = DBManager.getDBManager();
		List<String> attrNamesInCondition = Eval.getNames(this.condition);
		Table table = mng.getTable(this.tableName);
		if (table == null) {
			throw new ParseException("No table named " + this.tableName);
		}
		List<Integer> removeIndexList = new ArrayList<>();
		for (int i=0; i<table.tuples.size(); i++) {
			String exp = this.condition;
			if (exp.equals("")) {
				removeIndexList.add(i);
			} else {
				for (String attrNameInCondition : attrNamesInCondition) {
					int index = table.schema.getIndexByAttrName(attrNameInCondition.toUpperCase());
					if (index == -1) {
						throw new ParseException("No attribute named " + attrNameInCondition);
					}
					Value v = table.tuples.get(i).values.get(index);
					exp = exp.replace(attrNameInCondition, v.toString());
				}
				try {
					if (Eval.checkExp(exp)) {
						// Delete
						removeIndexList.add(i);
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					throw new ParseException("Type not equal");
				}
			}
		}
		
		//
		for(int i=0; i<removeIndexList.size(); i++){
			int removeIndex = removeIndexList.get(i);
			table.tuples.remove(removeIndex-i);
		}
		int countRemovedRow = removeIndexList.size();
		String plural = countRemovedRow>1?"s":"";
		System.out.println(countRemovedRow + " row" + plural + " affected");
	}
}