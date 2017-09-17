package test;

import java.util.Scanner;

public class Member {

	DataBase db = new DataBase();
	String username = "";
	String password = "";
	String role = "";
	String name = "";
	String address = "";
	String phoneNum = "";
	int invCount = 0;

	Scanner input = new Scanner(System.in);
	int countLines = db.count();
	String[][] data = db.dataBase();

	public Member(String username, String password, String role, String name, String address, String phoneNum) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
	}

	public String askUsername() {
		System.out.println("Please enter username: ");
		username = input.nextLine();
		checkUsername(username, data);
		return username;
	}

	public String askPassword() {
		System.out.println("Please enter your password:");
		password = input.nextLine();
		checkPassword(password, data);
		return password;

	}

	public String checkUsername(String username, String[][] data) {
		for (int i = 0; i < countLines; i++) {
			if (data[i][0] == username) {
				username = data[i][0];
				askPassword();
			} else {
				System.out.print("Invalid username");
				askUsername();
			}
		}
		return username;
	}

	public String checkPassword(String password, String[][] data) {
		for (int i = 0; i < countLines; i++) {
			if (data[i][1] == password) {
				password = data[i][1];
				// take to home screen
			} else {
				System.out.print("Invalid password");
				invCount++;
				//make sure not locked out
				if (invCount++ == 3) {
					askPassword();
				}
			}
		}
		return password;
	}
}

