package de.dsa.datastructures;

/**
 * ∗Einfach verkettete Liste ∗LeereListe<>istnull;nichtlereListe<a0,...,a(n−1)>
 * ∗bestehtauseinemListenkopfmitBeschriftunga0
 * ∗unddem(Verweisaufden)Rest<a1,...,a(n−1)> ∗
 **/

public class LinkedList {

	private Integer value;
	private LinkedList successor;

	/**
	 * @param element New head of list
	 * @param rest    rest of list
	 */
	protected LinkedList(Integer element, LinkedList rest) {
		value = element;
		successor = rest;
	}

	public static LinkedList empty() {
		return null;
	}

	/**
	 * Functions that returns the first element of the list.
	 * 
	 * @param list List to operate on
	 * @return First list value
	 * @throws ListIsEmptyException
	 */
	public static int first(final LinkedList list) {
		return list.value;
	}

	/**
	 * Returns reference to rest of list.
	 * 
	 * @param list List to be operatedo on
	 * @return Reference to rest of list
	 */
	public static LinkedList rest(final LinkedList list) {

		return list.successor;
	}

	/**
	 * Prepends element to given list. We had a append function that appended a
	 * value. I still have the code, if you want that, let me now, I'll send i to
	 * you Since we need a prepend for d) and append was a bit messy we are using
	 * the append as prepend.
	 * 
	 * @param list    List to append to
	 * @param element Element to append
	 */
	public static LinkedList append(LinkedList list, final int element) {
		LinkedList returnList;

		if (isEmpty(list)) {

			returnList = new LinkedList(element, empty());

		} else {

			returnList = new LinkedList(element, list);

		}

		return returnList;
	}

	/**
	 * Merges two lists
	 * 
	 * @param l1 First list
	 * @param l2 Second list
	 */
	public static LinkedList concat(LinkedList list1, LinkedList list2) {
		LinkedList returnList = list1;

		if (isEmpty(list1)) {

			returnList = list2;

		} else {
			LinkedList listPointer = list1;

			while (!isEmpty(rest(listPointer))) {
				listPointer = rest(listPointer);
			}

			listPointer.successor = list2;
		}

		return returnList;

	}

	public static LinkedList copy(LinkedList list) {

		LinkedList copy = empty();
		if (list != null) {
			LinkedList currentElement = list;
			if (!isEmpty(currentElement.successor)) {
				copy(currentElement.successor);
			}
			copy = append(currentElement.successor, currentElement.value);
		}
		return copy;

	}

	/**
	 * Check for empty list
	 * 
	 * @param list List to be checked
	 * @return True if empty, false otherwise
	 */
	public static boolean isEmpty(LinkedList list) {
		return list == null;
	}

}