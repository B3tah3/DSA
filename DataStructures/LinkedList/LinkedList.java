package LinkedList;

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
	
	/**
	 * Searches list for specified index
	 * @param index 
	 * @param list 
	 * @return list element at specified index
	 * @throws IndexOutOfBoundsException If index exceeds list length
	 */
	private static LinkedList linearSearchIndex(int index, LinkedList list) throws IndexOutOfBoundsException {
		
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		LinkedList returnList = list;
		for(int i = 0; i < index; i++) {
			
			if(!isEmpty(rest(returnList))) {
				returnList = rest(returnList);
			} else {
				throw new IndexOutOfBoundsException();
			}
			
		}
		
		return returnList;
	}
	
	/**
	 * Checks if index is contained in list
	 * @param index
	 * @param list
	 * @return true if in list else false
	 */
	public static boolean hasIndex(int index, LinkedList list) {
		boolean returnValue = true;
		
		try {
			linearSearchIndex(index, list);
		} catch (IndexOutOfBoundsException e) {
			returnValue = false;
		}
		
		return returnValue;
	}
	
	/**
	 * Removes element at index
	 * @param index
	 * @param list
	 * @throws IndexOutOfBoundsException
	 * @return First element of list
	 */
	public static LinkedList removeIndex(int index, LinkedList list) throws IndexOutOfBoundsException{
		
		if(!hasIndex(index, list)) {
			throw new IndexOutOfBoundsException();
		}
		
		boolean isLastIndex = !hasIndex(index + 1, list);
		LinkedList returnList = list;
		LinkedList beforeIndex;
		LinkedList atIndex;
		LinkedList afterIndex;
		
		if(index == 0 && !isLastIndex) {
			atIndex = list;
			afterIndex = linearSearchIndex(1, list);
			
			atIndex.successor = empty();
			returnList = afterIndex;
			
		} else if(index == 0 && isLastIndex) {
			atIndex = list;
		
			atIndex.successor = empty();
			returnList = empty();
			
		} else if(isLastIndex) {
			beforeIndex = linearSearchIndex(index - 1, list);
			atIndex = linearSearchIndex(1, beforeIndex);
			
			beforeIndex.successor = empty();
			
		} else {
			beforeIndex = linearSearchIndex(index - 1, list);
			atIndex = linearSearchIndex(1, beforeIndex);
			afterIndex = linearSearchIndex(1, atIndex);
			
			beforeIndex.successor = afterIndex;
			atIndex.successor = empty();
		}
		
		return returnList;
	}
	
	/**
	 * Inserts Int and index. Can also append, so an index of lastIndex + 1 is allowed.
	 * @param index
	 * @param element
	 * @param list
	 * @return First Element of list
	 * @throws IndexOutOfBoundsException
	 */
	public static LinkedList insertAt(int index, Integer element, LinkedList list) throws IndexOutOfBoundsException {
		LinkedList returnList;
		LinkedList listElement = new LinkedList(element, null);
		
		try {
			returnList = insertAt(index, listElement, list);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
		
		return returnList;
	}
	
	/**
	 * Inserts List element at index. Can also append so an index of lastIndex + 1 is allowed.
	 * @param index
	 * @param element
	 * @param list
	 * @return First elemen of list
	 * @throws IndexOutOfBoundsException
	 */
	public static LinkedList insertAt(int index, LinkedList element, LinkedList list) throws IndexOutOfBoundsException{
		
		boolean isAppend = hasIndex(index - 1, list);	
		if(!hasIndex(index, list) && !isAppend) {
			throw new IndexOutOfBoundsException();
		}
		
		LinkedList returnList = list;
		LinkedList beforeIndex;
		LinkedList atIndex;
		
		if(index == 0) {
			concat(element, list);
			returnList = element;
		} else if (isAppend) {
			concat(list, element);
		} else {
			beforeIndex = linearSearchIndex(index - 1, list);
			atIndex = linearSearchIndex(1, beforeIndex);
			
			beforeIndex.successor = element;
			element.successor = atIndex;
		}
		
		return returnList;
	}
	
	/**
	 * Moves element in list
	 * @param from
	 * @param to
	 * @param list
	 * @return First Element of list
	 * @throws IndexOutOfBoundsExpcetion
	 */
	public static LinkedList moveFromTo(int from, int to, LinkedList list) throws IndexOutOfBoundsException {
		boolean hasFrom = hasIndex(from, list);
		boolean hasTo = hasIndex(to, list) || hasIndex(to - 1, list);
		
		if(!hasFrom || !hasTo) {
			throw new IndexOutOfBoundsException();
		}
		
		LinkedList returnList = list;
		LinkedList moveElement = linearSearchIndex(from, list); 
		
		if(from != to) {
			returnList = removeIndex(from, returnList);
			returnList = insertAt(to, moveElement, returnList); 
		}
		
		return returnList;
	}

}