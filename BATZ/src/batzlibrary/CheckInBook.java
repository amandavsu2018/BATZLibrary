package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckInBook {
	public void putInDropBox() {
		SQL sql = new SQL();
		String query = "";
		String isbn = "";
		String userpin = "";
		int choice;
		Scanner scan = new Scanner(System.in);
		// Not sure another way to get user pin
		System.out.println("Please enter your pin... for security reasons...");
		userpin = scan.next();
		while (true) {
			System.out.println("What is the ISBN number?");
			isbn = scan.next();
			query = "UPDATE checkbooks SET checkbooks_dropbox = 'true' WHERE checkbooks_pin = '" + userpin
					+ "'AND checkbooks_ISBN = '" + isbn + "'";

			sql.SQLConnForUpdatingSingleRecord(query);
			System.out.println("Your book has been placed in the bin!");
			System.out.println("Would you like to return another Book?\n" + "1 for yes\n" + "2 for no");
			while (true) {
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice == 1) {
						break;
					} else if (choice == 2) {
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.print("Try again: ");
				}
			}
			switch (choice) {
			case 1:
				break;
			}
		}
	}

	//Needs to check for late fees before updating
	public void checkDropBox() {
		SQL sql = new SQL();
		String query = "UPDATE checkbooks SET checkbooks_datecheckedout = '', checkbooks_datetoreturn = '', checkbooks_pin = '', checkbooks_renewalcount = '0', checkbooks_dropbox = '' WHERE checkbooks_dropbox = 'true'";
		sql.SQLConnForUpdatingSingleRecord(query);
	}

}
