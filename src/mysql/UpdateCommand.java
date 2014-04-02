package mysql;

import java.util.Iterator;
import java.util.List;

import javax.script.ScriptException;

import parser.ParseException;
import util.Eval;

public class UpdateCommand extends Command {
	public String tableName;
	public List<AttributeAssign> attrAssigns;
	public String condition;

	public UpdateCommand(String tableName, List<AttributeAssign> attrAssigns,
			String condition) {
		this.tableName = tableName;
		this.attrAssigns = attrAssigns;
		this.condition = condition;
	}

	@Override
	public void execute() throws ParseException {
		// for(AttributeAssign assign : this.attrAssigns)
		// {
		// System.out.println(assign.attrName);
		// System.out.println(assign.value.getValue());
		// }
		// System.out.println(this.condition);
		DBManager mng = DBManager.getDBManager();
		int countAffectedRow = 0;
		Table table = mng.getTable(this.tableName);
		List<String> attrNamesInCondition = Eval.getNames(this.condition);
		if (table == null) {
			throw new ParseException("No table called " + this.tableName);
		}

		for (Tuple tuple : table.tuples) {
			String exp = this.condition;
			if (!exp.equals("")) {
				for (String attrNameInCondition : attrNamesInCondition) {
					int evalIndex = table.schema
							.getIndexByAttrName(attrNameInCondition.toUpperCase());
					if (evalIndex == -1) {
						throw new ParseException("No attribute named "
								+ attrNameInCondition);
					}
					Value v = tuple.values.get(evalIndex);
					exp = exp.replace(attrNameInCondition, v.toString());
				}
				try {
					if (Eval.checkExp(exp)) {
						for (AttributeAssign attrAssg : this.attrAssigns) {
							int index = table.schema
									.getIndexByAttrName(attrAssg.attrName);
							if (index == -1) {
								throw new ParseException("No attribute named "
										+ attrAssg.attrName);
							}
							if (table.schema.attributes.get(index).type != attrAssg.value.type) {
								throw new ParseException("Type not equal");
							}
							tuple.values.set(index, attrAssg.value);
						}
						countAffectedRow++;
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					throw new ParseException("Expression incorrect");
				}
			}else {
				for (AttributeAssign attrAssg : this.attrAssigns) {
					int index = table.schema
							.getIndexByAttrName(attrAssg.attrName);
					if (index == -1) {
						throw new ParseException("No attribute named "
								+ attrAssg.attrName);
					}
					if (table.schema.attributes.get(index).type != attrAssg.value.type) {
						throw new ParseException("Type not equal");
					}
					tuple.values.set(index, attrAssg.value);
				}
				countAffectedRow++;
			}
		}
		String plural = countAffectedRow > 1 ? "s" : "";
		System.out.println(countAffectedRow + " row" + plural + " affected");
	}
}
