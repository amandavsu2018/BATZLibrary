package batzlibrary;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class ReactivateUser {
	String pinnumber = "";
	SQL s = new SQL();

	public void setPin(String pin) {
		this.pinnumber = pin;
	}

	public boolean checkIfPinExistsInDB(String query) {
		boolean checkIfPinExists = false;
		ResultSet result = s.SQLConnMain(query);
		try {
			if (result.first()) {
				checkIfPinExists = true;
			} else {
				checkIfPinExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfPinExists;
	}

	public boolean updateLockedStatusInDB() {
		String query = "UPDATE users SET user_locked = 'false' WHERE user_pin = '" + pinnumber + "'";
		s.SQLConnMain(query);
		return true;
	}
}
