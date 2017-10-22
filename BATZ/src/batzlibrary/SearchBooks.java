package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchBooks {

	SQL s = new SQL();

	public void ISBNInput() {
		System.out.println("Enter an ISBN");
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

		String[] variables = new String[]{"Title: ", "Authors: ", "ISBN: ", "Publication Year: ", "Keywords: ", "Inventory Available: "};
		
		try {
			 while(result.next()) {
				for (int i = 2; i < 8; i++) {
					book = result.getString(i);
					System.out.print(variables[i-2]);
					System.out.println(book);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
