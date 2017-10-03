package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager {

	boolean sessionOpen = true;
	
	//for passing to createUser() method
	String userstatus = "manager";

	public Manager() {
	}

	public void actionsMa() {
		int choice = 0;
		System.out.println("What would you like to do?\n");
		System.out.println("1: Add User to Database.");
		System.out.println("2: Create book listing.");
		System.out.println("3: Edit User Account.");
		System.out.println("4: Exit.");
		Scanner choicescan = new Scanner(System.in);
		// get the choice
		while (true) {
			//these need to be here in order to set choice variable
			//to a number 1-4 and nothing else
			try {
				choice = Integer.parseInt(choicescan.nextLine());
				if (choice == 1) {
					break;
				} else if (choice == 2) {
					break;
				} else if (choice == 3) {
					break;
				} else if (choice == 4) {
					break;
				}
			} catch (NumberFormatException nfe) {
				System.out.print("Try again: ");
			}
		}

		// switch cases
		switch (choice) {
		case 1:
			CreateUser cu = new CreateUser();
			cu.createUser(userstatus);
			break;
		case 2:
			createBook();
		case 3:
			editUser();
		case 4:
			break;
		}	
		choicescan.close();
		sessionOpen = false;
	}
	
	public void editUser(){
		int choice = 0;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		String query = "";
		
		Scanner pinscan = new Scanner(System.in);
		System.out.println("Please enter user's pin number.");
		String pin = pinscan.next();
		
		//We need to add a checkPin() method later to check for existing pin
		
		System.out.println("What would you like to edit?\n");
		System.out.println("1: Generate New Password.");
		System.out.println("2: Set Locked to true or false.");
		System.out.println("3: Edit Status.");
		System.out.println("4: Edit First Name.");
		System.out.println("5: Edit Last Name.");
		System.out.println("6: Edit Street Address");
		System.out.println("7: Edit City.");
		System.out.println("8: Edit State.");
		System.out.println("9: Edit Zip Code.");
		System.out.println("10: Edit Phone.");
		System.out.println("11: Exit.");
		Scanner choicescan = new Scanner(System.in);
		while (true) {
			//these need to be here in order to set choice variable
			//to a number 1-4 and nothing else
			try {
				choice = Integer.parseInt(choicescan.nextLine());
				if (choice == 1) {
					break;
				} else if (choice == 2) {
					break;
				} else if (choice == 3) {
					break;
				} else if (choice == 4) {
					break;
				} else if (choice == 5) {
					break;
				} else if (choice == 6) {
					break;
				} else if (choice == 7) {
					break;
				} else if (choice == 8) {
					break;
				} else if (choice == 9) {
					break;
				} else if (choice == 10) {
					break;
				} else if (choice == 11) {
					break;
				}
			} catch (NumberFormatException nfe) {
				System.out.print("Try again: ");
			}
		}

		// switch cases
		switch (choice) {
		case 1:
			//generate password
			String password = "";
			CreateUser thing = new CreateUser();
			password = thing.createPassword();
			query = "UPDATE users SET user_password = '" + password + "'WHERE user_pin = '" + pin + "'";
			System.out.println(password);
			break;
		case 2:
			String locked = "";
			Scanner l = new Scanner(System.in);
			System.out.println("True or false?");
			locked = l.nextLine();
			query = "UPDATE users SET user_locked = '" + locked + "'WHERE user_pin = '" + pin + "'";
			break;
		case 3:
			String status = "";
			Scanner s = new Scanner(System.in);
			System.out.println("Enter status: manager, associate, or member");
			status = s.nextLine();
			query = "UPDATE users SET user_status = '" + status + "'WHERE user_pin = '" + pin + "'";
			break;
		case 4:
			String fName = "";
			Scanner f = new Scanner(System.in);
			System.out.println("First Name:");
			fName = f.nextLine();
			query = "UPDATE users SET user_firstname = '" + fName + "'WHERE user_pin = '" + pin + "'";
			break;
		case 5:
			String lname = "";
			Scanner ln = new Scanner(System.in);
			System.out.println("Last Name:");
			lname = ln.nextLine();
			query = "UPDATE users SET user_lastname = '" + lname + "'WHERE user_pin = '" + pin + "'";
			break;
		case 6:
			String street = "";
			Scanner a = new Scanner(System.in);
			System.out.println("Street:");
			street = a.nextLine();
			query = "UPDATE users SET user_street = '" + street + "'WHERE user_pin = '" + pin + "'";
			break;
		case 7:
			String city = "";
			Scanner c = new Scanner(System.in);
			System.out.println("City:");
			city = c.nextLine();
			query = "UPDATE users SET user_city = '" + city + "'WHERE user_pin = '" + pin + "'";
			break;
		case 8:
			String state = "";
			Scanner st = new Scanner(System.in);
			System.out.println("City:");
			state = st.nextLine();
			query = "UPDATE users SET user_state = '" + state + "'WHERE user_pin = '" + pin + "'";
			break;
		case 9:
			String zip = "";
			Scanner zi = new Scanner(System.in);
			System.out.println("Zip:");
			zip = zi.nextLine();
			query = "UPDATE users SET user_zip = '" + zip + "'WHERE user_pin = '" + pin + "'";
			break;
		case 10:
			String phone = "";
			Scanner ph = new Scanner(System.in);
			System.out.println("Phone:");
			phone = ph.nextLine();
			query = "UPDATE users SET user_phone = '" + phone + "'WHERE user_pin = '" + pin + "'";
			break;
		case 11:
			break;
		}
		try{
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("What would you like to do? 1. Edit more fields 2. Exit");
		Scanner scan = new Scanner(System.in);
		int thescan = scan.nextInt();
		if(thescan == 1){
			editUser();
		} else {
			sessionOpen = false;
		}
	}

	public void createBook(){
		//variables
		String bookTitle = null, bookAuthors = "", bookISBN = "", bookPubYear = "", bookKeywords = "", bookInvNum = "";
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter book title.");
		bookTitle = scan.nextLine().trim();
		if (bookTitle != null) {
			System.out.println("Enter book authors seperated by commas.");
			bookAuthors = scan.nextLine().trim();
			System.out.println("Enter book ISBN number.");
			bookISBN = scan.nextLine().trim();
			System.out.println("Enter book publication year.");
			bookPubYear = scan.nextLine().trim();
			System.out.println("Enter book keywords seperated by commas.");
			bookKeywords = scan.nextLine().trim();
			System.out.println("Enter number of books in inventory.");
			bookInvNum = scan.nextLine().trim();
		
			try {
				Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
				System.out.println("Connection Object Created.\n");

				String query = "INSERT INTO books (book_title, book_authors, book_ISBN, book_pubyear, book_keywords, book_invnum)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, bookTitle);
				preparedStmt.setString(2, bookAuthors);
				preparedStmt.setString(3, bookISBN);
				preparedStmt.setString(4, bookPubYear);
				preparedStmt.setString(5, bookKeywords);
				preparedStmt.setString(6, bookInvNum);

				System.out.println("Attempting database connection..\n");
				preparedStmt.execute();
				conn.close();
				System.out.println("Updated database successfully.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
