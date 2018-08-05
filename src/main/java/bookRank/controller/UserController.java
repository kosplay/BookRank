package bookRank.controller;

import bookRank.model.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.*;

/**
 *	Immutable user. 
 */
public class UserController {
	public ArrayList<User> list;
	
	public UserController() {
		list = new ArrayList<User>();
		try {
	        // Create an object of file reader
	        // class with CSV file as a parameter.
			String filePath = new File("").getAbsolutePath();
			filePath = filePath.concat("/src/main/resources/users.csv");
	        FileReader filereader = new FileReader(filePath);
	
	        CSVReader csvReader = new CSVReaderBuilder(filereader)
	                                  .withSkipLines(1)
	                                  .build();
	        List<String[]> allData = csvReader.readAll();

	        for (String[] row : allData) {
	            User user = new User(Integer.parseInt(row[0]), row[1], row[2], row[3]);
	            this.list.add(user);
	        }
	        System.out.println("Loaded users");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
//	public Book create(Book book) {
//		
//	}
}
