package org.batzlibrary.sprint1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JDBCdriver {
	String jdbc_driver = null;
	
	public String jdbcDriver(){
		try{
			File infile = new File("src/org/batzlibrary/sprint1/jdbcdriver.txt");
			Scanner dataFile = new Scanner(infile);
			jdbc_driver = dataFile.nextLine();
			dataFile.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return jdbc_driver;
	}
}