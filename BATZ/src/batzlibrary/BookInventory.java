package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInventory {
	String inventoryNumber = "";
	SQL s = new SQL();
	
	public String checkInventoryNumberViaISBN(String isbn){
		String query = "SELECT book_invnum FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		try{
			while(result.next()){
				inventoryNumber = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryNumber;
	}
	
	public String checkInventoryNumberViaTitle(String title){
		String query = "SELECT book_invnum FROM books WHERE book_title = '" + title + "'";
		ResultSet result = s.SQLConnMain(query);
		try{
			while(result.next()){
				inventoryNumber = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryNumber;
	}
	
	public boolean updateBookInventoryNumberViaISBN(String isbn, int number){
		String query = "UPDATE books SET book_invnum = '" + number + "'WHERE book_ISBN = '" + isbn + "'";
		s.SQLConnForUpdatingSingleRecord(query);
		return true;
	}
	
	public boolean updateBookInventoryViaTitle(String title){
		String query = "UPDATE books SET book_invnum = '" + inventoryNumber + "'WHERE book_ISBN = '" + title + "'";
		s.SQLConnForUpdatingSingleRecord(query);
		return true;
	}
}
