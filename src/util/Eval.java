package util;

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
}
