package moveToFront;
import java.io.IOException;

import textCodierung.TextCoding;
import textCodierung.CharacterCodingLib.CharacterCoding;

public class MoveToFrontTransformTest {

	static String basePath = "Tests/MoveToFrontTransform.";
	static String pathInput = basePath + "/input/faust.txt";
	static String pathTransformed = basePath + "/output/faust_transformed.txt";
	static String pathReconstructed = basePath + "/output/faust_reconstructed.txt";

	public static void main(String[] args) throws IOException {

		TextCoding.applyTransform(pathInput, pathTransformed);
		TextCoding.applyReverseTransform(pathTransformed, pathReconstructed);

		// Test for Difference

		CharacterCoding cc = new CharacterCoding();
		int origText[] = cc.readFromFile(pathInput);
		int reconText[] = cc.readFromFile(pathReconstructed);
		boolean isCorrect = true;

		if (origText.length != reconText.length) {
			System.out.println("Text Not the same size! Bad!");
		} else {
			for (int i = 0; i < origText.length; i++) {
				if (origText[i] != reconText[i]) {
					isCorrect = false;
					System.out.println("Text different at index " + i + "!");
				}
			}
		}
		System.out.println("Reconstruction is " + (isCorrect ? "successful" : "incorrect"));

		System.out.println("Size of original Text: " + sumOverText(pathInput));
		System.out.println("Size of compressed Text: " + sumOverText(pathTransformed));

	}

	private static long sumOverText(String inputPath) throws IOException {
		CharacterCoding cc = new CharacterCoding();
		int text[] = cc.readFromFile(inputPath);
		long textSum = 0;
		for (int i : text) {
			textSum += i;
		}

		return textSum;

	}
}
