package textCompression;

import java.io.IOException;

import huffman.Huffman;
import moveToFront.CharacterCoding;
import moveToFront.MoveToFront;

public class BurrowsWheelerTest {

	public static void main(String[] args) throws IOException {

		CharacterCoding cc = new CharacterCoding();

		int[] textRaw = cc.readFromFile("Resources/faust.txt");

		// int[] burrowTransformed = BurrowsWheeler.transform(textRaw);

		int[] moveToFrontTransformed = MoveToFront.applyTransform(textRaw);

		cc.writeToFile("MoveToFrontTransformed", moveToFrontTransformed);

		boolean[] huffmanTransformed = Huffman.compress(moveToFrontTransformed);

		int[] huffmanBacktransformed = Huffman.decompress(huffmanTransformed);

		int[] moveToFrontBacktransformed = MoveToFront.applyReverseTransform(huffmanBacktransformed);

		// int[] burrowBacktransformed =
		// BurrowsWheeler.reverseTransform(moveToFrontBacktransformed);

		cc.writeToFile("Resources/BurrowsMoveHuffmanBacktransformed.txt", moveToFrontBacktransformed);

	}

}
