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
	public ArrayList<Review> list;
	
	public ReviewController() {
		list = new ArrayList<Review>();
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
	        System.out.println("Loaded reviews");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public Review create(int user_id, int book_id, int rating, String content) {
		int id = this.list.size() + 1;
		Review newReview = new Review(id, user_id, book_id, rating, content);
		return newReview;
	}
	
	public ArrayList<Review> gerReviews(int start) {
		ArrayList<Review> result = new ArrayList<Review>();
		int end = start+10;
		for (int i=start; i<end; i++) {
			result.add(list.get(i));
		}
		return result;
	}
}
