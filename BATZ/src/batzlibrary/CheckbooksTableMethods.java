package batzlibrary;

import java.sql.ResultSet;

public class CheckbooksTableMethods {
	SQL s = new SQL();
	
	public void setCheckedOutDateOnBook(String checkoutDate, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_datecheckedout = '" + checkoutDate + "' WHERE checkbooks_id = '" + checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}
	
	public void setPINNumberOnBook(String pin, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_pin = '" + pin + "' WHERE checkbooks_id = '" + checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}
	
	public void setRenewalCountOnBook(String checkbooksID, String count) {
		String query = "UPDATE checkbooks SET checkbooks_renewalcount = '" + count + "' WHERE checkbooks_id = '" + checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}
	
	public void setReturnDateOnBook(String returnDate, String checkbooksID) {
		String query = "UPDATE checkbooks SET checkbooks_datetoreturn = '" + returnDate + "' WHERE checkbooks_id = '" + checkbooksID + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}
	
	public ResultSet getCheckbookAvailable(String isbn){
		ResultSet check = null;
		String query = "SELECT checkbooks_id FROM checkbooks WHERE checkbooks_ISBN = '" + isbn + "' AND checkbooks_pin IS NULL";
		check = s.SQLConnMain(query);
		return check;
	}
}