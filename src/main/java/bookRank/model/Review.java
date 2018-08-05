package bookRank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public int user_id;
	public int book_id;
	public int rating;
	public String content;
	
	public Review(int id, int user_id, int book_id, int rating, String content) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.rating = rating;
		this.content = content;
	}
}
