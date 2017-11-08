package batzlibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Database {
	String database = null;
	String user = null;
	String pass = null;
	int countlines = 0;
	
	public String getDatabase(){
		try{
			File infile = new File("src/batzlibrary/database.txt");
			BufferedReader br = new BufferedReader(new FileReader(infile));
			this.database = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return database;
	}
	
	public String getDatabaseUser(){
		try{
			File infile = new File("src/batzlibrary/database.txt");
			BufferedReader br = new BufferedReader(new FileReader(infile));
			for(int i=0; i < 1; i++){
				br.readLine();
			}
			this.user = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String getDatabasePassword(){
		try{
			File infile = new File("src/batzlibrary/database.txt");
			BufferedReader br = new BufferedReader(new FileReader(infile));
			for(int i=0; i < 2; i++){
				br.readLine();
			}
			this.pass = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	public String dataBase(){
		try{
			File infile = new File("src/batzlibrary/database.txt");
			Scanner dataFile = new Scanner(infile);
			database = dataFile.nextLine();
			user = dataFile.nextLine();
			pass = dataFile.nextLine();
			dataFile.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return database;
	}
}

