package batzlibrary;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Main {

	static boolean sessionOpen = true;
	// JDBC driver name and database URL
	static String jdbc_driver = "";
	static String database = "";
	static String USER = "";
	static String PASS = "";

	public static void main(String[] args) {
		// Set the JDBC Driver for connecting to MariaDB Database
		JDBCdriver jd = new JDBCdriver();
		jdbc_driver = jd.jdbcDriver();

		// Instantiate Database Object and update database address, user, pass
		Database db = new Database();
		database = db.dataBase();

		while (sessionOpen == true) {
			greet();
		}
	}

	public static void greet() {
		String answ = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello! Welcome to BATZ Library. Would you like to do?");
		System.out.println("1. Log in, 2. Search, 3. Exit");
		answ = sc.nextLine();

		if (answ.equals("1")) {
			Login l = new Login();
			l.logIn();
		} else if (answ.equals("2")) {
			SearchBooks sb = new SearchBooks();
			sb.choice();
		} else if (answ.equals("3")) {
			sessionOpen = false;
			System.out.print("Goodbye!");
		} else {
			System.out.println("Invalid input.");
			greet();
		}
	}
}