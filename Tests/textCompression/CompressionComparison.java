package textCompression;

import java.io.IOException;

import burrowsWheeler.BurrowsWheeler;
import huffman.Huffman;
import moveToFront.CharacterCoding;
import moveToFront.MoveToFront;

public class CompressionComparison {

	static CharacterCoding cc = new CharacterCoding();

	public static void main(String[] args) throws IOException {

		CharacterCoding cc = new CharacterCoding();

		int[] textRaw = cc.readFromFile("Resources/faust.txt");

		System.out.format("Raw Text size: %d Bytes\n", textRaw.length);

		huffmanCompression(textRaw);
		allCompressions(textRaw);

	}

	static void huffmanCompression(int[] textRaw) {

		boolean[] compressed = Huffman.compress(textRaw);

		System.out.format("Just Huffman Compression: %d Bytes\n", (int) Math.ceil(compressed.length / 8));

	}

	static void allCompressions(int[] textRaw) {

		boolean[] compressed = Huffman.compress(MoveToFront.transform(BurrowsWheeler.transform(textRaw)));

		System.out.format("All Compressions chained: %d Bytes\n", (int) Math.ceil(compressed.length / 8));

	}

}
