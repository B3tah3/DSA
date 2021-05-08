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
		System.out.println("TESTING APPEND");
		LinkedList l = LinkedList.empty();
		l = testAppend(l, 4);
		printSeperator();

		// Testsuit for copy
		System.out.println("TESTING COPY");
		
		LinkedList f = LinkedList.copy(l);
		System.out.println("ID for f: " + f + ", ID for l: " + l);
		f = LinkedList.append(f, 42);
		
		System.out.print("Changed f: ");
		print(f);
		System.out.print("Copied l: ");
		print(l);
		
		printSeperator();

		// Testsuit for concat
		System.out.println("TESTING CONCAT");
		LinkedList c = new LinkedList(0, null);
		c = setupConcat(c);
		l = testConcat(l, c);
		printSeperator();
		
		//Testsuit for insertAt
		System.out.println("TESTING INSERTAT");
		testInsertAt();
		printSeperator();
		
		//Test for removeIndex
		System.out.println("TESTING REMOVEINDEX");
		testRemoveIndex();
		printSeperator();
		
		//Testsuit for moveFromTo
		System.out.println("TESTING MOVEFROMTO");
		testMoveFromTo();
		printSeperator();
		
	}
	
	@Test
	private static void testMoveFromTo() {
		LinkedList m;
		m = initLinkedList();
		
		print(m);
		System.out.println();
		
		for(int n = 0; n<6; n++) {
			try {
			
			m = initLinkedList();
			System.out.print(String.format("Moving index %d to front: ", n));
			m = LinkedList.moveFromTo(n, 0, m);
			print(m);
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}
		
		System.out.println();
		m = initLinkedList();
		
		for(int i = 0; i<6; i++) {
			for(int j = 0; j<6; j++) {
				try {
					m = initLinkedList();
					m = LinkedList.moveFromTo(i, j, m);
					System.out.format("Moving Index %d to Index %d: ", i, j);
					print(m);
				} catch(IndexOutOfBoundsException e) {
					System.out.format("Moving Index %d to Index %d: ", i, j);
					System.out.println(e);
				}
			}
		}
	}
	
	@Test
	private static void testRemoveIndex() {
		LinkedList m;
		m = initLinkedList();
		
		for(int n = 0; n<6; n++) {
			try {
			
			m = initLinkedList();
			System.out.print(String.format("Removing index %d: ", n));
			m = LinkedList.removeIndex(n, m);
			print(m);
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}
		
		m = new LinkedList(1, null);
		m = LinkedList.removeIndex(0, m);
		System.out.print("Removing element from list length 1: ");
		print(m);
	}
	
	@Test
	private static void testInsertAt() {
		LinkedList m = initLinkedList();
		LinkedList insert = new LinkedList(100, null);
		
		System.out.print("Original List: ");
		print(m);
		
		for(int i = 0; i < 7; i++) {
			try {
				m = initLinkedList();
				m = LinkedList.insertAt(i, LinkedList.copy(insert), m);
				
				System.out.format("Inserting at index %d: ", i);
				print(m);
			} catch(IndexOutOfBoundsException e) {
				System.out.format("Inserting at index %d: ", i);
				System.out.println(e);
			}
		}
	}
	
	@Before
	private static LinkedList initLinkedList() {
		LinkedList m = LinkedList.empty();
		
		for(int k = 0; k < 5; k++) {
			m = LinkedList.append(m, k);
		}
		return m;
	}
	
	/**
	 * Prints a seperator
	 */
	private static void printSeperator() {
		for(int i = 0; i<6; i++) {
			System.out.print("-");
		}
		
		for(int j = 0; j < 2; j++) {
			System.out.println();
		}
		
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
