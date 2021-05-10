package textCodierung;

import java.io.IOException;

import LinkedList.LinkedList;

public class TextCoding {

	static LinkedList alphabet = generateAlphabet(111);

	public static void main(String[] args) throws IOException {

		CharacterCoding characterCoding = new CharacterCoding();

		String path = "Algorithmen/textCodierung/";

		int[] text = characterCoding.readFromFile(path + "faust.txt");
		int[] textResult = new int[text.length];

		for (int i = 0; i < text.length; i++) {
			int character = text[i];
			textResult[i] = moveToFront(character);
		}

		characterCoding.writeToFile(path + "faust_compressed.txt", textResult);

	}

	private static int moveToFront(int character) {

		int charIndex = LinkedList.getIndexByValue(character, alphabet);

		alphabet = LinkedList.moveFromTo(charIndex, 0, alphabet);

		return charIndex;
	}

	private static LinkedList generateAlphabet(int m) {
		LinkedList alphabet = LinkedList.empty();
		for (int i = 0; i < m; i++) {
			alphabet = LinkedList.append(alphabet, i);
		}
		return alphabet;
	}

}
