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
		// Not sure another way to get user pin
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

	// Needs to check for late fees before updating
	public void checkDropBox() {
		SQL sql = new SQL();
		String query = "";
		ResultSet result = null;
		int amount = 0;
		String getAmount = "";
		String pin = "";
		int i = 1;
		UsersTableCheckout utc = new UsersTableCheckout();
		
		query = "SELECT checkbooks_pin FROM checkbooks WHERE checkbooks_dropbox = 'true'";
		result = sql.SQLConnMain(query);
		
		try {
			while(result.next() == true) {
				pin = result.getString(i);
				getAmount = utc.getAmountCheckedOut(pin);
				amount = Integer.parseInt(getAmount);
				amount--;
				getAmount = Integer.toString(amount);
				utc.setAmountCheckedOut(pin, getAmount);
				i++;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	
		/*
		query = "SELECT checkbooks_pin FROM checkbooks WHERE checkbooks_dropbox = 'true'";
		result = sql.SQLConnMain(query);
		
		try{
			while(result.next()){
					pin = result.getString(i);
					query = "SELECT user_checkedoutnumber FROM users WHERE user_pin = '" + pin + "'";
					sql.SQLConnMain(query);
					try {
						while(result.next()) {
							inv = result.getString(1);
							x = Integer.parseInt(inv);
							x--;
							inv = Integer.toString(x);
							query = "UPDATE users SET user_checkedoutnumber = inv WHERE user_pin = '" + pin + "'";
							sql.SQLConnForUpdatingSingleRecord(query);
						}
					}
					catch (SQLException e){
					e.printStackTrace();
					}
					i++;
				}
				System.out.println("");
		} catch (SQLException e){
			e.printStackTrace();
		}
		*/
		
		query = "UPDATE checkbooks SET checkbooks_datecheckedout = NULL, checkbooks_datetoreturn = NULL, checkbooks_pin = NULL, checkbooks_renewalcount = '0', checkbooks_dropbox = '0' WHERE checkbooks_dropbox = 'true'";
		sql.SQLConnForUpdatingSingleRecord(query);	
	}

}
