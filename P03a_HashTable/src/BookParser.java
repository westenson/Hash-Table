import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class that parses the files containing all the books This class is used by
 * the BookHashTableTest.java file to create an arraylist of all the book in
 * order to test all the methods
 * 
 */
public class BookParser {

	// @param booksfilename - a csv file with book database information
	// Parse the csv file into a list of book object
	public static ArrayList<Book> parse(String booksfilename)
			throws FileNotFoundException {

		//arraylist of all the book
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {

			//scanner to read from file
			Scanner scanner = new Scanner(new File(booksfilename));

			Scanner valueScanner = null;
			int idx = 0;

			// try different methods of the Scanner STDIN
			while (scanner.hasNext()) {

				valueScanner = new Scanner(scanner.nextLine());
				valueScanner.useDelimiter(",");
				ArrayList<String> book = new ArrayList<String>();
				while (valueScanner.hasNext()) {

					idx++;
					// System.out.println(idx);
					String data = valueScanner.next();
					book.add(data);
				}

				Book bookobj = new Book(book.get(0), book.get(1), book.get(2),
						book.get(3), book.get(4), book.get(5), book.get(6),
						book.get(7));

				// System.out.println(bookobj.toString());
				bookList.add(bookobj);
			}
			
			bookList.remove(0);
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("BookParser: file not found");
		}
		return bookList;
	}
}
