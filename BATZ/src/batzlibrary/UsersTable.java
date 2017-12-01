package batzlibrary;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UsersTable {
	SQL s = new SQL();
	String utablename, utableid, utableusername, utablepw, utablepin, utablelocked, utablestatus, utablefname,
			utablelname, utablestreet, utablecity, utablestate, utablezip, utablephone, utablecheckedoutnum;
	String pin;
	String query = "";
	String[] user = new String[11];
	String[] columns = { "Password: ", "Locked: ", "Status: ", "First Name: ", "Last Name: ", "Street: ", "City: ",
			"State: ", "Zip: ", "Phone: ", "Checked Out Books: " };

	public Random random = new Random();

	public boolean checkCorrectUser(String pinnum) {
		boolean ccubool;
		String query = "SELECT * FROM users WHERE user_pin = '" + pinnum + "'";
		String question = "\nIs this the correct user?";
		BATZUtils utils = new BATZUtils();
		setPin(pinnum);
		ccubool = checkIfPinExistsInDB(query);
		if (ccubool == true) {
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

	public Boolean checkUsername(String user) {
		boolean bool = true;
		ResultSet result = null;

		String query = "SELECT * FROM " + utablename + " WHERE " + utableusername + " = '" + user + "'";
		result = s.SQLConnMain(query);

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

	public Boolean checkUserPassword(String user, String pass) {
		boolean bool = true;
		ResultSet result = null;

		String query = "SELECT user_password FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);

		try {
			if (result.next()) {
				String temp = result.getString(1);
				if (temp.equals(pass)) {
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
			if (result.next()) {
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

	public String getFirstname(String user, String fname) {
		ResultSet result = null;
		String query = "SELECT user_firstname FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);

		try {
			if (result.next()) {
				fname = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fname;
	}

	public String getStatus(String user, String status) {
		ResultSet result = null;
		String query = "SELECT user_status FROM users WHERE user_username = '" + user + "'";
		result = s.SQLConnMain(query);

		try {
			if (result.next()) {
				status = result.getString(1);
			}
		} catch (SQLException e) {
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
			if (result.next()) {
				status = result.getString(1);
				if (status.equals("true")) {
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

		// create the pin here, run the checks below
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

	public void setTableVariables() {
		try {
			File infile = new File("src/batzlibrary/users.txt");
			Scanner dataFile = new Scanner(infile);
			utablename = dataFile.nextLine();
			utableid = dataFile.nextLine();
			utableusername = dataFile.nextLine();
			utablepw = dataFile.nextLine();
			utablepin = dataFile.nextLine();
			utablelocked = dataFile.nextLine();
			utablestatus = dataFile.nextLine();
			utablefname = dataFile.nextLine();
			utablelname = dataFile.nextLine();
			utablestreet = dataFile.nextLine();
			utablecity = dataFile.nextLine();
			utablestate = dataFile.nextLine();
			utablezip = dataFile.nextLine();
			utablephone = dataFile.nextLine();
			utablecheckedoutnum = dataFile.nextLine();
			dataFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUtablename() {
		return utablename;
	}

	public void setUtablename(String utablename) {
		this.utablename = utablename;
	}

	public String getUtableid() {
		return utableid;
	}

	public void setUtableid(String utableid) {
		this.utableid = utableid;
	}

	public String getUtableusername() {
		return utableusername;
	}

	public void setUtableusername(String utableusername) {
		this.utableusername = utableusername;
	}

	public String getUtablepw() {
		return utablepw;
	}

	public void setUtablepw(String utablepw) {
		this.utablepw = utablepw;
	}

	public String getUtablepin() {
		return utablepin;
	}

	public void setUtablepin(String utablepin) {
		this.utablepin = utablepin;
	}

	public String getUtablelocked() {
		return utablelocked;
	}

	public void setUtablelocked(String utablelocked) {
		this.utablelocked = utablelocked;
	}

	public String getUtablestatus() {
		return utablestatus;
	}

	public void setUtablestatus(String utablestatus) {
		this.utablestatus = utablestatus;
	}

	public String getUtablefname() {
		return utablefname;
	}

	public void setUtablefname(String utablefname) {
		this.utablefname = utablefname;
	}

	public String getUtablelname() {
		return utablelname;
	}

	public void setUtablelname(String utablelname) {
		this.utablelname = utablelname;
	}

	public String getUtablestreet() {
		return utablestreet;
	}

	public void setUtablestreet(String utablestreet) {
		this.utablestreet = utablestreet;
	}

	public String getUtablecity() {
		return utablecity;
	}

	public void setUtablecity(String utablecity) {
		this.utablecity = utablecity;
	}

	public String getUtablestate() {
		return utablestate;
	}

	public void setUtablestate(String utablestate) {
		this.utablestate = utablestate;
	}

	public String getUtablezip() {
		return utablezip;
	}

	public void setUtablezip(String utablezip) {
		this.utablezip = utablezip;
	}

	public String getUtablephone() {
		return utablephone;
	}

	public void setUtablephone(String utablephone) {
		this.utablephone = utablephone;
	}

	public String getUtablecheckedoutnum() {
		return utablecheckedoutnum;
	}

	public void setUtablecheckedoutnum(String utablecheckedoutnum) {
		this.utablecheckedoutnum = utablecheckedoutnum;
	}
}
