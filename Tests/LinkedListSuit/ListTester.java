package de.dsa.Tests.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.dsa.datastructures;

public class ListTester {

	/**
	 * Main Method for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Testsuit for append
		List l = List.empty();
		l = testAppend(l, 4);

		// Testsuit for copy
		List f = List.copy(l);
		System.out.println("ID for f: " + f + ", ID for l: " + l);
		print(f);
		f = List.append(f, 42);
		print(f);
		print(l);

		// Testsuit for concat
		List c = new List(0, null);
		c = setupConcat(c);
		l = testConcat(l, c);
	}

	/**
	 * Prints a given list in a nice formatted way
	 * 
	 * @param a the list to be printed
	 */
	public static void print(List a) {

		System.out.print("(");
		if (!List.isEmpty(a)) {
			System.out.print(List.first(a));
			a = List.rest(a);
		}
		while (!List.isEmpty(a)) {

			System.out.print(", " + List.first(a));
			a = List.rest(a);
		}
		System.out.println(")");
	}


	/**
	 * Test function that appends *times* numbers to the startList
	 * @param startList
	 * @param times
	 * @return The mutated startList
	 */
	@Test
	public static List testAppend(List startList, int times) {
		for (int i = 1; i < times + 1; i++) {
			startList = List.append(startList, i);

			System.out.format("Testing Append with: %d: ", i);
			print(startList);
		}

		return startList;
	}
	
	/**
	 * Sets up a new list, that can be used as a list for concat
	 * @param c empty list to be generated for concat
	 * @return filled list
	 */
	@BeforeEach
	public static List setupConcat(List c) {
		for (int i = 1; i < 3; i++) {
			c = new List(i, c);
		}

		System.out.print("Done with setting up c: ");
		print(c);

		return c;
	}
	
	/**
	 * Testfunction for concat
	 * @param l
	 * @param c
	 * @return list l that was concatted
	 */
	@Test
	public static List testConcat(List l, List c) {
		l = List.concat(l, c);

		System.out.print("Test concat: ");
		print(l);

		return l;
	}

}
