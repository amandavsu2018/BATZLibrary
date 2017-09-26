package batzlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager {

	boolean sessionOpen = true;

	public Manager() {
	}

	public void actionsMa() {
		int choice = 0;
		System.out.println("What would you like to do?\n");
		System.out.println("1: Add User to Database.");
		System.out.println("2: Create book listing.");
		System.out.println("3: Exit.");
		Scanner choicescan = new Scanner(System.in);
		// get the choice
		while (true) {
			try {
				choice = Integer.parseInt(choicescan.nextLine());
				if (choice == 1) {
					break;
				} else if (choice == 2) {
					break;
				} else if (choice == 3)
					break;
			} catch (NumberFormatException nfe) {
				System.out.print("Try again: ");
			}
		}

		// switch cases
		switch (choice) {
		case 1:
			CreateUser cu = new CreateUser();
			cu.createUser();
			break;
		case 2:
			createBook();
		case 3:
			break;
		}
		choicescan.close();
		sessionOpen = false;
	}

	public void createBook(){
		//variables
		String bookTitle = null, bookAuthors = "", bookISBN = "", bookPubYear = "", bookKeywords = "", bookInvNum = "";
		Database db = new Database();
		String database = new String(db.getDatabase());
		String databaseUser = new String(db.getDatabaseUser());
		String databasePass = new String(db.getDatabasePassword());
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter book title.");
		bookTitle = scan.nextLine().trim();
		if (bookTitle != null) {
			System.out.println("Enter book authors seperated by commas.");
			bookAuthors = scan.nextLine().trim();
			System.out.println("Enter book ISBN number.");
			bookISBN = scan.nextLine().trim();
			System.out.println("Enter book publication year.");
			bookPubYear = scan.nextLine().trim();
			System.out.println("Enter book keywords seperated by commas.");
			bookKeywords = scan.nextLine().trim();
			System.out.println("Enter number of books in inventory.");
			bookInvNum = scan.nextLine().trim();
		
			try {
				Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
				System.out.println("Connection Object Created.\n");

				String query = "INSERT INTO books (book_title, book_authors, book_ISBN, book_pubyear, book_keywords, book_invnum)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, bookTitle);
				preparedStmt.setString(2, bookAuthors);
				preparedStmt.setString(3, bookISBN);
				preparedStmt.setString(4, bookPubYear);
				preparedStmt.setString(5, bookKeywords);
				preparedStmt.setString(6, bookInvNum);

				System.out.println("Attempting database connection..\n");
				preparedStmt.execute();
				conn.close();
				System.out.println("Updated database successfully.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
