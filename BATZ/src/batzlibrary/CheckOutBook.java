package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CheckOutBook {
	SQL s = new SQL();
	int noOfDays = 14;
	String id = "";
	String pin = "";
	String isbn = "";
	String title = "";
	String choice = "";

	public void checkOut(String isbnnum, String pinnum) {
		CheckBooksTable cbt = new CheckBooksTable();
		ResultSet result = null;
		boolean cobool = false;
		String checkbooksid = "";

		cbt.setTableVariables();
		result = cbt.getCheckbookAvailable(isbnnum, cbt.getTableName(), cbt.getTableBookID(), cbt.getTableBookISBN(),
				cbt.getTablePin());
		try {
			if (result.first() == true) {
				checkbooksid = result.getString(1);
				checkOutBook(checkbooksid, pinnum);
				System.out.println("Book Checked out successfully!\n");
			} else {
				String question = "This book's inventory is currently all checked out.\n\nWould you like to place a hold on this book? (y/n)";
				Scanner scanp = new Scanner(System.in);
				BATZUtils utils = new BATZUtils();
				cobool = utils.yesOrNo(question);
				if (cobool == true) {
					BooksQueueTable bqt = new BooksQueueTable();
					bqt.setBookHold(isbnnum, pinnum);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Check out a book, update checkedbooks table, users table
	public boolean checkOutBook(String checkbookid, String pinnum) {
		CheckbooksTableMethods cbtm = new CheckbooksTableMethods();
		UsersTableCheckout utc = new UsersTableCheckout();
		BATZUtils utils = new BATZUtils();
		int amount = 0;
		String getAmount = "";
		LocalDate today = utils.getCurrentDate();
		LocalDate twoWeeksDate = utils.getTwoWeeksDate(today);

		// update checbooks table
		cbtm.setCheckedOutDateOnBook(today, checkbookid);
		cbtm.setReturnDateOnBook(twoWeeksDate, checkbookid);
		cbtm.setPINNumberOnBook(pinnum, checkbookid);

		// update users table (amount of books checked out)
		getAmount = utc.getAmountCheckedOut(pinnum);
		amount = Integer.parseInt(getAmount);
		amount++;
		getAmount = Integer.toString(amount);
		utc.setAmountCheckedOut(pinnum, getAmount);

		return true;
	}
}
