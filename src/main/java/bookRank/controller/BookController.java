package bookRank.controller;

import bookRank.model.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import com.opencsv.*;

/**
 *	Add, remove book. 
 */
public class BookController {
	public ArrayList<Book> list;
	
	public BookController() {
		list = new ArrayList<Book>();
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
	        System.out.println("Loaded books");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public Book create(String isbn, int year, int month, String color, String title) {
		int id = list.size() + 1;
		Book newBook = new Book(id, isbn, year, month, color, title);
		return newBook;
	}
	
	public Book get(int id) {
		for (Book book : this.list) {
			if (book.id == id) {
				return book;
			}
		}
		return null;
	}
	
	public ArrayList<Book> getBooks(int start) {
		ArrayList<Book> result = new ArrayList<Book>();
		int end = start+10;
		for (int i=start; i<end; i++) {
			result.add(list.get(i));
		}
		return result;
	}
	
	public ArrayList<Book> searchByTitleContain(String str) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.list) {
			if (book.title.matches("(.*)"+ str +"(.*)")) {
				result.add(book);
			}
		}
		return result;
	}
	
	public void addReview(Review review) {
		for (Book book : this.list) {
			if (book.id == review.book_id) {
				book.addReview(review);
			}
		}
	}
	
	public void removeBook(int book_id) {
		for (Book b: this.list) {
			if (b.id == book_id) {
				list.remove(b);
				System.out.println("The following book is removed!");
				System.out.println(b);
			}
		}
	}
	
	public void addReviews(ArrayList<Review> reviews) {
		for (Review review: reviews) {
			this.addReview(review);
		}
	}
}
