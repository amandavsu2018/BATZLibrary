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

	public Boolean checkUsername(String user, boolean bool) {
		bool = true;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try {
			// Creates conn Object with database from first line of file,
			// databaseUser from 2nd,
			// and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);

			// Here we set the MariaDB query variable which is the command we
			// are going to use
			// when we query the database in order to obtain information
			String query = "SELECT * FROM users WHERE user_username = '" + user + "'";

			// This is Java PreparedStatement, it creates the object
			// preparedStatement which has the
			// conn variable data and the database query variable. It will be
			// executed in order
			// to connect to the MariaDB database to get our information out
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			// This ResultSet is an object that contains the executed query from
			// preparedStatement.
			// if result has stuff in it from the query (i.e. the username we
			// are checking to see
			// is already in the database) then result.first() will have stuff
			// in it and we can
			// set boolean to true. Else it's empty and we set boolean to false
			result = preparedStmt.executeQuery(query);

			// close database connection after query
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// check to see if ResultSet result has stuff in it. Return true if so,
		// and false if not.
		try {
			if (result.first()) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public String replaceUsername(String user) {
		boolean replace = false;
		String tempuser = user;
		int num = 1;
		String[] stringarray = new String[2];
		String temp = null;
		while (replace == false) {
			Database db = new Database();
			String database = new String(db.getDatabase());
			String databaseUser = new String(db.getDatabaseUser());
			String databasePass = new String(db.getDatabasePassword());
			ResultSet result = null;
			try {
				// Creates conn Object with database from first line of file,
				// databaseUser from 2nd,
				// and databasePass from 3rd
				Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);

				// Here we set the MariaDB query variable which is the command
				// we are going to use
				// when we query the database in order to obtain information
				String query = "SELECT * FROM users WHERE user_username = '" + user + "'";

				// This is Java PreparedStatement, it creates the object
				// preparedStatement which has the
				// conn variable data and the database query variable. It will
				// be executed in order
				// to connect to the MariaDB database to get our information out
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				// This ResultSet is an object that contains the executed query
				// from preparedStatement.
				// if result has stuff in it from the query (i.e. the username
				// we are checking to see
				// is already in the database) then result.first() will have
				// stuff in it and we can
				// set boolean to true. Else it's empty and we set boolean to
				// false
				result = preparedStmt.executeQuery(query);

				// close database connection after query
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// check to see if ResultSet result has stuff in it. If it does,
			// then the user name still exists in the database. So, increment
			// num,
			// concatenate to tempuser, and try again. If result.first() returns
			// null,
			// then ResultSet result contains nothing and user name does not
			// exist in
			// database. So, set replace to true and return current user name in
			// user.
			try {
				if (result.first()) {
					stringarray[0] = tempuser;
					stringarray[1] = Integer.toString(num);
					temp = stringarray[0] + stringarray[1];
					num++;
					user = temp;
				} else {
					replace = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public String createPassword() {
		String pass = "";
		int passlength = 7; // change to determine length of password
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456790";
		for (int i = 0; i < passlength; i++) {
			pass += alphabet.charAt(random.nextInt(alphabet.length()));
		}
		return pass;
	}

	public String generatePin() {
		String pin = "";
		pin = String.format("%04d", random.nextInt(9999));
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		ResultSet result = null;
		try {
			// Creates conn Object with database from first line of file,
			// databaseUser from 2nd,
			// and databasePass from 3rd
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);

			// Here we set the MariaDB query variable which is the command
			// we are going to use
			// when we query the database in order to obtain information
			String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";

			// This is Java PreparedStatement, it creates the object
			// preparedStatement which has the
			// conn variable data and the database query variable. It will
			// be executed in order
			// to connect to the MariaDB database to get our information out
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			// This ResultSet is an object that contains the executed query
			// from preparedStatement.
			// if result has stuff in it from the query (i.e. the username
			// we are checking to see
			// is already in the database) then result.first() will have
			// stuff in it and we can
			// set boolean to true. Else it's empty and we set boolean to
			// false
			result = preparedStmt.executeQuery(query);

			// close database connection after query
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// checking if pin exists in ResultSet result
		try {
			int blah = 1;
			if (result.next()) {
				System.out.println(result.getString(blah));
				String temp = result.getString(blah);
				if (temp.equals(0)) {
					return pin;
				} else {
					return generatePin();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pin;
	}

	public String assignStatus() {
		/*
		 * We need to work on passing a false or true in order to determine if
		 * case 4 can go through in other words, if associate, case 4 won't go
		 * through
		 * 
		 * System.out.println("True or false?"); Scanner scantemp = new
		 * Scanner(System.in); boolean temp = true; temp =
		 * scantemp.nextBoolean();
		 */
		while (true) {
			int choice = 0;
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
			/*
			 * case 4: if(temp == true){ return "manager"; } else {
			 * System.out.println("You are not a manger."); }
			 *
			 */
			default:
				System.out.println(
						"Invalid input. Please choose 1 for member, 2 for associate, 3 to exit, or 4 for manager.");
			}
		}
	}

	public void createUser() {
		String createuser = null;
		String createpass = "", createpin = "", createlocked = "", createstatus = null;
		String createfname = "", createlname = "", createstreet = "", createcity = "", createstate = "";
		String createzip = "", createphone = "", createemail = "";
		boolean checkBool = false;

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter username.");
		createuser = scan.nextLine();
		// Check to see if user exists
		boolean cubool = checkUsername(createuser, checkBool);
		if (cubool == true) {
			System.out.println("User already exists.");
			createuser = replaceUsername(createuser);
			System.out.println("new username: " + createuser);
		} else {
			System.out.println("username good.");
		}
		System.out.println("Checking password: ");
		createpass = createPassword();
		System.out.println("Checking pin: ");
		createpin = generatePin();
		System.out.println("Setting to unlocked");
		createlocked = "false";
		System.out.println("Assigning Status");
		createstatus = assignStatus();
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

			try {
				Connection conn = DriverManager.getConnection("jdbc:mariadb://batzlibrary.org/BATZlibrary",
						"batzlibrary", "XvKk1hl36JLqYLKC");
				System.out.println("Connection Object Created.\n");

				String query = "INSERT INTO users (user_username, user_password, user_pin, user_locked, user_status, user_firstname, user_lastname, user_street, user_city, user_state, user_zip, user_phone)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, createuser);
				preparedStmt.setString(2, createpass);
				preparedStmt.setString(3, createpin);
				preparedStmt.setString(4, createlocked);
				preparedStmt.setString(5, createstatus);
				preparedStmt.setString(6, createfname);
				preparedStmt.setString(7, createlname);
				preparedStmt.setString(8, createstreet);
				preparedStmt.setString(9, createcity);
				preparedStmt.setString(10, createstate);
				preparedStmt.setString(11, createzip);
				preparedStmt.setString(12, createphone);

				System.out.println("Attempting database connection..\n");
				preparedStmt.execute();
				conn.close();
				System.out.println("Updated database successfully.");
				System.out.println("Your username is: " + createuser);
				System.out.println("Your password is: " + createpass);
				System.out.println("Your pin is: " + createpin);
				System.out.println("Welcome to BATZ Library " + createfname + "!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			//take back to home
		}
	}
}

