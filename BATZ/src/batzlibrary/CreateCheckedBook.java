package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateCheckedBook {

	SQL s = new SQL();
	String bookTitle = "";
	
	public void setBookTitle(String title){
		this.bookTitle = title;
	}
	
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
	
	public boolean checkBookUpdateWithoutCreatingNewBook(String isbn){
		CheckBookExists cbe = new CheckBookExists();
		Scanner scan = new Scanner(System.in);
		BookInventory bi = new BookInventory();
		String invnumber = bi.checkInventoryNumberViaISBN(isbn);
		String ccbnumber = "";
		ResultSet title = cbe.returnExistingTitle(isbn);
		try{
			while(title.next()){
				bookTitle = title.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("How many would you like to add to inventory?");
		int inv = scan.nextInt();
		ccbnumber = Integer.toString(inv);
		inv = inv + Integer.parseInt(invnumber);
		invnumber = Integer.toString(inv);
		checkBookExisting(isbn, bookTitle, ccbnumber);
		bi.updateBookInventoryNumberViaISBN(isbn, inv);
		//scan.close();
		return true;
	}
}
