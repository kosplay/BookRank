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
 *	Add review for a book. 
 */
public class ReviewController {
	ArrayList<Review> list;
	
	public ReviewController() {
		try {
	        // Create an object of file reader
	        // class with CSV file as a parameter.
			String filePath = new File("").getAbsolutePath();
			filePath = filePath.concat("/src/main/resources/reviews.csv");
	        FileReader filereader = new FileReader(filePath);
	
	        CSVReader csvReader = new CSVReaderBuilder(filereader)
	                                  .withSkipLines(1)
	                                  .build();
	        List<String[]> allData = csvReader.readAll();

	        for (String[] row : allData) {
	            Review review = new Review(Integer.parseInt(row[0]), Integer.parseInt(row[0]),  Integer.parseInt(row[2]),  Integer.parseInt(row[3]), row[4]);
	            this.list.add(review);
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
