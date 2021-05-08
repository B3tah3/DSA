package code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@Test
	public static List testAppend(List startList, int times) {
		for (int i = 1; i < times + 1; i++) {
			startList = List.append(startList, i);

			System.out.format("Testing Append with: %d: ", i);
			print(startList);
		}

		return startList;
	}

	@BeforeEach
	public static List setupConcat(List c) {
		for (int i = 1; i < 3; i++) {
			c = new List(i, c);
		}

		System.out.print("Done with setting up c: ");
		print(c);

		return c;
	}

	@Test
	public static List testConcat(List l, List c) {
		l = List.concat(l, c);

		System.out.print("Test concat: ");
		print(l);

		return l;
	}

}
