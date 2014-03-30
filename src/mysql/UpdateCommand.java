package mysql;

import java.util.List;

public class UpdateCommand extends Command {
	public String tableName;
	public List<AttributeAssign> attrAssigns;
	public String condition;
	public UpdateCommand(String tableName, List<AttributeAssign> attrAssigns, String condition)
	{
		this.tableName = tableName;
		this.attrAssigns = attrAssigns;
		this.condition = condition;
	}
	
	@Override
	public void execute()
	{
		for(AttributeAssign assign : this.attrAssigns)
		{
			System.out.println(assign.attrName);
			System.out.println(assign.value.getValue());
		}
		System.out.println(this.condition);
	}
}
