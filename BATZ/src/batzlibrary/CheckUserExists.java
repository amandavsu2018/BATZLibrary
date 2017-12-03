package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckUserExists {
	String pin = "";
	SQL s = new SQL();
	String query = "";
	String[] user = new String[12];
	String[] columns = { "Password: ", "Locked: ", "Status: ", "First Name: ", "Last Name: ", "Street: ", "City: ",
			"State: ", "Zip: ", "Phone: ", "Checked Out Books: ", "Fines: " };

	public void setPin(String pinnum) {
		this.pin = pinnum;
	}

	public boolean checkPinOnly() {
		boolean bool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);

		try {
			if (result.first()) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bool;
	}

	public boolean checkIfPinExistsInDB(String query) {
		boolean checkIfPinExists = false;
		ResultSet result = s.SQLConnMain(query);
		try {
			if (result.next()) {
				checkIfPinExists = true;
				setPin(pin);
				getUserPassword(pin);
				getUserLocked(pin);
				getUserStatus(pin);
				getUserFN(pin);
				getUserLN(pin);
				getUserStreet(pin);
				getUserCity(pin);
				getUserState(pin);
				getUserZip(pin);
				getUserPhone(pin);
				getCheckedOutNum(pin);
				getFines(pin);
			} else {
				checkIfPinExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfPinExists;
	}

	public void getUserPassword(String pin) {
		query = "SELECT user_password FROM users WHERE user_pin = '" + pin + "'";
		user[0] = query;
	}

	public void getUserLocked(String pin) {
		query = "SELECT user_locked FROM users WHERE user_pin = '" + pin + "'";
		user[1] = query;
	}

	public void getUserStatus(String pin) {
		query = "SELECT user_status FROM users WHERE user_pin = '" + pin + "'";
		user[2] = query;
	}

	public void getUserFN(String pin) {
		query = "SELECT user_firstname FROM users WHERE user_pin = '" + pin + "'";
		user[3] = query;
	}

	public void getUserLN(String pin) {
		query = "SELECT user_lastname FROM users WHERE user_pin = '" + pin + "'";
		user[4] = query;
	}

	public void getUserStreet(String pin) {
		query = "SELECT user_street FROM users WHERE user_pin = '" + pin + "'";
		user[5] = query;
	}

	public void getUserCity(String pin) {
		query = "SELECT user_city FROM users WHERE user_pin = '" + pin + "'";
		user[6] = query;
	}

	public void getUserState(String pin) {
		query = "SELECT user_state FROM users WHERE user_pin = '" + pin + "'";
		user[7] = query;
	}

	public void getUserZip(String pin) {
		query = "SELECT user_zip FROM users WHERE user_pin = '" + pin + "'";
		user[8] = query;
	}

	public void getUserPhone(String pin) {
		query = "SELECT user_phone FROM users WHERE user_pin = '" + pin + "'";
		user[9] = query;
	}

	public void getCheckedOutNum(String pin) {
		query = "SELECT user_checkedoutnumber FROM users WHERE user_pin = '" + pin + "'";
		user[10] = query;
	}
	
	public void getFines(String pin) {
		query = "SELECT user_fines FROM users WHERE user_pin = '" + pin + "'";
		user[11] = query;
	}
	
	public void connect() {
		ResultSet result = null;
		int count = 0;
		for (String st : user) {
			result = s.SQLConnMain(st);
			try {
				if (result.first()) {
					System.out.print(columns[count]);
					System.out.println(result.getString(1));
					count++;
				} else {
					System.out.println(result.next());
					System.out.println("Error");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
