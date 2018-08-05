package bookRank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import bookRank.controller.*;
import bookRank.model.*;

@SpringBootApplication
public class BookRankApplication {
	
	public BookRankApplication() {
		BookController bookCtl = new BookController();
	}

	public static void main(String[] args) {
		SpringApplication.run(BookRankApplication.class, args);
	}
}
