/**
 * Filename:   TestHashTableDeb.java
 * Project:    p3
 * Authors:    Debra Deppeler (deppeler@cs.wisc.edu)
 * 			   Wally Estenson (westenson@wisc.edu)
 * 
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   before 10pm on 10/29
 * Version:    2.0
 * 
 * Credits:    None so far
 * 
 * Bugs:       None known. 
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test HashTable class implementation to ensure that required functionality
 * works for all cases.
 */
public class BookHashTableTest {

	// Default name of books data file or try books_clean.csv
	public static final String BOOKS = "books_clean.csv";

	// Empty hash tables that can be used by tests
	static BookHashTable bookObject;
	static ArrayList<Book> bookTable;

	static final int INIT_CAPACITY = 2;
	static final double LOAD_FACTOR_THRESHOLD = 0.49;

	static Random RNG = new Random(0); // seeded to make results repeatable
										// (deterministic)

	/** Create a large array of keys and matching values for use in any test */
	@BeforeAll
	public static void beforeClass() throws Exception {
		bookTable = BookParser.parse(BOOKS);
	}

	/** Initialize empty hash table to be used in each test */
	@BeforeEach
	public void setUp() throws Exception {

		bookObject = new BookHashTable(INIT_CAPACITY, LOAD_FACTOR_THRESHOLD);
	}

	/** Not much to do, just make sure that variables are reset */
	@AfterEach
	public void tearDown() throws Exception {
		bookObject = null;
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon
	 * initialization
	 */
	@Test
	public void test000_collision_scheme() {
		if (bookObject == null)
			fail("Gg");
		int scheme = bookObject.getCollisionResolutionScheme();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon
	 * initialization
	 */
	@Test
	public void test001_IsEmpty() {
		// "size with 0 entries:"
		assertEquals(0, bookObject.numKeys());
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is not empty after
	 * adding one (key,book) pair
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test002_IsNotEmpty()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		String expected = "" + 1;
		// "size with one entry:"

		assertEquals(expected, "" + bookObject.numKeys());
	}

	/**
	 * Checks number of keys after inserting several
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test003_IsNotEmpty()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));
		bookObject.insert(bookTable.get(50).getKey(), bookTable.get(50));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
		bookObject.insert(bookTable.get(45).getKey(), bookTable.get(45));
		String expected = "" + 5;
		// "size with one entry:"
		assertEquals(expected, "" + bookObject.numKeys());
	}

	/**
	 * Checks that the get methods return value after 1 insert
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test004_get()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));

		String expected = "" + bookTable.get(0);

		// get value that was inserted
		try {
			assertEquals(expected,
					"" + bookObject.get(bookTable.get(0).getKey()));
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks that the get methods return value after several inserts
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test005_get()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));
		bookObject.insert(bookTable.get(50).getKey(), bookTable.get(50));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
		bookObject.insert(bookTable.get(45).getKey(), bookTable.get(45));

		String expected = "" + bookTable.get(50);

		// get value
		try {
			assertEquals(expected,
					"" + bookObject.get(bookTable.get(50).getKey()));
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Test if the hash table will be resized
	 * after adding two (key,book) pairs given the load factor is 0.49 and
	 * initial capacity to be 2.
	 */
	@Test
	public void test006_Resize()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		int cap1 = bookObject.getCapacity();
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		int cap2 = bookObject.getCapacity();

		// "size with one entry:"
		assertTrue(cap2 > cap1 & cap1 == 4);
	}

	/**
	 * Checks resizing after several inserts
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test007_Resize()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		int cap1 = bookObject.getCapacity();
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
		int cap2 = bookObject.getCapacity();

		// checks that expansion worked correctly
		assertTrue(cap2 > cap1 & cap2 == 16);
	}

	/**
	 * Checks that the remove after 1 insert works correclty
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test008_remove()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.remove(bookTable.get(0).getKey());

		// try to get value that was just removed
		try {
			bookObject.get(bookTable.get(0).getKey());
		} catch (KeyNotFoundException e) {
			return;
		}

		fail("should have thrown keynotfoundexception after insert and remove");
	}

	/**
	 * Checks that the remove after multiple inserts works correctly
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test009_remove()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
		bookObject.remove(bookTable.get(2).getKey());

		// try to get value that was removed
		try {
			bookObject.get(bookTable.get(2).getKey());
		} catch (KeyNotFoundException e) {
			return;
		}

		fail("should have thrown keynotfoundexception after insert and remove");
	}

	/**
	 * Checks that remove returns true when removing
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test010_remove()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));

		if (bookObject.remove(bookTable.get(0).getKey()) != true) {
			fail("should return true after removal ");
		}

	}

	/**
	 * Checks that remove returns false when trying to remove a non existent key
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test011_remove()
			throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));

		if (bookObject.remove(bookTable.get(1).getKey()) != false) {
			fail("should return false after trying to remove key that wasnt there ");
		}

	}

	/**
	 * Try to insert with null key. Should throw null key exception.
	 * 
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test012_insertNull()
			throws IllegalNullKeyException, DuplicateKeyException {
		// try to insert with null key
		try {
			bookObject.insert(null, bookTable.get(0));

		} catch (IllegalNullKeyException e) {
			return;
		}
		fail("should have thrown null key exception after trying to insert null key");
	}

	/**
	 * Try to insert duplicate. Should throw duplicate key exception.
	 * 
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test013_insertDuplicate()
			throws IllegalNullKeyException, DuplicateKeyException {
		// try to insert duplicate
		try {
			bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

		} catch (DuplicateKeyException e) {
			return;
		}
		fail("should have thrown duplicate key exception after trying to insert duplicate key");
	}

	/**
	 * Try to remove with null key. Should throw null key exception.
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test014_removeNull()
			throws IllegalNullKeyException, DuplicateKeyException {
		// try to remove with null key
		try {
			bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
			bookObject.remove(null);

		} catch (IllegalNullKeyException e) {
			return;
		}
		fail("should have thrown null key exception after trying to remove with null key");
	}

	/**
	 * Try to get with null key Should throw null key exception.
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	@Test
	public void test015_getNull() throws IllegalNullKeyException,
			DuplicateKeyException, KeyNotFoundException {
		// try to insert with null key
		try {
			bookObject.get(null);

		} catch (IllegalNullKeyException e) {
			return;
		}
		fail("should have thrown null key exception after trying to get with null key");
	}

	/**
	 * Try to get key that doesnt exist. Should throw key not found exception.
	 *
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	@Test
	public void test016_getNonExistent() throws IllegalNullKeyException,
			DuplicateKeyException, KeyNotFoundException {
		// try to get key that doesnt exists

		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

		try {
			bookObject.get("5");

		} catch (KeyNotFoundException e) {
			return;
		}
		fail("should have thrown key not found exception after getting non existent key");
	}
	
	/**
	 * Should return correct number of keys 
	 *
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	@Test
	public void test017_numKeys() throws IllegalNullKeyException,
			DuplicateKeyException, KeyNotFoundException {

		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

		if (bookObject.numKeys() != 5 ){
		fail("number of keys should have been 5");
		}
	}
	
	/**
	 * Should return correct number of keys after removals 
	 *
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	@Test
	public void test018_numKeys() throws IllegalNullKeyException,
			DuplicateKeyException, KeyNotFoundException {

		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
		bookObject.insert(bookTable.get(3).getKey(), bookTable.get(1));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
		bookObject.remove(bookTable.get(1).getKey());
		bookObject.remove(bookTable.get(4).getKey());
		bookObject.insert(bookTable.get(5).getKey(), bookTable.get(4));
		bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
		bookObject.insert(bookTable.get(7).getKey(), bookTable.get(4));
		bookObject.insert(bookTable.get(8).getKey(), bookTable.get(4));
		

		if (bookObject.numKeys() != 7 ){
		fail("number of keys should have been 7 after inserts and removals ");
		}
	}
}
