package moveToFront;

import java.io.IOException;

import LinkedList.LinkedList;

public class MoveToFront {

	/**
	 * Transformes a text
	 * 
	 * @param loadPath Path to load text from
	 * @param outPath  path to save transformed text to.
	 * @throws IOException
	 */
	public static int[] applyTransform(int[] textInput) throws IOException {

		LinkedList alphabet = generateAlphabet(111);

		int[] textResult = new int[textInput.length];

		for (int i = 0; i < textInput.length; i++) {
			int character = textInput[i];
			int charIndex = LinkedList.getIndexByValue(character, alphabet);
			alphabet = LinkedList.moveFromTo(charIndex, 0, alphabet);
			textResult[i] = charIndex;
		}

		return textResult;

	}

	/**
	 * Reconstructs a transformed text, transformed by applyTransform()
	 * 
	 * @param loadPath Path to lead transformed text from
	 * @param outPath  Path to save reconstructed text to
	 * @throws IOException
	 */
	public static int[] applyReverseTransform(int[] textInput) throws IOException {

		LinkedList alphabet = generateAlphabet(111);

		int[] textReconstruct = new int[textInput.length];

		for (int i = 0; i < textInput.length; i++) {

			int index = textInput[i];
			int character = LinkedList.getValueByIndex(index, alphabet);

			alphabet = LinkedList.moveFromTo(index, 0, alphabet);

			textReconstruct[i] = character;
		}

		return textReconstruct;

	}

	/**
	 * Creates a Linked List filled with numbers from 0 to m-1
	 * 
	 * @param m the length of the desired alphabet
	 * @return the LinkedList
	 */
	private static LinkedList generateAlphabet(int m) {
		LinkedList alphabet = LinkedList.empty();
		for (int i = m - 1; i >= 0; i--) {
			alphabet = LinkedList.append(alphabet, i);
		}
		return alphabet;
	}

}
