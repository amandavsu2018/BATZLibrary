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
			System.out.println("4: Edit Book.");
			System.out.println("5: Reactivate a User Account");
			System.out.println("6: Display User Info");
			System.out.println("7: Exit.");
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
						break;
					} else if (choice == 6) {
						break;
					} else if (choice == 7) {
						sessionOpen = false;
						break;
					} else {
						sessionOpen = false;
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.print("Try again: ");
				}
				// choicescan.close();
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
			 */
			case 3:
				editUser();
				break;
			case 4:
				editBook();
				break;
			case 5:
				reactivateUser();
				break;
			// case 5:
			// sessionOpen = false;
			// break;
			case 6:
				scanPin();
				break;
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
	}

	public void editBook() {
		int choice = 0;
		int run = 0;
		String query1 = "";
		String query2 = "";
		String query3 = "";

		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());

		Scanner isbnnum = new Scanner(System.in);
		System.out.println("Please enter the book's ISBN.");
		String isb = isbnnum.next();

		CheckBookExists check = new CheckBookExists();
		if (check.checkIfISBNExists(isb)) {
			System.out.println("What would you like to edit?\n");
			System.out.println("1: Edit Title.");
			System.out.println("2: Edit Author.");
			System.out.println("3: Edit ISBN.");
			System.out.println("4: Edit Publishing Year.");
			System.out.println("5: Edit Keywords. (Enter keywords seperated by commas.)");
			System.out.println("6: Edit Inventory Number. (do not use)");
			System.out.println("7: Exit.");
			Scanner choicescan = new Scanner(System.in);
			while (true) {
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
					}
				} catch (NumberFormatException nfe) {
					System.out.print("Try again: ");
				}
			}

			Scanner scan = new Scanner(System.in);

			// switch cases
			switch (choice) {
			case 1:
				System.out.println("Title: ");
				String title = scan.nextLine();
				query1 = "UPDATE books SET book_title = '" + title + "'WHERE book_ISBN = '" + isb + "'";
				query2 = "UPDATE checkbooks SET checkbooks_title = '" + title + "'WHERE checkbooks_ISBN = '" + isb
						+ "'";
				query3 = "UPDATE bookqueue SET bookqueue_title = '" + title + "'WHERE bookqueue_ISBN = '" + isb + "'";
				run = 2;
				break;
			case 2:
				System.out.println("Author: ");
				String author = scan.nextLine();
				query1 = "UPDATE books SET book_authors = '" + author + "'WHERE book_ISBN = '" + isb + "'";
				run = 1;
				break;
			case 3:
				System.out.println("ISBN: ");
				String isbn = scan.nextLine();
				query1 = "UPDATE books SET book_ISBN = '" + isbn + "'WHERE book_ISBN = '" + isb + "'";
				query2 = "UPDATE checkbooks SET checkbooks_ISBN = '" + isbn + "'WHERE checkbooks_ISBN = '" + isb + "'";
				query3 = "UPDATE bookqueue SET bookqueue_ISBN = '" + isbn + "'WHERE bookqueue_ISBN = '" + isb + "'";
				run = 2;
				break;
			case 4:
				System.out.println("Publishing Year: ");
				String pubyear = scan.nextLine();
				query1 = "UPDATE books SET book_pubyear = '" + pubyear + "'WHERE book_ISBN = '" + isb + "'";
				run = 1;
				break;
			case 5:
				System.out.println("KeyWords:(seperated by commas) ");
				String keyword = scan.nextLine();
				query1 = "UPDATE books SET book_keywords = '" + keyword + "'WHERE book_ISBN = '" + isb + "'";
				run = 1;
				break;
			/*
			 * Need to meet together to talk about how to do this!!!! case 6:
			 * System.out.println("Inventory Number: "); String invnum = scan.nextLine();
			 * query1 = "UPDATE books SET book_invnum = '" + invnum + "'WHERE book_ISBN = '"
			 * + isb + "'"; break;
			 */
			default:
				break;
			}
			// checks if there are 1 or 2 connections needed.
			if (run == 1) {
				SQL sql = new SQL();
				sql.SQLConnForUpdatingSingleRecord(query1);
			} else if (run == 2) {
				SQL sql = new SQL();
				sql.SQLConnForUpdatingSingleRecord(query1);
				sql.SQLConnForUpdatingSingleRecord(query2);
				sql.SQLConnForUpdatingSingleRecord(query3);
			}
		} else {
			System.out.println("That ISBN does not exist!");
		}
	}

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
				} else {
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

	public void scanPin() {
		String pin = "";
		Scanner scanp = new Scanner(System.in);

		System.out.println("Enter user's pin: ");
		pin = scanp.nextLine();
		while (true) {
			boolean pinExists = false;
			String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
			CheckUserExists cue = new CheckUserExists();
			cue.setPin(pin);
			pinExists = cue.checkIfPinExistsInDB(query);
			if (pinExists == true) {
				cue.connect();
				break;
			} else {
				System.out.println("Try Again. Please enter a valid pin");
				scanPin();
			}
		}

	}

}
