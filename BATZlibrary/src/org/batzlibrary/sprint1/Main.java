package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int countLines = 1;
		String username = "";
		String password = "";
		String role = "";
		String name = "";
		String address = "";
		String phoneNum = "";
		
		DataBase db = new DataBase();
		Member m = new Member(username, password, role, name, address, phoneNum);
		
		String[][] data = db.dataBase();
		countLines = db.count();
		
		String un = m.askUsername();
		String pw = m.askPassword();
		String unCheck = m.checkUsername(username, data);
		String pwCheck = m.checkPassword(password, data);
		System.out.println(un);
	}

}
