package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksQueueTable {
	SQL s = new SQL();

	public boolean setBookHold(String isbn, String pin) {
		String[] statement = { isbn, pin };
		String query = "INSERT INTO bookqueue (bookqueue_ISBN, bookqueue_pin)" + " VALUES (?, ?);";
		s.SQLConnForMoreThanOnePreparedStatement(query, statement);
		return true;
	}

	public void displayHolds(String pin) {
		ResultSet result = null;
		String var = "";
		String[] bookinfo = {"ISBN: "};
		String query = "SELECT * FROM bookqueue WHERE bookqueue_pin = '" + pin + "'";
		result = s.SQLConnMain(query);
		try {
			System.out.println("Books user " + pin + "has on hold: ");
			while (result.next()) {
				for (int j = 1; j < 2; j++) {
					var = result.getString(j+1);
					System.out.print(bookinfo[j-1]);
					System.out.println(var);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
