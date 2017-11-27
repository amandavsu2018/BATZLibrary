package batzlibrary;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CalculateFines {
	SQL sql = new SQL();

	// make it into a different branch
	public void BookFine(String pinnum, String isbnnum) {
		LocalDate today = LocalDate.now();

		// I didn't get to test out whether the date from our database will work
		// but, it should work with the format
		String grab = "SELECT checkbooks_datecheckedout FROM checkbooks WHERE checkbooks_pin = '" + pinnum + "'"
				+ "AND checkbooks_isbnnum = '" + isbnnum + "'";
		ResultSet result = sql.SQLConnMain(grab);

		// if result, which should be a string(**if not we need to convert it to
		// one**), string comes out in this format "2017-11-26"
		// it should be passed through the dateString Array and be spilt

		// I set result to a string just incase
		String DateData;
		try {
			DateData = ((String) result.getObject(1)).toString();
			String[] dateString = DateData.split("-");

			// Here the date is set to a localdate variable
			LocalDate checkOutBookDay = LocalDate.of(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]),
					Integer.parseInt(dateString[2]));

			// Then the time from the date when the book is checked out is
			// calculated with this
			long sub = ChronoUnit.DAYS.between(checkOutBookDay, today);
			// Then the 14 days are subtracted from the days
			long day = sub - 14;
			// The entire if-else statement says if the book is on time then
			// there is no fee but if its not then the fee is calculated
			// and if the fee is over $25 then the account is locked
			if (day > 0) {
				double FixedFine = .1;
				double Fine = day * FixedFine;
				String take = "SELECT user_fines FROM users WHERE user_pin = '" + pinnum + "'";
				ResultSet result1 = sql.SQLConnMain(take);
				// This sets result1 to a double
				double currentFine = ((Number) result1.getObject(1)).doubleValue();

				double TotalFine = currentFine + Fine;
				String FineAmount = String.format("%.2f", TotalFine);
				if (Fine >= 25) {
					String lock = "true";
					String locking = "UPDATE users SET user_locked = '" + lock + "'" + "WHERE user_pin = '" + pinnum
							+ "'";
					sql.SQLConnMain(locking);
					String FineUpdate = "UPDATE users SET user_fines = '" + FineAmount + "'" + "WHERE user_pin = '"
							+ pinnum + "'";
					sql.SQLConnMain(FineUpdate);
				} else {
					String FineUpdate = "UPDATE users SET user_fines = '" + FineAmount + "'" + "WHERE user_pin = '"
							+ pinnum + "'";
					sql.SQLConnMain(FineUpdate);
				}
			} else {
				System.out.println("No fine(s) accumulated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Should show the fines of the user *only last calculated but could be
	// updated need a loop*
	public String ShowFine() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the users pin?");
		String pinnum = scan.next();
		String UsersFine = "SELECT user_fines FROM users WHERE user_pin = '" + pinnum + "'";
		ResultSet result = sql.SQLConnMain(UsersFine);
		Double Fine;
		try {
			Fine = ((Number) result.getObject(1)).doubleValue();
			return "Your fine amount is: $" + String.format("%.2f", Fine);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
