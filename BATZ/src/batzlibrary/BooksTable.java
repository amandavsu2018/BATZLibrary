package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksTable {
	SQL s = new SQL();
	String bookTitle, bookAuthors, bookISBN, bookPubYear, bookKeywords, bookInvNum;

	public boolean checkCorrectBook(String isbn) {
		boolean bool = false;
		String[] bookinfo = { "Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: " };
		String var = "";
		String question = "\nIs this the correct book? (y/n)";
		BATZUtils utils = new BATZUtils();
		BooksTable bt = new BooksTable();
		ResultSet result = bt.returnExistingBook(isbn);
		try {
			while (result.next()) {
				for (int i = 1; i < 6; i++) {
					var = result.getString(i + 1);
					System.out.print(bookinfo[i - 1]);
					System.out.println(var);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		bool = utils.yesOrNo(question);

		return bool;
	}

	public boolean checkIfISBNExists(String isbn) {
		boolean checkIfISBNExists = false;
		String query = "SELECT * FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		try {
			if (result.first()) {
				checkIfISBNExists = true;
			} else {
				checkIfISBNExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfISBNExists;
	}

	public ResultSet returnExistingBook(String isbn) {
		String query = "SELECT * FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		return result;
	}

	public ResultSet returnExistingTitle(String isbn) {
		String query = "SELECT book_title FROM books WHERE book_ISBN = '" + isbn + "'";
		ResultSet result = s.SQLConnMain(query);
		return result;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
	}

	public void setBookAuthors(String authors) {
		this.bookAuthors = authors;
	}

	public void setISBN(String isbn) {
		this.bookISBN = isbn;
	}

	public void setBookPubYear(String pubYear) {
		this.bookPubYear = pubYear;
	}

	public void setBookKeywords(String keywords) {
		this.bookKeywords = keywords;
	}

	public void setBookInventoryNumber(String invNum) {
		this.bookInvNum = invNum;
	}
}
