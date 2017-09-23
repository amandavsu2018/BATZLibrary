package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseOld {

	String temp = "";
	int countLines = 0;
	ArrayList<String> dataAL = new ArrayList<String>();

	public String[][] dataBase() {

		try {
			File infile = new File("src/test/Data.txt");
			Scanner dataFile = new Scanner(infile);
			while (dataFile.hasNext()) {
				countLines++;
				dataAL.add(dataFile.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(dataAL.toString());
		System.out.println(countLines);
		String[][] data = new String[countLines][6];

		for (int i = 0; i < countLines; i++) {
			temp = dataAL.get(i);
			String[] arr = temp.split(",");
			for (int j = 0; j < arr.length; j++) {
				data[i][j] = arr[j];
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		return data;
	}
	
	public int count(){
		return countLines;
	}
}