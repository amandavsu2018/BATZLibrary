package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchBooks {

	SQL s = new SQL();

	public void choice() {
		String num = "";
		boolean sessionOpen = true;

		while (sessionOpen == true) {
			Scanner how = new Scanner(System.in);
			System.out.println("How would you like to search? 1. ISBN, 2. Keyword, 3. Exit");
			num = how.nextLine();
			if (num.equals("1")) {
				ISBNInput();
			} else if (num.equals("2")) {
				KeywordInput();
			} else if (num.equals("3")) {
				sessionOpen = false;
			} else {
				System.out.println("Invalid input. Try again");
			}
		}
	}

	public void ISBNInput() {
		String query = "";
		String ISBN = "";
		String book = "";
		ResultSet result = null;
		Scanner key = new Scanner(System.in);
		System.out.println("Enter an ISBN");
		ISBN = key.nextLine();

		query = "SELECT * FROM books WHERE book_ISBN = '" + ISBN + "'";
		result = s.SQLConnMain(query);

		String[] variables = new String[] { "Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: ",
				"Inventory Available: " };

		try {
			while (result.next()) {
				for (int i = 2; i < 8; i++) {
					book = result.getString(i);
					System.out.print(variables[i - 2]);
					System.out.println(book);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void KeywordInput() {
		String query = "";
		String keyword = "";
		String book = "";
		ResultSet result = null;
		Scanner key = new Scanner(System.in);
		System.out.println("Enter a keyword");
		keyword = key.nextLine();

		query = "SELECT * FROM books WHERE book_keywords LIKE '%" + keyword + "%'";
		result = s.SQLConnMain(query);

		String[] variables = new String[] { "Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: ",
				"Inventory Available: " };

		try {
			while (result.next()) {
				for (int i = 2; i < 8; i++) {
					book = result.getString(i);
					System.out.print(variables[i - 2]);
					System.out.println(book);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
