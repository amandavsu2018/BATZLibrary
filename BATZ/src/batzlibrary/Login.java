package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

public class Login {
	Database db = new Database();

	String username, password, status = "", fname = "";
	boolean sessionOpen = false;
	boolean checkBool = false;

	UsersTable ut = new UsersTable();

	public void logIn() {
		System.out.println("Login.\n");
		Scanner scan = new Scanner(System.in);
		System.out.print("Username: ");
		username = scan.nextLine();

		boolean userlogin = ut.checkUsername(username, checkBool);

		if (userlogin == true) {
			System.out.println("User exists\n");
			System.out.println("Password: ");
			password = scan.nextLine();
			checkBool = false;
			userlogin = ut.checkUserPassword(username, password, checkBool);
			if (userlogin == true) {
				System.out.println("Username and password match, LOGIN!");
				sessionOpen = true;
			} else {
				System.out.println("Username and password DO NOT match, Goodbye!");
			}
		} else {
			System.out.println("User does not exist!");
		}

		fname = ut.getFirstname(username, fname);
		status = ut.getStatus(username, status);

		System.out.println(fname + "'s status is a " + status + ".");

		while (sessionOpen == true) {
			System.out.println("Welcome " + fname + ". What would you like to do?");

			if (status.equals("member")) {
				Member me = new Member();
				me.actionsMe();
			} else if (status.equals("associate")) {
				Associate a = new Associate();
				a.actionsA();
			} else if (status.equals("manager")) {
				Manager ma = new Manager();
				ma.actionsMa();
			} else {
				System.out.println("Your membership status is still pending. Please contact an manager.");
			}

			sessionOpen = false;
		}
		System.out.println("Session closing. Goodbye!");
	}

}
