package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBookExists {
	SQL s = new SQL();
	
	public boolean checkIfISBNExists(String isbn){
		boolean checkIfISBNExists = false;
		String query = "SELECT * FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		try{
			if(result.first()){
				checkIfISBNExists = true;
			} else {
				checkIfISBNExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfISBNExists;
	}
	
	public boolean checkIfTitleExists(String title){
		boolean checkIfTitleExists = false;
		String query = "SELECT * FROM books WHERE book_title = '" + title + "'";
		ResultSet result = s.SQLConnMain(query);
		try{
			if(result.first()){
				checkIfTitleExists = true;
			} else {
				checkIfTitleExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfTitleExists;
	}
	
	public ResultSet returnExistingBook(String isbn){
		String query = "SELECT * FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		return result;
	}
	
	public ResultSet returnExistingTitle(String isbn){
		String query = "SELECT book_title FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		return result;
	}
}
