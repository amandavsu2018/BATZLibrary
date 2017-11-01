package batzlibrary;

import java.util.Scanner;

public class Associate /* extends Member */ {

	// for passing to createUser() method
	String userstatus = "associate";
	
	SQL s = new SQL();
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
				checkOut();
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
	
	public void checkOut() {
		//String pin = "":
		boolean cuebool = false;
		System.out.println("Please enter the Member's library card number: ");
		Scanner scanp = new Scanner(System.in);
		pin = scanp.nextLine();
		
		//check to see if user exists
		CheckUserExists cue = new CheckUserExists();
		cue.setPin(pin);
		cuebool = cue.checkPinOnly();
		
		// if correct pin, check locked status
		if(cuebool == true) {
			System.out.println("");
		} else {
			System.out.println("This library card number does not exist in the database. Please try again.");
			checkOut();
		}
	}
}