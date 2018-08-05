package bookRank;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import bookRank.controller.*;
import bookRank.model.*;


/**
 * Proposal:
 * 
 * Functionality: 
Recommend books by rating ranking
Recommend books by reading count (popularity) ranking
Read a book (lorem ipsum)
Other tentative features

OOD:
Books
	-Book Name
	-Reading Count (Popularity)
	-Reading Count Ranking
-Ratings Average (computed by sum of all rating per Reading Count)
-Ratings Ranking

Sort: 
Bucket sort for rating
Quick sort of reading count

Search:
Binary Search Tree


Data Strcuture:
	Tree
Array

 * 
 * 
 * Prof. comments on proposal:

Your system needs to load at least 100 records, in your case at last 100 books. Each book must be a class with multiple attributes. You can load the data from local file and submit that file with your project.
Your functionality needs to allow adding and removing a book from the list that was loaded.
A user must have ability to search the books using a criteria that will return multiple books such as all books published in year X or with “vvv” string in the title. Binary search tree can only return a single value so you need to use a different search algorithm. Sequential search is fine.
The sub-list returned from the search needs to be stored in some data structure and sorted to be displayed to the user. You should allow user to decide how to sort that output and you need to allow at least two ways. For example by book title or ranking.
You need to implement two sorts that will do the same functionality (sort the sub-list as indicated above). Quicksort is fine for one of them and then you need another for the analysis. You cannot use bucket sort for that as you need to use algorithm we go over in class.
The rest of your system is fine
 *
 */

@SpringBootApplication
public class BookRankApplication {
	BookController bookCtl;
	UserController userCtl;
	ReviewController reviewCtl;
	
	public BookRankApplication() {
		this.bookCtl = new BookController();
		this.userCtl = new UserController();
		this.reviewCtl = new ReviewController();
		bookCtl.addReviews(reviewCtl.list);
        System.out.println("Loaded reviews to books");
        this.promptActions();
	}
	
	public void promptActions() {
		System.out.println("******************************");
		System.out.println("There are " + bookCtl.list.size() + " book, " + reviewCtl.list.size() + " reviews" + "" );
		System.out.println("What actions would you like to preform?");
		System.out.println("Actions:");
		System.out.println("1. Show 10 books by id, ascending order");
		System.out.println("2. Show 10 reviews by id, ascending order");
		System.out.println("3. Sort and show books by popularity. Quick Sort.");
		System.out.println("4. Sort and show books by popularity. Bubble Sort.");
		System.out.println("5. Add a new book with 0 popularity and 0 rating.");
		System.out.println("6. Add a existing book with x popularity and y rating.");
		System.out.println("7. Remove a book.");
		System.out.println("8.Add a review for a book.");
		System.out.println("9. Sort and show books by review rating. Quick Sort.");
		System.out.println("10. Sort and show books by review rating. Bubble Sort.");
		System.out.println("11. Search by Book Title containing word.");
		System.out.print("Command:  ");
		Scanner scanner = new Scanner(System.in);
		int command = scanner.nextInt();
		this.execute(command);
	}
	
	public void execute(int command) {
		switch (command) {
		case 1:
			this.showBooks(0);
			break;
		case 2:
			this.showReviews(0);
			break;
		case 3:
			this.showBooks(0);
			break;
		case 4:
			this.showBooks(0);
			break;
		case 5:
			this.addNewBook();
			break;
		case 6:
			this.addExistingBook();
			break;
		case 7:
			this.removeBook();
			break;
		case 8:
			this.addReview();
			break;
		case 9:
			this.showBooks(0);
			break;
		case 10:
			this.showBooks(0);
			break;
		case 11:
			this.searchByBookTitleWord();
			break;
		default:
			this.promptActions();
		}
	}
	
	private void showBooks(int start) {
		ArrayList<Book> books = bookCtl.getBooks(start);
		for (Book b: books) {
			System.out.println(b.toString());
		}

		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Would you like to see 10 more? (y/n): ");
		 String response = scanner.next();
		 if (response.equalsIgnoreCase("Y")) {
			 this.showBooks(start + 10);
		 } else {
			 this.promptActions();
		 }
	}
	
	private void showReviews(int start) {
		ArrayList<Review> reviews = reviewCtl.gerReviews(start);
		for (Review r : reviews) {
			System.out.println(r.toString());
		}

		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Would you like to see 10 more? (y/n): ");
		 String response = scanner.next();
		 if (response.equalsIgnoreCase("Y")) {
			 this.showReviews(start + 10);
		 } else {
			 this.promptActions();
		 }
	}
	
	private Book addBook() {
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter ISBN (example: 123456789-0): ");
		 String isbn = scanner.next();
		 System.out.print("Enter year ( > 1970): ");
		 int year = scanner.nextInt();
		 System.out.print("Enter month (1~12): \"");
		 int month = scanner.nextInt();
		 System.out.print("Enter color: ");
		 String color = scanner.next();
		 System.out.print("Enter title: ");
		 String title = scanner.next();
		 Book book = this.bookCtl.create(isbn, year, month, color, title);
		 return book;
	}
	
	private void addNewBook() {
		 Book book = this.addBook();
		 System.out.println("Here is the new book");
		 System.out.println(book.toString());
		 this.promptActions();
	}
	
	private void addExistingBook() {
		 Book book = this.addBook();
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter read count (popularity) ( > 0): ");
		 int popularity = scanner.nextInt();
		 book.popularity = popularity;
		 System.out.print("Enter rating (0.00 ~ 5.00): ");
		 double rating = scanner.nextDouble();
		 book.averageRating = rating;
		 System.out.println("Here is the new book");
		 System.out.println(book.toString());
		 this.promptActions();
	}
	
	private void addReview() {
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter your user_id (1 ~ 100): ");
		 int user_id = scanner.nextInt();
		 System.out.print("Enter book_id (1 ~ " + bookCtl.list.size() + ": ");
		 int book_id = scanner.nextInt();
		 System.out.print("Enter your rating (1~5): ");
		 int rating = scanner.nextInt();
		 System.out.print("Enter review content: ");
		 scanner = new Scanner(System.in);
		 String content = scanner.next();
		 Review r = this.reviewCtl.create(user_id, book_id, rating, content);
		 System.out.println("Created review as following: ");
		 System.out.println(r.toString());
		 System.out.println("Book before the review is added: ");
		 System.out.println(this.bookCtl.get(r.book_id).toString());
		 this.bookCtl.addReview(r);
		 System.out.println("Book after the review is added: ");
		 System.out.println(this.bookCtl.get(r.book_id).toString());
		 this.promptActions();
	}
	
	private void removeBook() {
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Book id to remove: ");
		 int book_id = scanner.nextInt();
		 bookCtl.removeBook(book_id);
		 this.promptActions();
	}
	
	private void searchByBookTitleWord() {
		 Scanner scanner = new Scanner(System.in);
		 System.out.print("Enter a title word to search: ");
		 String needle = scanner.next();
		 ArrayList<Book> result = this.bookCtl.searchByTitleContain(needle);
		 System.out.println("Found " + result.size() + " results.");
		 for (Book b: result) {
			 System.out.println(b.toString());
		 }
		 this.promptActions();
	}

	public static void main(String[] args) {
		SpringApplication.run(BookRankApplication.class, args);
	}
}
