package LinkedListSuit;

import LinkedList.LinkedList;

public class ListTester {

	/**
	 * Main Method for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Testsuit for append
		LinkedList l = LinkedList.empty();
		l = testAppend(l, 4);

		// Testsuit for copy
		LinkedList f = LinkedList.copy(l);
		System.out.println("ID for f: " + f + ", ID for l: " + l);
		print(f);
		f = LinkedList.append(f, 42);
		print(f);
		print(l);

		// Testsuit for concat
		LinkedList c = new LinkedList(0, null);
		c = setupConcat(c);
		l = testConcat(l, c);
	}

	/**
	 * Prints a given list in a nice formatted way
	 * 
	 * @param a the list to be printed
	 */
	public static void print(LinkedList a) {

		System.out.print("(");
		if (!LinkedList.isEmpty(a)) {
			System.out.print(LinkedList.first(a));
			a = LinkedList.rest(a);
		}
		while (!LinkedList.isEmpty(a)) {

			System.out.print(", " + LinkedList.first(a));
			a = LinkedList.rest(a);
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
	public static LinkedList testAppend(LinkedList startList, int times) {
		for (int i = 1; i < times + 1; i++) {
			startList = LinkedList.append(startList, i);

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
	@Before
	public static LinkedList setupConcat(LinkedList c) {
		for (int i = 1; i < 3; i++) {
			c = new LinkedList(i, c);
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
	public static LinkedList testConcat(LinkedList l, LinkedList c) {
		l = LinkedList.concat(l, c);

		System.out.print("Test concat: ");
		print(l);

		return l;
	}

}
