package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager {

	// for passing to createUser() method
	String userstatus = "manager";

	public Manager() {
	}

	public void actionsMa() {
		boolean sessionOpen = true;
		while (sessionOpen == true) {
			int choice = 0;
			System.out.println("What would you like to do?\n");
			System.out.println("1: Add User to Database.");
			System.out.println("2: Create book listing.");
			System.out.println("3: Edit User Account.");
			System.out.println("4: Reactivate a User Account");
			System.out.println("5: Exit.");
			// get the choice
			while (true) {
				Scanner choicescan = new Scanner(System.in);
				// these need to be here in order to set choice variable
				// to a number 1-4 and nothing else
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
						sessionOpen = false;
						break;
					} else {
						sessionOpen = false;
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.print("Try again: ");
				}
//				choicescan.close();
			}

			// switch cases
			switch (choice) {
			case 1:
				CreateUser cu = new CreateUser();
				cu.createUser(userstatus);
				break;
			case 2:
				createBook();
				break;
			/*
			 * System.out.
			 * println("What would you like to do next? 1. Main Menu, 2. Create another book"
			 * ); String x = choicescan.nextLine(); if (x.equals("1")) { actionsMa(); } else
			 * if (x.equals("2")) { createBook(); } else { break; }
			 */ case 3:
				editUser();
				break;
			case 4:
				reactivateUser();
				break;
			// case 5:
			// sessionOpen = false;
			// break;
			default:
				sessionOpen = false;
				break;
			}
			// sessionOpen = false;
			// System.out.println("Exiting.. Goodbye!");
			// System.exit(1);
		}
	}

	public void reactivateUser() {
		System.out.println("Please enter the pin number of the user that you would like to reactivate: ");
		Scanner scan = new Scanner(System.in);
		String promptChoice;
		while (true) {
			boolean checkIfPinExists = false;
			String pinnumber = scan.nextLine();
			String query = "SELECT * FROM users WHERE user_pin = '" + pinnumber + "'";
			ReactivateUser reactivate = new ReactivateUser();
			reactivate.setPin(pinnumber);
			checkIfPinExists = reactivate.checkIfPinExistsInDB(query);
			if (checkIfPinExists == true) {
				reactivate.updateLockedStatusInDB();
				System.out.println("User unlocked successfully.");
				break;
			} else {
				System.out.println("The pin number entered does not exists in the database.");
				reactivateUser();
			}
		}
		/*
		 * System.out.
		 * println("What would you like to do? 1. Go back to main prompt, 2. Exit");
		 * promptChoice = scan.nextLine(); if (promptChoice.equals("1")) { actionsMa();
		 * } else { scan.close(); System.out.println("Exiting.. Goodbye!");
		 * System.exit(1); }
		 */}

	public void editUser() {
		int choice = 0;
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		String query = "";

		Scanner pinscan = new Scanner(System.in);
		System.out.println("Please enter user's pin number.");
		String pin = pinscan.next();

		// We need to add a checkPin() method later to check for existing pin

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
			// these need to be here in order to set choice variable
			// to a number 1-4 and nothing else
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
			// generate password
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
		try {
			Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * System.out.
		 * println("What would you like to do? 1. Go back to main prompt, 2. Exit");
		 * Scanner scan = new Scanner(System.in); int thescan = scan.nextInt(); if
		 * (thescan == 1) { actionsMa(); } else {
		 * System.out.println("Exiting.. Goodbye!"); System.exit(1); }
		 */ }

	public void createBook() {
		// variables
		boolean evaluate = false;
		String[] variables = new String[] { "Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: ",
				"Inventory Available: " };
		String bookTitle = "", bookAuthors = "", bookISBN = null, bookPubYear = "", bookKeywords = "", bookInvNum = "";
		CreateBook cb = new CreateBook();
		CheckBookExists cbe = new CheckBookExists();

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter book ISBN number: ");
		bookISBN = scan.nextLine().trim();
		if ((bookISBN != null) && ((bookISBN.length() == 10) || (bookISBN.length() == 13))) {
			evaluate = cbe.checkIfISBNExists(bookISBN);
			if (evaluate == false) {
				cb.setISBN(bookISBN);
				System.out.println("Enter book title.");
				bookTitle = scan.nextLine().trim();
				cb.setBookTitle(bookTitle);
				System.out.println("Enter book authors seperated by commas.");
				cb.setBookAuthors(scan.nextLine().trim());
				System.out.println("Enter book ISBN number.");
				cb.setISBN(scan.nextLine().trim());
				System.out.println("Enter book publication year.");
				cb.setBookPubYear(scan.nextLine().trim());
				System.out.println("Enter book keywords seperated by commas.");
				cb.setBookKeywords(scan.nextLine().trim());
				System.out.println("Enter number of books in inventory.");
				cb.setBookInventoryNumber(scan.nextLine().trim());

				cb.createNewBook();
			} else {
				System.out.println("Book Exists in the database!\n");
				ResultSet result = cbe.returnExistingBook(bookISBN);
				String book = "";
				try {
					while (result.next()) {
						for (int i = 0; i < 6; i++) {
							book = result.getString(i + 2);
							System.out.print(variables[i]);
							System.out.println(book);
						}
						System.out.println("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				System.out.println("");
				System.out.println("Would you like to: 1. Add to inventory, 2. Exit?");
				String scanned = scan.nextLine();
				if (scanned.equals("1")) {
					CreateCheckedBook ccb = new CreateCheckedBook();
					ccb.checkBookUpdateWithoutCreatingNewBook(bookISBN);
				} else if (scanned.equals("2")) {
					actionsMa();
				}
				else {
				}
				// else if (scanned.equals("3")) {
				// System.out.println("Exiting.. Goodbye!");
				// System.exit(1);
				// } else {
				// System.out.println("Invlaid choice. Defaulting to main screen.");
				// actionsMa(); } } }
				// System.out.println("Exiting.. Goodbye!");
			}
		} else {
			System.out.println("You need to enter an ISBN that is either 10 numbers or 13 numbers");
			createBook();
		}
	}
}
