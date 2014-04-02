package mysql;

import java.io.Serializable;

public class User implements Serializable{
	public enum Type {
		ADMIN(2), USER_A(1), USER_B(0);
		private int rank;
		private Type(int rank) {
			this.rank = rank;
		}
	}
	public String userName;
	public Type userType;
	public User(String userName, Type userType)
	{
		this.userName = userName;
		this.userType = userType;
	}
}
