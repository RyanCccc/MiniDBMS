package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Eval {
	static ScriptEngineManager mgr = new ScriptEngineManager();
	static ScriptEngine engine = mgr.getEngineByName("JavaScript");
	public static boolean checkExp(String exp) throws ScriptException
	{
		return (boolean)engine.eval(exp);
	}
	
	public static List<String> getNames(String str)
	{
		List<String> names = new ArrayList<String>();
		Pattern p = Pattern.compile("[a-zA-Z]([_0-9a-zA-Z])*");
		Pattern p2 = Pattern.compile("'.*'");
		Matcher m2 = p2.matcher(str);
		while(m2.find())
			str = str.replace(m2.group(), "");
		Matcher m = p.matcher(str);
		while(m.find())
			names.add(m.group());
		return names;
	}
}
