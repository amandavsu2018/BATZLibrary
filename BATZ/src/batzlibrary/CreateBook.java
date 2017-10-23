package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateBook {
	String bookTitle = "", bookAuthors = "", bookISBN = "", bookPubYear = "", bookKeywords = "", bookInvNum = "";
	SQL s = new SQL();
	
	public void setBookTitle(String title){
		this.bookTitle = title;
	}
	
	public void setBookAuthors(String authors){
		this.bookAuthors = authors;
	}
	
	public void setISBN(String isbn){
		this.bookISBN = isbn;
	}
	
	public void setBookPubYear(String pubYear){
		this.bookPubYear = pubYear;
	}
	
	public void setBookKeywords(String keywords){
		this.bookKeywords = keywords;
	}
	
	public void setBookInventoryNumber(String invNum){
		this.bookInvNum = invNum;
	}
	
	public boolean createNewBook(){
		String[] stringArray = {bookTitle, bookAuthors, bookISBN, bookPubYear, bookKeywords, bookInvNum};
		CreateCheckedBook cb = new CreateCheckedBook();
		cb.checkBookExisting(bookISBN, bookTitle, bookInvNum);
		String query = "INSERT INTO books (book_title, book_authors, book_ISBN, book_pubyear, book_keywords, book_invnum)"
					+ " VALUES (?, ?, ?, ?, ?, ?);";
		s.SQLConnForMoreThanOnePreparedStatement(query, stringArray);
		return true;
	}
}
