package batzlibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UsersTable {
	SQL s = new SQL();
	String pin;
	String query = "";
	String[] user = new String[11];
	String[] columns = {"Password: ", "Locked: ", "Status: ", "First Name: ", "Last Name: ", "Street: ", "City: ", "State: ", "Zip: ", "Phone: ", "Checked Out Books: "}; 
	
	public Random random = new Random();
	
	public boolean checkCorrectUser(String pinnum) {
		boolean ccubool;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		String question = "\nIs this the correct user?";
		BATZUtils utils = new BATZUtils();
		setPin(pinnum);
		ccubool = checkIfPinExistsInDB(query);
		if(ccubool == true) {
			connect();
			
			Scanner scanp = new Scanner(System.in);
			ccubool = utils.yesOrNo(question);
		} 
		return ccubool;
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
			} else {
				checkIfPinExists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIfPinExists;
	}
	
	public boolean checkUserExists(String pinnum) {
		boolean cuebool = false;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		cuebool = checkIfPinExistsInDB(query);
		return cuebool;
	}
	
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
	
	public boolean getUserLockedStatus(String pin) {
		boolean lockedornot = false;
		String status = "";
		String query = "SELECT user_locked FROM users WHERE user_pin = '" + pin + "'";
		ResultSet result = s.SQLConnMain(query);
		try {
			if(result.next()) {
				status = result.getString(1);
				if(status.equals("true")) {
					lockedornot = true;
				} else {
					lockedornot = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lockedornot;
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
	
	public void setPin(String pinnum) {
		this.pin = pinnum;
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
	
	public void connect() {
		ResultSet result = null;
		int count = 0;
		for(String st : user) {
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
