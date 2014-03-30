package mysql;

import java.util.*;

public class SelectCommand extends Command {
	List<String> attrNames;
	List<String> tables;
	String condition;
	public SelectCommand(List<String> attrNames, List<String> tables, String condition)
	{
		this.attrNames = attrNames;
		this.tables = tables;
		this.condition = condition;
	}
	
	@Override
	public void execute()
	{
		System.out.println(this.condition);
	}
}
