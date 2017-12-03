package batzlibrary;

import java.sql.ResultSet;
import java.time.LocalDate;
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
			System.out.println("\n" + "What would you like to do?");
			System.out.println("1: Add User to Database.");
			System.out.println("2: Display User Info. ");
			System.out.println("3: Checkout Book for Member");
			System.out.println("4: Check In Books From Dropbox ");
			System.out.println("5. Display Holds for User");
			System.out.println("6: Exit.");
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
						break;
					} else if (choice == 6) {
						sessionOpen = false;
						break;
					} else if (choice == 696) {
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
				checkOutBk();
				break;
			case 4:
				CheckInBook cib = new CheckInBook();
				cib.checkDropBox();
				break;
			case 5:
				disHolds();
				break;
			case 696:
				wormHole();
				break;
			default:
				sessionOpen = false;
				break;
			}
		}
	}

	public void checkOutBk() {
		while (true) {
			CheckOutBook cob = new CheckOutBook();
			UsersTable userstable = new UsersTable();
			System.out.println("Please enter the Member's library card number: ");
			Scanner scanp = new Scanner(System.in);
			pin = scanp.nextLine();
			bool = userstable.checkUserExists(pin);

			// if user exists, check if corrent user
			if (bool == true) {
				bool = userstable.checkCorrectUser(pin);
			} else {
				System.out.println("That library card number does not exists in the database.\n");
				break;
			}

			// if correct user, check if locked
			if (bool == true) {
				bool = userstable.getUserLockedStatus(pin);
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
			BooksTable bt = new BooksTable();
			System.out.println("Please enter the ISBN of the book to checkout: ");
			isbn = scanp.nextLine();
			bool = bt.checkIfISBNExists(isbn);
			if (bool == true) {
				bool = bt.checkCorrectBook(isbn);
			} else {
				System.out.println("This book ISBN does not exist in the database.");
				break;
			}

			// user & author correct, check number of books checked out for user
			if (bool == true) {
				numberOfCheckedOutBooks = Integer.parseInt(userstable.getAmountCheckedOut(pin));
			} else {
				break;
			}

			// if numberOfCheckedOutBooks < 10, proceed
			if (numberOfCheckedOutBooks < 10) {
				cob.checkOut(isbn, pin);
				break;
			} else {
				System.out.println("You have 10 books checked out already. You are allowed a maximum of 10 books.");
				break;
			}
		}
	}

	private void disHolds() {
		UsersTable userstable = new UsersTable();
		System.out.println("Please enter the Member's library card number: ");
		Scanner scanp = new Scanner(System.in);
		pin = scanp.nextLine();
		bool = userstable.checkUserExists(pin);

		if (bool == true) {
			BooksQueueTable bqt = new BooksQueueTable();
			bqt.displayHolds(pin);
		} else {
			System.out.println("Please enter a valid pin");
		}

	}

	public void wormHole() {
		SQL sql = new SQL();
		BATZUtils bu = new BATZUtils();
		int timetravel;
		String omgwut, lunchmoney;
		LocalDate today = bu.getCurrentDate();
		LocalDate wormhole;
		String pushtotable;
		System.out.println("Welcome to the Wormhole. What time period would you like to visit?\n");
		Scanner hammertime = new Scanner(System.in);
		timetravel = hammertime.nextInt();
		wormhole = bu.subtractDays(today, timetravel);
		System.out.println("Please enter the pin number of the Average Joe you want to change.");
		omgwut = hammertime.next();
		System.out.println("Please enter the ISBN number that Average Joe has checked out.");
		lunchmoney = hammertime.next();
		pushtotable = "UPDATE checkbooks SET checkbooks_datecheckedout = '" + wormhole + "'WHERE checkbooks_pin = '"
				+ omgwut + "' AND checkbooks_ISBN = '" + lunchmoney + "'";
		sql.SQLConnForUpdatingSingleRecord(pushtotable);
	}
}