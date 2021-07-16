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

		int burrowIndex = 34234;
		int[] burrowTransformed = BurrowsWheeler.transform(textRaw);
		
		cc.writeToFile("Resources/BurrowsWheelerTransformed.txt", burrowTransformed);
		
		
		int[] moveToFrontTransformed = MoveToFront.applyTransform(textRaw);

		
		boolean[] huffmanTransformed = Huffman.compress(moveToFrontTransformed);
		
		Huffman.writeStringAsPseudoBinary("Resources/Binary", huffmanTransformed);
		

		int[] huffmanBacktransformed = Huffman.decompress(huffmanTransformed);

		int[] moveToFrontBacktransformed = MoveToFront.applyReverseTransform(huffmanBacktransformed);

		 int[] burrowBacktransformed = BurrowsWheeler.backTransform(burrowTransformed, burrowIndex);

		cc.writeToFile("Resources/BurrowsMoveHuffmanBacktransformed.txt", burrowBacktransformed);

	}

}
