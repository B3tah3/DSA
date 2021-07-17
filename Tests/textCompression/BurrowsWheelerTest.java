package textCompression;

import java.io.IOException;

import burrowsWheeler.BurrowsWheeler;
import huffman.Huffman;
import moveToFront.CharacterCoding;
import moveToFront.MoveToFront;

public class BurrowsWheelerTest {

	public static void main(String[] args) throws IOException {

		CharacterCoding cc = new CharacterCoding();

		int[] textRaw = cc.readFromFile("Resources/faust.txt");

		int[] burrowTransformed = BurrowsWheeler.transform(textRaw);

		cc.writeToFile("Resources/BurrowsWheelerTransformed.txt", burrowTransformed);

		int[] moveToFrontTransformed = MoveToFront.transform(burrowTransformed);

		boolean[] huffmanTransformed = Huffman.compress(moveToFrontTransformed);

		Huffman.writeStringAsPseudoBinary("Resources/Binary", huffmanTransformed);
		System.out.format("Number of Bytes: %d\n", (int) Math.ceil(huffmanTransformed.length / 8));

		int[] huffmanBacktransformed = Huffman.decompress(huffmanTransformed);

		int[] moveToFrontBacktransformed = MoveToFront.reverseTransform(huffmanBacktransformed);

		int[] burrowBacktransformed = BurrowsWheeler.reverseTransform(moveToFrontBacktransformed);

		boolean huffmanCorrect = true, moveToFrontCorrect = true, burrowWheelerCorrect = true;
		for (int i = 0; i < huffmanBacktransformed.length; i++) {
			if (huffmanBacktransformed[i] != moveToFrontTransformed[i]) {
				huffmanCorrect = false;
			}
		}
		for (int i = 0; i < moveToFrontBacktransformed.length; i++) {
			if (moveToFrontBacktransformed[i] != burrowTransformed[i]) {
				moveToFrontCorrect = false;
			}
		}
		for (int i = 0; i < burrowBacktransformed.length; i++) {
			if (burrowBacktransformed[i] != textRaw[i]) {
				burrowWheelerCorrect = false;
			}
		}

		if (!burrowWheelerCorrect || !huffmanCorrect || !moveToFrontCorrect) {
			System.out.println("Text reconstruction failed!");
		} else {
			System.out.println("Text recontruction successful!");
		}

		cc.writeToFile("Resources/BurrowsMoveHuffmanBacktransformed.txt", burrowBacktransformed);

	}

}
