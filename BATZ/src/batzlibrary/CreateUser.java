package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class CreateUser {
	public Random random = new Random();
	public SQL s = new SQL();
	UsersTable ut = new UsersTable();

	public CreateUser() {
		ut.setTableVariables();
	}

	public String createPassword() {
		String pass = "";
		int passlength = 7;
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456790";
		for (int i = 0; i < passlength; i++) {
			pass += alphabet.charAt(random.nextInt(alphabet.length()));
		}
		return pass;
	}

	public String assignStatus(String status) {

		while (true) {
			int choice = 0;

			if (status.equals("associate")) {
				System.out.println(
						"What status would you like to assign to this user? Enter 1 for member, 2 for associate, or 3 to exit.");
				Scanner newscan = new Scanner(System.in);
				choice = newscan.nextInt();
				switch (choice) {
				case 1:
					return "member";
				case 2:
					return "associate";
				case 3:
					return null;
				default:
					System.out.println("Invalid input. Please choose 1 for member, 2 for associate, or 3 to exit.");
				}
			} else if (status.equals("manager")) {
				System.out.println(
						"What status would you like to assign to this user? Enter 1 for member, 2 for associate, 3 for manager, or 4 to exit.");
				Scanner newscan = new Scanner(System.in);
				choice = newscan.nextInt();
				switch (choice) {
				case 1:
					return "member";
				case 2:
					return "associate";
				case 3:
					return "manager";
				case 4:
					return null;
				default:
					System.out.println(
							"Invalid input. Please choose 1 for member, 2 for associate, 3 for manager, or 4 to exit.");
				}
			}
		}
	}

	public void createUser(String status) {
		String createuser = null;
		String createpass = "", createpin = "", createlocked = "", createstatus = null;
		String createfname = "", createlname = "", createstreet = "", createcity = "", createstate = "";
		String createzip = "", createphone = "", createcheckedoutnumber = "0", createfines = "0";

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter username.");
		createuser = scan.nextLine();
		// Check to see if user exists
		boolean cubool = ut.checkUsername(createuser);
		if (cubool == true) {
			System.out.println("User already exists.");
			createuser = ut.replaceUsername(createuser);
			System.out.println("new username: " + createuser);
		} else {
			System.out.println("username good.");
		}
		createpass = createPassword();
		createpin = ut.generatePin();
		createlocked = "false";
		createstatus = assignStatus(status);
		if (createstatus != null) {
			System.out.println("Enter First Name:");
			createfname = (scan.nextLine()).trim();
			System.out.println("Enter Last Name:");
			createlname = scan.nextLine().trim();
			System.out.println("Enter Address:");
			createstreet = scan.nextLine().trim();
			System.out.println("Enter City:");
			createcity = scan.nextLine().trim();
			System.out.println("Enter State:");
			createstate = scan.nextLine().trim();
			System.out.println("Enter Zip:");
			createzip = scan.nextLine().trim();
			System.out.println("Enter Phone:");
			createphone = scan.nextLine().trim();

			String query = "INSERT INTO users (user_username, user_password, user_pin, user_locked, user_status, user_firstname, user_lastname, user_street, user_city, user_state, user_zip, user_phone, user_checkedoutnumber, user_fines)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			String[] arr = { createuser, createpass, createpin, createlocked, createstatus, createfname, createlname,
					createstreet, createcity, createstate, createzip, createphone, createcheckedoutnumber, createfines };
			s.SQLConnForMoreThanOnePreparedStatement(query, arr);

			System.out.println("Updated database successfully.");
			System.out.println("Your username is: " + createuser);
			System.out.println("Your password is: " + createpass);
			System.out.println("Your pin is: " + createpin);
			System.out.println("Welcome to BATZ Library " + createfname + "!");
		}
	}
}
