package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTableCheckout {
	SQL s = new SQL();
	
	public String getAmountCheckedOut(String pin) {
		String amount = null;
		String query = "SELECT user_checkedoutnumber FROM users WHERE user_pin = '" + pin + "'";
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
	
	public String getUserLockedStatus(String pin) {
		String status = "";
		String query = "SELECT user_locked FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		try {
			if(result.next()) {
				status = result.getString(1);
			} else {
				status = "false";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
}
