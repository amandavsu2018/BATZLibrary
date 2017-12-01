package batzlibrary;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckBooksTable {
	SQL s = new SQL();
	String tablename, tablebookid, tablebookisbn, tablebooktitle, tabledatecheckedout, tabledatetoreturn, tablepin;
	String tablerenewalcount, tabledropbox;

	public void setCheckedOutDateOnBook(LocalDate checkoutDate, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_datecheckedout = '" + checkoutDate
				+ "' WHERE checkbooks_id = '" + checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}

	public void setPINNumberOnBook(String pin, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_pin = '" + pin + "' WHERE checkbooks_id = '" + checkbooksID
				+ "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}

	public void setRenewalCountOnBook(String checkbooksID, String count) {
		String query = "UPDATE checkbooks SET checkbooks_renewalcount = '" + count + "' WHERE checkbooks_id = '"
				+ checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}

	public void setReturnDateOnBook(LocalDate returnDate, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_datetoreturn = '" + returnDate + "' WHERE checkbooks_id = '"
				+ checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}

	public ResultSet getCheckbookAvailable(String isbn, String tablenm, String tableid, String tableisbn,
			String tablepin) {
		ResultSet check = null;
		String query = "SELECT " + tableid + " FROM " + tablenm + " WHERE " + tableisbn + " = '" + isbn + "' AND "
				+ tablepin + " IS NULL";
		check = s.SQLConnMain(query);
		return check;
	}

	public void setTableVariables() {
		try {
			File infile = new File("src/batzlibrary/checkbooks.txt");
			Scanner dataFile = new Scanner(infile);
			tablename = dataFile.nextLine();
			tablebookid = dataFile.nextLine();
			tablebookisbn = dataFile.nextLine();
			tablebooktitle = dataFile.nextLine();
			tabledatecheckedout = dataFile.nextLine();
			tabledatetoreturn = dataFile.nextLine();
			tablepin = dataFile.nextLine();
			tablerenewalcount = dataFile.nextLine();
			tabledropbox = dataFile.nextLine();
			dataFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTableName() {
		return tablename;
	}

	public String getTableBookID() {
		return tablebookid;
	}

	public String getTableBookISBN() {
		return tablebookisbn;
	}

	public String getTableBookTitle() {
		return tablebooktitle;
	}

	public String getTableDateCheckedOut() {
		return tabledatecheckedout;
	}

	public String getTableDateToReturn() {
		return tabledatetoreturn;
	}

	public String getTablePin() {
		return tablepin;
	}

	public String getTableRenewalCount() {
		return tablerenewalcount;
	}

	public String getTableDropBox() {
		return tabledropbox;
	}
}