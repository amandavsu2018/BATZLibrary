package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UsersTable {
	SQL s = new SQL();
	public Random random = new Random();
	
	public Boolean checkUsername(String user, boolean bool) {
		bool = true;
		ResultSet result = null;
		
		String query = "SELECT * FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);
		
		try{
			if(result.first()){
				bool = true;
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public Boolean checkUserPassword(String user, String pass, boolean bool) {
		bool = true;
		ResultSet result = null;
		
		String query = "SELECT user_password FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);
		
		try{
			if(result.next()){
				String temp = result.getString(1);
				if(temp.equals(pass)){
					bool = true;
				} else {
					bool = false;
				}
			} else {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public String getFirstname(String user, String fname){
		ResultSet result = null;
		String query = "SELECT user_firstname FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);	
		
		try{
			if(result.next()){
				fname = result.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return fname;
	}
	
	public String getStatus(String user, String status){
		ResultSet result = null;
		String query = "SELECT user_status FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);
		
		try{
			if(result.next()){
				status = result.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	public String replaceUsername(String user) {
		boolean replace = false;
		String tempuser = user;
		int num = 1;
		String[] stringarray = new String[2];
		String temp = null;
		while (replace == false) {
			String query = "SELECT * FROM users WHERE user_username = '" + user + "'";
			ResultSet result = s.SQLConnMain(query);

			try {
				if (result.first()) {
					stringarray[0] = tempuser;
					stringarray[1] = Integer.toString(num);
					temp = stringarray[0] + stringarray[1];
					num++;
					user = temp;
				} else {
					replace = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	public String generatePin() {
		String pin = "";
		
		//create the pin here, run the checks below
		pin = String.format("%04d", random.nextInt(9999));
		
		String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		
		try {
			int blah = 1;
			if (result.next()) {
				System.out.println(result.getString(blah));

				String temp = result.getString(blah);
				
				if (temp.equals(0)) {
					return pin;
				} else {
					return generatePin();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pin;
	}

	public void scanPin() {
		String pin = "";
		Scanner scanp = new Scanner(System.in);

		System.out.println("Enter user's pin: ");
		pin = scanp.nextLine();
		while (true) {
			boolean pinExists = false;
			String query = "SELECT * FROM users WHERE user_pin = '" + pin + "'";
			CheckUserExists cue = new CheckUserExists();
			cue.setPin(pin);
			pinExists = cue.checkIfPinExistsInDB(query);
			if (pinExists == true) {
				cue.connect();
				break;
			} else {
				System.out.println("Try Again. Please enter a valid pin");
				scanPin();
			}
		}

	}
}
