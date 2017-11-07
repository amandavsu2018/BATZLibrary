package batzlibrary;

import java.sql.ResultSet;
import java.util.Scanner;

public class Associate /* extends Member */ {

	// for passing to createUser() method
	String userstatus = "associate";
	UsersTable ut = new UsersTable();

	SQL s = new SQL();
	boolean bool;
	String choice = "";
	String lockedStatus = "";
	String pin = "";
	String isbn = "";
	int numberOfCheckedOutBooks = 0;

	public Associate() {
	}

	public void actionsA() {
		boolean sessionOpen = true;
		while (sessionOpen == true) {
			int choice = 0;
			System.out.println("What would you like to do?");
			System.out.println("1: Add User to Database.");
			System.out.println("2: Display User Info. ");
			System.out.println("3: Checkout Book for Member");
			System.out.println("4: Check In Books From Dropbox ");
			System.out.println("5: Exit.");
			Scanner choicescan = new Scanner(System.in);
			// get the choice
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
						sessionOpen = false;
						break;
					} else {
						sessionOpen = false;
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
				ut.scanPin();
				break;
			case 3:
				case3();
				break;
			case 4:
				CheckInBook cib = new CheckInBook();
				cib.checkDropBox();
				break;
			default:
				sessionOpen = false;
				break;
			// case 2:
			// break;
			}
			// sessionOpen = false;
		}
	}

/*	public void scanPin() {
		// String pin = "";
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
				System.out.println("Invalid Pin. Try Again.");
				scanPin();
			}
		}

	}
*/
	public void case3() {
		while (true) {
			CheckOutBook cob = new CheckOutBook();
			System.out.println("Please enter the Member's library card number: ");
			Scanner scanp = new Scanner(System.in);
			pin = scanp.nextLine();
			bool = cob.checkUserExists(pin);

			// if user exists, check if corrent user
			if (bool == true) {
				bool = cob.checkCorrectUser(pin);
			} else {
				System.out.println("That library card number does not exists in the database.\n");
				break;
			}

			// if correct user, check if locked
			if (bool == true) {
				bool = cob.checkUserLockedStatus(pin);
			} else {
				break;
			}

			// if not locked, enter ISBN of book to checkout
			if (bool == true) {
				System.out.println("\nThis user is locked and cannot check out books at this current time.");
				System.out.println("Please contact a manager in order to resolve this issue.\n");
				break;
			}

			// check book ISBN number
			CheckBookExists cbe = new CheckBookExists();
			System.out.println("Please enter the ISBN of the book to checkout: ");
			isbn = scanp.nextLine();
			bool = cbe.checkIfISBNExists(isbn);
			if (bool == true) {
				bool = cob.checkCorrectBook(isbn);
			} else {
				System.out.println("This book ISBN does not exist in the database.");
				break;
			}

			// user & author correct, check number of books checked out for user
			if (bool == true) {
				UsersTableCheckout utc = new UsersTableCheckout();
				numberOfCheckedOutBooks = Integer.parseInt(utc.getAmountCheckedOut(pin));
			} else {
				break;
			}
			
			// if numberOfCheckedOutBooks < 10, proceed
			if(numberOfCheckedOutBooks < 10){
				cob.checkOut(isbn, pin);
				break;
			} else {
				System.out.println("You have 10 books checked out already. You are allowed a maximum of 10 books." );
				break;
			}
		}
	}
}