package textCompression;

import java.io.IOException;

import moveToFront.CharacterCoding;
import moveToFront.MoveToFront;

public class MoveToFrontTransformTest {

	public static void main(String[] args) throws IOException {

		CharacterCoding cc = new CharacterCoding();
		int[] textRaw = cc.readFromFile("Resources/faust.txt");
		System.out.format("RawTextLength: %d\n", textRaw.length);

		int[] textTransformed = MoveToFront.transform(textRaw);
		System.out.format("TransformedTextLength: %d\n", textTransformed.length);

		int[] textBacktransformed = MoveToFront.reverseTransform(textTransformed);
		System.out.format("BackTransformedTextLength: %d\n", textBacktransformed.length);

		// Test for Difference

		boolean isCorrect = true;

		if (textRaw.length != textBacktransformed.length) {
			System.out.println("Text Not the same size! Bad!");
		} else {
			for (int i = 0; i < textRaw.length; i++) {
				if (textRaw[i] != textBacktransformed[i]) {
					isCorrect = false;
					System.out.println("Text different at index " + i + "!");
				}
			}
		}
		System.out.println("Reconstruction is " + (isCorrect ? "successful" : "incorrect"));

		System.out.println("Size of original Text: " + sumOverText(textRaw));
		System.out.println("Size of compressed Text: " + sumOverText(textBacktransformed));

	}

	private static long sumOverText(int[] text) {

		long textSum = 0;
		for (int i : text) {
			textSum += i;
		}

		return textSum;

	}
}
