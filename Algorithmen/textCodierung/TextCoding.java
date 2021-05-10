package textCodierung;

import java.io.IOException;

import LinkedList.LinkedList;
import textCodierung.CharacterCodingLib.CharacterCoding;

public class TextCoding {

	/**
	 * Transformes a text
	 * 
	 * @param loadPath Path to load text from
	 * @param outPath  path to save transformed text to.
	 * @throws IOException
	 */
	public static void applyTransform(String loadPath, String outPath) throws IOException {

		CharacterCoding characterCoding = new CharacterCoding();
		LinkedList alphabet = generateAlphabet(111);

		int[] text = characterCoding.readFromFile(loadPath);
		int[] textResult = new int[text.length];

		for (int i = 0; i < text.length; i++) {
			int character = text[i];
			int charIndex = LinkedList.getIndexByValue(character, alphabet);
			alphabet = LinkedList.moveFromTo(charIndex, 0, alphabet);
			textResult[i] = charIndex;
		}

		characterCoding.writeToFile(outPath, textResult);

	}

	/**
	 * Reconstructs a transformed text, transformed by applyTransform()
	 * 
	 * @param loadPath Path to lead transformed text from
	 * @param outPath  Path to save reconstructed text to
	 * @throws IOException
	 */
	public static void applyReverseTransform(String loadPath, String outPath) throws IOException {

		CharacterCoding characterCoding = new CharacterCoding();
		LinkedList alphabet = generateAlphabet(111);

		int[] text = characterCoding.readFromFile(loadPath);
		int[] textReconstruct = new int[text.length];

		for (int i = 0; i < text.length; i++) {

			int index = text[i];
			int character = LinkedList.getValueByIndex(index, alphabet);

			alphabet = LinkedList.moveFromTo(index, 0, alphabet);

			// int character = LinkedList.first(alphabet);

			textReconstruct[i] = character;
		}

		characterCoding.writeToFile(outPath, textReconstruct);
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
