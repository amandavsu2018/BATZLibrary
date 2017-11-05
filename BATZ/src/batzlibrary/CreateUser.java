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
	
	//So.... this method checks to see if the username is in the database on login attempt
	//If true, proceed
	//If false, don't proceed, session closes
	public Boolean checkUsername(String user, boolean bool) {
		bool = true;
		
		String query = "SELECT * FROM users WHERE user_username = '" + user + "'";
		ResultSet result = s.SQLConnMain(query); 
		
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
			String query = "SELECT * FROM users WHERE user_username = '" + user + "'";
			ResultSet result = s.SQLConnMain(query);
			
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
		
		//create the pin here, run the checks below
		pin = String.format("%04d", random.nextInt(9999));
		
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		
		/* DEMONSTRATE HERE */
		// checking if pin exists in ResultSet result
		try {
			// blah = 1 for the pointer to ResultSet data
			int blah = 1;
			// if there is a result in ResultSet result, print the result
			if (result.next()) {
				//This prints the pin
				System.out.println(result.getString(blah));
				
				//temp will equal 0 if result.next() has nothing in the pointer address
				//else it will contain something other than 0
				String temp = result.getString(blah);
				
				//if temp.equals(0) it will return the generated pin, else generate
				//new pin because pin already exists in database
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

	public String assignStatus(String status) {
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
			
			//passed status, if associate then 3 cases, if manager then 4 cases
			if(status.equals("associate")){
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
					System.out.println(
							"Invalid input. Please choose 1 for member, 2 for associate, or 3 to exit.");
				}
			} else if(status.equals("manager")){
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
				/*
				 * case 4: if(temp == true){ return "manager"; } else {
				 * System.out.println("You are not a manger."); }
				 *
				 */
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
		String createzip = "", createphone = "", createcheckedoutnumber = "0";
		
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

			try {
				Connection conn = DriverManager.getConnection("jdbc:mariadb://batzlibrary.org/BATZlibrary",
						"batzlibrary", "XvKk1hl36JLqYLKC");
				System.out.println("Connection Object Created.\n");

				String query = "INSERT INTO users (user_username, user_password, user_pin, user_locked, user_status, user_firstname, user_lastname, user_street, user_city, user_state, user_zip, user_phone, user_checkedoutnumber)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
				preparedStmt.setString(13, createcheckedoutnumber);

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

