package batzlibrary;

public class CreateCheckedBook {

	SQL s = new SQL();
	
	public void checkBookExisting(String bookISBN, String bookTitle, String bookInvNum) {
		String dateCheckedOut = null;
		String dateToReturn = null;
		String pin = null;
		String renewalCount = null;
		String hold = null;
		
		int bookInv = Integer.parseInt(bookInvNum);
		
		String[] stringarray = {bookISBN, bookTitle, dateCheckedOut, dateToReturn, pin, renewalCount, hold};
		String query = "INSERT INTO checkbooks (checkbooks_ISBN, checkbooks_title, checkbooks_datecheckedout, checkbooks_datetoreturn, checkbooks_pin, checkbooks_renewalcount, checkbooks_hold)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		for(int i = 0; i < bookInv; i++) {
			s.SQLConnForMoreThanOnePreparedStatement(query, stringarray);
		}
	}
}
