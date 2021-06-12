package huffman;

import java.util.LinkedList;

public class HuffmanParser {
	HuffmanTreeNode root;
	HuffmanTreeNode pointer;
	LinkedList<Integer> output = new LinkedList<Integer>();
	boolean[] input;

	public HuffmanParser(HuffmanTree tree, boolean[] input) {
		this.root = tree.getRoot();
		this.pointer = this.root;
		this.input = input;
	}

	/**
	 * walks along the tree used to build a given parser object, using input as
	 * pathguide , and and oufpts a Array with the values of all leafs passed along
	 * the way if a leaf is reached and parsing is resumed from the beginning of the
	 * tree
	 *
	 * @requires treeparser to be initialized with a valid Tree, and the input array
	 *           to be a valid representatio of a text genereted using a identical
	 *           Tree
	 * @ensures an int array consisting of valid characterCodes
	 * 
	 * @return intArray with the character values of all leafs passed while parsing
	 */
	public int[] parse() {
		for (int i = 0; i < input.length; i++) {

			pointer = pointer.getNext(input[i]);
			if (!pointer.hasNext()) {
				assert (pointer.getCharacter() != -1);
				output.add(pointer.getCharacter());
				pointer = root;
			}

		}

		int[] intArray = toIntArray(output.toArray());
		return intArray;
	}

	/**
	 * turns a given linked list storing INtegers into an int[] Array
	 * 
	 * @param objectArray
	 * @return intArray with values of the given LinkedList
	 */
	private int[] toIntArray(Object[] objectArray) {
		int length = objectArray.length;

		int[] intArray = new int[length];
		for (int i = 0; i < length; i++) {
			intArray[i] = (int) objectArray[i];
		}
		return intArray;
	}

	/**
	 * resets the position of the pointer node to the root of the tree
	 */
	public void reset() {
		this.pointer = this.root;
	}
}
