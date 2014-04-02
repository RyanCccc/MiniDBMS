package mysql;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import parser.ParseException;

public class DBManager {
	private static DBManager me;
	public List<Table> tables = new ArrayList<Table>();
	public List<Schema> schemas = new ArrayList<Schema>();
	public List<User> users = new ArrayList<User>();
	public User currentUser;

	public static DBManager getDBManager() {
		if (me == null) {
			me = new DBManager();
		}
		return me;
	}

	public Schema getSchema(String tableName) {
		for (Schema schema : this.schemas) {
			if (schema.tableName.equals(tableName)) {
				return schema;
			}
		}
		return null;
	}

	public Table getTable(String tableName) {
		for (Table table : this.tables) {
			if (table.tableName.equals(tableName)) {
				return table;
			}
		}
		return null;
	}

	public User getUser(String username) {
		for (User user : this.users) {
			if (user.userName.equals(username)) {
				return user;
			}
		}
		return null;
	}

	public void saveUsers() throws ParseException {
		try (OutputStream file = new FileOutputStream("users.ser");
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(this.users);
		} catch (IOException ex) {
			throw new ParseException("Cannot save users");
		}
	}
	
	public void saveTables() throws ParseException {
		try (OutputStream file = new FileOutputStream("tables.ser");
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(this.tables);
		} catch (IOException ex) {
			throw new ParseException("Cannot save tables");
		}
	}
	
	public void saveSchema() throws ParseException {
		try (OutputStream file = new FileOutputStream("schemas.ser");
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(this.schemas);
		} catch (IOException ex) {
			throw new ParseException("Cannot save schemas");
		}
	}

	
	public void loadTables() throws ParseException {
		try (InputStream file = new FileInputStream("tables.ser");
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			List<Table> recoveredTables = (List<Table>) input.readObject();
			this.tables = recoveredTables;
		} catch (ClassNotFoundException ex) {
			throw new ParseException("Cannot perform input. Class not found");
		} catch (IOException ex) {
			System.out.println("No data for tables");
		}
	}
	
	public void loadSchemas() throws ParseException {
		try (InputStream file = new FileInputStream("schemas.ser");
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			List<Schema> recoveredSchemas = (List<Schema>) input.readObject();
			this.schemas = recoveredSchemas;
		} catch (ClassNotFoundException ex) {
			throw new ParseException("Cannot perform input. Class not found");
		} catch (IOException ex) {
			System.out.println("No data for schemas");
		}
	}
	
	
	public void loadUsers() throws ParseException {
		try (InputStream file = new FileInputStream("users.ser");
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			List<User> recoveredUsers = (List<User>) input.readObject();
			this.users = recoveredUsers;
		} catch (ClassNotFoundException ex) {
			throw new ParseException("Cannot perform input. Class not found");
		} catch (IOException ex) {
			System.out.println("No data for users");
		}
	}
}
