package batzlibrary;

import java.util.Scanner;

public class Manager {

	boolean sessionOpen = true;

	public Manager() {
	}

	public void actionsMa() {
		int choice = 0;
		System.out.println("What would you like to do?\n");
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
			cu.createUser();
			break;
		case 2:
			break;
		}
		choicescan.close();
		sessionOpen = false;
	}
}
