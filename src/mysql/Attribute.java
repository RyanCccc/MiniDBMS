package mysql;

public class Attribute {
	public enum Type{
		INT, CHAR, DECIMAL;
	}
	public String name;
	public Type type;
	public int length;
	public Attribute(Type type, String name, int length) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.name = name;
		this.length = length;
	}

}
