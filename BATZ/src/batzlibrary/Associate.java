package batzlibrary;

import java.util.Scanner;

public class Associate /* extends Member */ {

	// for passing to createUser() method
	String userstatus = "associate";

	public Associate() {
	}

	public void actionsA() {
		boolean sessionOpen = true;
		while (sessionOpen == true) {
			int choice = 0;
			System.out.println("What would you like to do?");
			System.out.println("1: Add User to Database.");
			System.out.println("2: Exit.");
			Scanner choicescan = new Scanner(System.in);
			// get the choice
			while (true) {
				try {
					choice = Integer.parseInt(choicescan.nextLine());
					if (choice == 1) {
						break;
					} else if (choice == 2) {
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
			default:
				sessionOpen = false;
				break;
			// case 2:
			// break;
			}
			// sessionOpen = false;
		}
	}
}