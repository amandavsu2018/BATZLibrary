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

	public void BookFine(String pinnum, String isbnnum) {
		LocalDate today = LocalDate.now();

		String grab = "SELECT checkbooks_datecheckedout FROM checkbooks WHERE checkbooks_pin = '" + pinnum + "'"
				+ " AND checkbooks_ISBN = '" + isbnnum + "'";
		ResultSet result = sql.SQLConnMain(grab);
		String DateData = "";

		try {
			while (result.next()) {
				DateData = result.getString(1);
				String[] dateString = DateData.split("-");

				LocalDate checkOutBookDay = LocalDate.of(Integer.parseInt(dateString[0]),
						Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2]));

				long sub = ChronoUnit.DAYS.between(checkOutBookDay, today);
				long day = sub - 14;

				if (day > 0) {
					double FixedFine = .1;
					double Fine = day * FixedFine;
					double currentFine = 0;
					String take = "SELECT user_fines FROM users WHERE user_pin = '" + pinnum + "'";
					ResultSet result1 = sql.SQLConnMain(take);
					try {
						while (result1.next()) {
							currentFine = Double.parseDouble(result1.getString(1));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
			e.printStackTrace();
		}
		return null;
	}

}
