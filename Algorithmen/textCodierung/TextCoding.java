package textCodierung;

import java.io.IOException;

import LinkedList.LinkedList;

public class TextCoding {

	static LinkedList alphabet = generateAlphabet(111);

	/*
	 * Main Method that processes the text into it's compressed form and then
	 * reconstructs it
	 */
	public static void main(String[] args) throws IOException {

		CharacterCoding characterCoding = new CharacterCoding();

		String path = "Algorithmen/textCodierung/";

		int[] text = characterCoding.readFromFile(path + "faust.txt");
		int[] textResult = new int[text.length];

		for (int i = 0; i < text.length; i++) {
			int character = text[i];
			int charIndex = LinkedList.getIndexByValue(character, alphabet);
			alphabet = LinkedList.moveFromTo(charIndex, 0, alphabet);
			textResult[i] = charIndex;
		}

		characterCoding.writeToFile(path + "faust_compressed.txt", textResult);

		// Reconstructing
		alphabet = generateAlphabet(111);
		int[] textReconstruct = new int[textResult.length];
		for (int i = 0; i < textResult.length; i++) {

			int index = textResult[i];
			textReconstruct[i] = sortBack(index);
		}

		characterCoding.writeToFile(path + "faust_reconstructed.txt", textReconstruct);

	}

//	/**
//	 * Not the clean
//	 * @param character the charater to be searched and moved to the front
//	 * @return the index where the character was found
//	 */
//	private static int moveToFront(int character) {
//
//		int charIndex = LinkedList.getIndexByValue(character, alphabet);
//
//		alphabet = LinkedList.moveFromTo(charIndex, 0, alphabet);
//
//		return charIndex;
//	}
//
//	private static int sortBack(int index) {
//
//		int character = LinkedList.first(alphabet);
//
//		alphabet = LinkedList.moveFromTo(0, index, alphabet);
//
//		return character;
//	}

	/**
	 * Creates a Linked List filled with numbers from 0 to m-1
	 * 
	 * @param m the length of the desired alphabet
	 * @return the LinkedList
	 */
	private static LinkedList generateAlphabet(int m) {
		LinkedList alphabet = LinkedList.empty();
		for (int i = 0; i < m; i++) {
			alphabet = LinkedList.append(alphabet, i);
		}
		return alphabet;
	}

}
