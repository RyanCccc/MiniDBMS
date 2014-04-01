package main;

import java.io.*;
import java.util.Scanner;

import mysql.Command;
import mysql.DBManager;
import mysql.Table;
import mysql.Tuple;
import mysql.User;
import mysql.User.Type;
import parser.MiniParser;
import parser.ParseException;

public class Console {
	private static Scanner scanner;

	public static void main(String[] args){
		scanner = new Scanner(System.in);
		boolean quit = false;
		User user = new User("admin", Type.ADMIN);
		DBManager.getDBManager().users.add(user);
		DBManager.getDBManager().currentUser = user;
		while (!quit){
			System.out.print("\nSQL> ");
			String commandString = scanner.nextLine();
			InputStream commandStream = new ByteArrayInputStream(commandString.getBytes());
			MiniParser parser = new MiniParser(commandStream);
			try {
				Command c = parser.Command();
				c.execute();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
