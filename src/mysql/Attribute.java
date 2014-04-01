package mysql;

public class Attribute {
	public enum Type{
		INT, CHAR, DECIMAL;
	}
	public String name;
	public Type type;
	public int length;
	public String constraint;
	public Attribute(Type type, String name, int length, String constraint) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.name = name;
		this.length = length;
		this.constraint = constraint;
	}
	@Override
	public String toString()
	{
		String str = this.name + " -- ";
		switch (this.type) {
		case INT:
			str += "int";
			break;
		case CHAR:
			str += "char(" + this.length + ")";
			break;
		case DECIMAL:
			str += "decimal";
		}
		return str;
	}

}
