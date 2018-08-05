package bookRank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public String ISBN;
	public int year;
	public int month;
	public String color;
	public String title;
	
	public Book(int id, String row, int year, int month, String row2, String row3) {
		this.id = id;
		this.ISBN = row;
		this.year = year;
		this.month = month;
		this.color = row2;
		this.title = row3;
	}
	
	public String toString() {
		return "ID: " + this.id + " ISBN: "
				+ this.id + " YEAR: " + this.id + " MONTH: " + this.id
				+ " COLOR: " + this.id + " TITLE: " + this.title;
	}
}
