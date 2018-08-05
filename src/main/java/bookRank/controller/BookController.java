/**
 * Controllers specify allowed actions on entities.
 */
package bookRank.controller;

import bookRank.model.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.*;

/**
 *	Add, remove book. 
 */
public class BookController {
	ArrayList<Book> list;
	
	public BookController() {
		try {
	        // Create an object of file reader
	        // class with CSV file as a parameter.
			String filePath = new File("").getAbsolutePath();
			filePath = filePath.concat("/src/main/resources/books.csv");
	        FileReader filereader = new FileReader(filePath);
	
	        CSVReader csvReader = new CSVReaderBuilder(filereader)
	                                  .withSkipLines(1)
	                                  .build();
	        List<String[]> allData = csvReader.readAll();

	        for (String[] row : allData) {
	            Book b = new Book(Integer.parseInt(row[0]), row[1],  Integer.parseInt(row[2]),  Integer.parseInt(row[3]), row[4], row[5]);
	            this.list.add(b);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
//	public Book create(Book book) {
//		
//	}
}
