package code;

/**
 * ∗Einfach verkettete Liste ∗LeereListe<>istnull;nichtlereListe<a0,...,a(n−1)>
 * ∗bestehtauseinemListenkopfmitBeschriftunga0
 * ∗unddem(Verweisaufden)Rest<a1,...,a(n−1)> ∗
 **/

public class List {

	private Integer value;
	private List successor;

	/**
	 * @param element New head of list
	 * @param rest    rest of list
	 */
	protected List(Integer element, List rest) {
		value = element;
		successor = rest;
	}

	public static List empty() {
		return null;
	}

	/**
	 * Functions that returns the first element of the list.
	 * 
	 * @param list List to operate on
	 * @return First list value
	 * @throws ListIsEmptyException
	 */
	public static int first(final List list) {
		return list.value;
	}

	/**
	 * Returns reference to rest of list.
	 * 
	 * @param list List to be operatedo on
	 * @return Reference to rest of list
	 */
	public static List rest(final List list) {

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
	public static List append(List list, final int element) {
		List returnList;

		if (isEmpty(list)) {

			returnList = new List(element, empty());

		} else {

			returnList = new List(element, list);

		}

		return returnList;
	}

	/**
	 * Merges two lists
	 * 
	 * @param l1 First list
	 * @param l2 Second list
	 */
	public static List concat(List list1, List list2) {
		List returnList = list1;

		if (isEmpty(list1)) {

			returnList = list2;

		} else {
			List listPointer = list1;

			while (!isEmpty(rest(listPointer))) {
				listPointer = rest(listPointer);
			}

			listPointer.successor = list2;
		}

		return returnList;

	}

	public static List copy(List list) {

		List copy = empty();
		if (list != null) {
			List currentElement = list;
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
	public static boolean isEmpty(List list) {
		return list == null;
	}

}