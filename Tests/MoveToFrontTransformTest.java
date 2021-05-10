import java.io.IOException;

import textCodierung.TextCoding;
import textCodierung.CharacterCodingLib.CharacterCoding;

public class MoveToFrontTransformTest {

	public static void main(String[] args) throws IOException {

		String basePath = "Tests/MoveToFrontTransform.";
		String pathInput = basePath + "/input/faust.txt";
		String pathTransformed = basePath + "/output/faust_transformed.txt";
		String pathReconstructed = basePath + "/output/faust_reconstructed.txt";

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

	}

	// private static long sumOverText(String inputPath) {
	// ;
	// }
}
