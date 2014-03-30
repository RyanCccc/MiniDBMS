package main;

import java.io.*;
import java.util.Scanner;

import mysql.Command;
import mysql.DBManager;
import parser.MiniParser;
import parser.ParseException;

public class Console {
	private static Scanner scanner;

	public static void main(String[] args){
		scanner = new Scanner(System.in);
		boolean quit = false;
		while (!quit){
			System.out.print("\nSQL> ");
			String commandString = scanner.nextLine();
			InputStream commandStream = new ByteArrayInputStream(commandString.getBytes());
			MiniParser parser = new MiniParser(commandStream);
			try {
				Command c = parser.Command();
				c.execute();
				DBManager db = DBManager.getDBManager();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
