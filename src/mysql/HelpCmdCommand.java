package mysql;

import parser.ParseException;

public class HelpCmdCommand extends Command {
	public enum Type {
		CREATE_TABLE, DROP_TABLE, SELECT, INSERT, DELETE, UPDATE,
	}

	public Type type;
	public Type[] restrictTypes;

	public HelpCmdCommand(Type type) {
		this.type = type;
		this.restrictTypes = new Type[] { Type.SELECT };
	}

	@Override
	public void execute() throws ParseException {
		if (DBManager.getDBManager().currentUser.userType == User.Type.USER_B) {
			boolean contains = false;
			for (Type t : this.restrictTypes) {
				if (t == this.type) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				throw new ParseException("No specific command");
			}
		}

		switch (this.type) {
		case CREATE_TABLE:
			System.out.println("This command has the form: \nCREATE TABLE table_name ( attribute_1 attribute1_type CHECK (constraint1),\n"+
					"attribute_2 attribute2_type, ... , PRIMARY KEY ( attribute_1, attribute_2 ), FOREIGN \n" +
					"KEY ( attribute_y ) REFERENCES table_x ( attribute_t ), FOREIGN KEY \n" +
					"( attribute_w ) REFERENCES table_y ( attribute_z )... );");
			break;
		case DROP_TABLE:
			System.out.println(
					"This command has the form: \n" +
					"DROP TABLE table_name;\n" + 
					"The \"DROP TABLE\" token is followed by a table name"
			);
			break;
		case SELECT:
			System.out.println(
					"This command has the form: \n" +
					" DELETE FROM table_name WHERE condition_list; \n" +
					" The \"DELETE FROM\" token is followed by a table name, followed by the optional \n" +
					"\"WHERE\" keyword and a condition list. The condition list has the following format: \n" +
					" attribute1 operator value1 \n" +
					" OR \n" +
					" attribute1 operator value1 AND/OR attribute2 operator value2 AND/OR attribute3 \n" +
					"operator value3..."
			);
			break;
		case INSERT:
			System.out.println(
					"This command has the form: \n" +
					"INSERT INTO table_name VALUES ( val1, val2, ... );" 
			);
			break;
		case DELETE:
			System.out.println(
					"This command has the form: \n" +
					" \n" +
					"DELETE FROM table_name WHERE condition_list; \n" +
					" \n" +
					"The \"DELETE FROM\" token is followed by a table name, followed by the optional \n" +
					"\"WHERE\" keyword and a condition list. The condition list has the following format: \n" +
					" \n" +
					"attribute1 operator value1 \n" +
					" \n" +
					"OR \n" +
					" \n" +
					"attribute1 operator value1 AND/OR attribute2 operator value2 AND/OR attribute3 \n" +
					"operator value3..."
			);
			break;
		case UPDATE:
			System.out.println(
					"This command has the form: \n" +
					" \n" +
					"UPDATE table_name SET attr1 = val1, attr2 = val2... WHERE condition_list;"
			);
			break;

		default:
			throw new ParseException("No specific command");
		}
	}
}
