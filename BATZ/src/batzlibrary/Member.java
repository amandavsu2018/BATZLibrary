package batzlibrary;

import java.util.Scanner;

public class Member {

	public Member(){
	}

	public void actionsMe() {
		boolean sessionOpen = true;
		while (sessionOpen == true) {
			int choice = 0;
			System.out.println("What would you like to do?");
			System.out.println("1: Search for books");
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
				SearchBooks sb = new SearchBooks();
				sb.KeywordInput();
				break;
			default:
				sessionOpen = false;
				break;
			}
		}
	}
}
