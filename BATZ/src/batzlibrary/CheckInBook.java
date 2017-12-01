package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckInBook {
	public void putInDropBox() {
		SQL sql = new SQL();
		String query = "";
		String isbn = "";
		String userpin = "";
		int choice;
		boolean sessionOpen = true;
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Please enter your pin... for security reasons...");
		userpin = scan.next();
		while (sessionOpen == true) {
			System.out.println("What is the ISBN number?");
			isbn = scan.next();
			query = "UPDATE checkbooks SET checkbooks_dropbox = 'true' WHERE checkbooks_pin = '" + userpin
					+ "'AND checkbooks_ISBN = '" + isbn + "'";

			sql.SQLConnForUpdatingSingleRecord(query);
			System.out.println("Your book has been placed in the bin!");
			System.out.println("Would you like to return another Book?\n" + "1 for yes\n" + "2 for no");
			while (true) {
				try {
					choice = Integer.parseInt(scan1.nextLine());
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
			case 2:
				sessionOpen = false;
				break;
			}
		}
	}

	public void checkDropBox() {
		SQL sql = new SQL();
		CalculateFines cf = new CalculateFines();
		String query1 = "";
		ResultSet result = null;
		int amount = 0;
		String getAmount = "";
		String pin = "", isbn = "";
		int i = 1;
		UsersTableCheckout utc = new UsersTableCheckout();

		query1 = "SELECT checkbooks_pin, checkbooks_ISBN FROM checkbooks WHERE checkbooks_dropbox = 'true'";
		result = sql.SQLConnMain(query1);

		try {
			while (result.next() == true) {
				pin = result.getString(i);
				i++;
				isbn = result.getString(i);
				cf.BookFine(pin, isbn);
				getAmount = utc.getAmountCheckedOut(pin);
				amount = Integer.parseInt(getAmount);
				amount--;
				getAmount = Integer.toString(amount);
				utc.setAmountCheckedOut(pin, getAmount);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		query1 = "UPDATE checkbooks SET checkbooks_datecheckedout = NULL, checkbooks_datetoreturn = NULL, checkbooks_pin = NULL, checkbooks_renewalcount = '0', checkbooks_dropbox = '0' WHERE checkbooks_dropbox = 'true'";
		sql.SQLConnForUpdatingSingleRecord(query1);
	}

}
