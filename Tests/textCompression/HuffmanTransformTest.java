package textCompression;

import java.io.IOException;

import huffman.Huffman;
import moveToFront.CharacterCoding;

public class HuffmanTransformTest {

	public static void main(String[] args) throws IOException {

		String input = "Resources/faust.txt";
		String output = "Resources/HuffmanTransformed.txt";
		String huffmanBacktransformedressedOutput = "Resources/HuffmanBacktransformed.txt";

		CharacterCoding cc = new CharacterCoding();
		int[] rawText = cc.readFromFile(input);

		boolean[] huffmanTransformed = Huffman.compress(rawText);
		System.out.println("Length of huffmanTransformedressed binaries: " + huffmanTransformed.length);

		Huffman.writeStringAsPseudoBinary(output, huffmanTransformed);

		int[] huffmanBacktransformed = Huffman.decompress(huffmanTransformed);
		System.out.println("Length of reconstructed text: " + huffmanBacktransformed.length);

		boolean isCorrect = true;

		if (rawText.length != huffmanBacktransformed.length) {
			System.out.println("Text don't match in Length");
		} else {
			for (int i = 0; i < rawText.length; i++) {
				if (rawText[i] != huffmanBacktransformed[i]) {
					isCorrect = false;
					System.out.format("Text don't match in position %d\n", i);
				}
			}
		}

		System.out.println("Reconstruction is " + (isCorrect ? "successful" : "incorrect"));

		cc.writeToFile(huffmanBacktransformedressedOutput, huffmanBacktransformed);

	}
}
