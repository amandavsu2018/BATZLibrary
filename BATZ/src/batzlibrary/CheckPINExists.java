package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckPINExists {
	SQL s = new SQL();
	
	public boolean scanLibraryCard(String pin) {
		boolean bool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		
		try {
			if(result.first()) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bool;
	}
}