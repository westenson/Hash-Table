import java.util.Objects;

/**
 * HashTable implementation that uses and Array of LinkedLists
 * 
 * Book object - below you will find many characteristics of a book The
 * BookHashTable.java file creates a hashtable to store book objects
 */
public class Book {
	private String isbn13;
	private String authors;
	private String original_publication_year;
	private String title;
	private String language_code;
	private String average_rating;
	private String cover_type;
	private String pages;

	// book constructor
	public Book(String isbn13, String authors, String original_publication_year,
			String title, String language_code, String average_rating,
			String cover_type, String pages) {
		this.isbn13 = isbn13;
		this.title = title;
		this.authors = authors;
		this.original_publication_year = original_publication_year;
		this.language_code = language_code;
		this.average_rating = average_rating;
		this.cover_type = cover_type;
		this.pages = pages;
	}

	// return the isbn13 key of a book
	public String getKey() {
		return this.isbn13;
	}

	// sets the isbn13 key of a book
	public void setKey(String isbn13) {
		this.isbn13 = isbn13;
	}

	// takes a book object and returns a string value with many of the important
	// characteristics of the book
	@Override
	public String toString() {
		return "ISBN13: " + this.isbn13 + "; Book: " + this.title + ", Author: "
				+ this.authors + ", Original Publication Year: "
				+ this.original_publication_year + ", Language: "
				+ this.language_code + ", Average Rating: "
				+ this.average_rating + ", Cover Type: " + this.cover_type
				+ ", Pages: " + this.pages;
	}
}
