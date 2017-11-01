package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCheckedOutNumber {
	SQL s = new SQL();
	
	public String getAmountCheckedOut(String pin) {
		String amount = null;
		String query = "SELECT checkedoutnumber FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		
		try {
			if(result.next()) {
				amount = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return amount;
	}
	
	public void setAmountCheckedOut(String pin, String amount) {
		String query = "UPDATE users SET user_checkedoutnumber = '" + amount + "' WHERE user_pin = '" + pin + "'";
		s.SQLConnForUpdatingSingleRecord(query);
	}
}