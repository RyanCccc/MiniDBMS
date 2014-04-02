package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import net.sf.jsqlparser.statement.create.table.CreateTable;
import mysql.Attribute;
import mysql.Command;
import mysql.CreateTableCommand;
import mysql.DBManager;
import mysql.DeleteCommand;
import mysql.DropTableCommand;
import mysql.HelpCmdCommand;
import mysql.HelpDescribeCommand;
import mysql.HelpTableCommand;
import mysql.InsertCommand;
import mysql.QuitCommand;
import mysql.Schema;
import mysql.SelectCommand;
import mysql.Table;
import mysql.Tuple;
import mysql.UpdateCommand;
import mysql.User;
import mysql.User.Type;
import parser.MiniParser;
import parser.ParseException;

public class Console {
	private static Scanner scanner;
	private static Class restrictUserA[] = new Class[]
	{
		CreateTableCommand.class,
		DropTableCommand.class,
		SelectCommand.class,
		InsertCommand.class,
		DeleteCommand.class,
		UpdateCommand.class,
		HelpTableCommand.class,
		HelpDescribeCommand.class,
		HelpCmdCommand.class,
		QuitCommand.class,
	};
	private static Class restrictUserB[] = new Class[]
	{
		SelectCommand.class,
		HelpTableCommand.class,
		HelpDescribeCommand.class,
		HelpCmdCommand.class,
		QuitCommand.class,
	};
	public static void main(String[] args){
		scanner = new Scanner(System.in);
		boolean quit = false;
		DBManager mng = DBManager.getDBManager();
		
		try {
			mng.loadUsers();
			mng.loadSchemas();
			mng.loadTables();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		}

		User admin = mng.getUser("admin");
		if (admin==null) {
			admin = new User("admin", Type.ADMIN);
			mng.users.add(admin);
		}
		
		while(!quit){
			System.out.print("\nUser name> ");
			String username = scanner.nextLine();
			User user = mng.getUser(username);
			if (user==null) 
			{
				System.out.println("User name:" + username + " does not exist");
			}else
			{
				mng.currentUser = user;
				quit = true;
			}
		}
		List<Class> restrict = null;
		if (mng.currentUser.userType == Type.USER_B) {
			swapAttrToRestrict();
			restrict = Arrays.asList(restrictUserB);
		}else if (mng.currentUser.userType == Type.USER_A) {
			restrict = Arrays.asList(restrictUserA);
		}
		
		quit = false;
		while (!quit){
			System.out.print("\nSQL> ");
			String commandString = scanner.nextLine();
			InputStream commandStream = new ByteArrayInputStream(commandString.getBytes());
			MiniParser parser = new MiniParser(commandStream);
			try {
				Command c = parser.Command();
				if (restrict!=null) {
					if (!restrict.contains(c.getClass())) {
						throw new ParseException("Authorization failure");
					}
				}
				c.execute();
				
				if (c instanceof QuitCommand) {
					System.exit(0);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	
	public static void swapAttrToRestrict()
	{
		DBManager mng = DBManager.getDBManager();
		for(Schema schema : mng.schemas)
		{
			if (!schema.visibleAttributes.isEmpty()) {
				schema.attributes = schema.visibleAttributes;
				schema.attrs = schema.visibleAttrs;
			}
		}
		for(Table table : mng.tables)
		{
			Schema schema = table.schema;
			if (!schema.visibleAttributes.isEmpty()) {
				schema.attributes = schema.visibleAttributes;
				schema.attrs = schema.visibleAttrs;
			}
		}
	}
}
