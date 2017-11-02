package batzlibrary;

import java.util.Scanner;

public class Associate /* extends Member */ {

	// for passing to createUser() method
	String userstatus = "associate";
	
	SQL s = new SQL();
	boolean bool;
	String choice = "";
	String lockedStatus = "";
	String pin = "";
	String isbn = "";

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
			System.out.println("4: Exit.");
			Scanner choicescan = new Scanner(System.in);
			// get the choice
			while (true) {
				try {
					choice = Integer.parseInt(choicescan.nextLine());
					if (choice == 1) {
						break;
					} else if (choice == 2) {
						break;
					} else if(choice == 3) {
						break;
					} else if (choice == 4) {
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
				scanPin();
				break;
			case 3:
				System.out.println("Please enter the Member's library card number: ");
				Scanner scanp = new Scanner(System.in);
				pin = scanp.nextLine();
				bool = checkUserExists(pin);
				
				// if user exists, check if corrent user
				if(bool == true) {
					bool = checkCorrectUser(pin);
				} else {
					System.out.println("That library card number does not exists in the database.\n");
					break;
				}
				
				// if correct user, check if locked
				if(bool == true) {
					bool = checkUserLockedStatus(pin);
				} else {
					break;
				}
				
				// if not locked, enter ISBN of book to checkout
				if(bool == true) {
					System.out.println("\nThis user is locked and cannot check out books at this current time.");
					System.out.println("Please contact a manager in order to resolve this issue.\n");
					break;
				}
				
				// check book ISBN number
				CheckBookExists cbe = new CheckBookExists();
				System.out.println("Please enter the ISBN of the book to checkout: ");
				isbn = scanp.nextLine();
				bool = cbe.checkIfISBNExists(isbn);
				if(bool == true) {
					checkOut();
				}
			default:
				sessionOpen = false;
				break;
			// case 2:
			// break;
			}
			// sessionOpen = false;
		}
	}

	public void scanPin() {
		//String pin = "";
		Scanner scanp = new Scanner(System.in);
		
		System.out.println("Enter user's pin: " );
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
	
	public boolean checkUserExists(String pinnum) {
		boolean cuebool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		
		//check to see if user exists
		CheckUserExists cue = new CheckUserExists();
		cue.setPin(pin);
		cuebool = cue.checkIfPinExistsInDB(query);
		return cuebool;
	}
	
	public boolean checkCorrectUser(String pin) {
		boolean cuebool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		CheckUserExists cue = new CheckUserExists();
		cue.setPin(pin);
		cuebool = cue.checkIfPinExistsInDB(query);
		if(cuebool == true) {
			cue.connect();
		}
		System.out.println("\nIs this the correct user? y/n: ");
		Scanner scanp = new Scanner(System.in);
		while(true) {
			choice = scanp.nextLine();
			if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")) {
				cuebool = true;
				break;
			} else if(choice.equals("n") || choice.equals("N") || choice.equals("no")) {
				cuebool = false;
				break;
			} else {
				System.out.println("Please enter a 'y' or an 'n'");
			}
		}
		
		return cuebool;
	}
	
	public boolean checkUserLockedStatus(String pin) {
		boolean cuebool = false;
		String status = "";
		UsersTableCheckout utc = new UsersTableCheckout();
		status = utc.getUserLockedStatus(pin);
		if(status.equals("true")) {
			cuebool = true;
		}
		
		return cuebool;
	}
	
	public void checkOut() {
		
	}
}