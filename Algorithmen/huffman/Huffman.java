package huffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import textCodierung.CharacterCodingLib.CharacterCoding;

public class Huffman {

	private static CharacterCoding coding;

	static {

		coding = new CharacterCoding();

	}

	public static void main(String[] args) {

		String input = "Resources/faust.txt";
		String output = "Resources/faustCompressed.txt";
		String decompressedOutput = "Resources/faustDecompressed.txt";

		ArrayList<Boolean> comp = compress(input, output);
		System.out.println("Length of compressed binaries: " + comp.size());

		writeStringAsPseudoBinary(output, comp);

		int[] decomp = decompress(comp, decompressedOutput);
		System.out.println("Length of reconstructed text: " + decomp.length);

		try {
			coding.writeToFile(decompressedOutput, decomp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * turns a text file into a compressed file ("0" and "1")
	 * 
	 * @param filepath
	 * @param coding
	 * @throws IOException
	 */
	public static ArrayList<Boolean> compress(String input, String output) {

		ArrayList<Boolean> codedText = new ArrayList<>();
		try {
			int[] text = coding.readFromFile(input);
			codedText = new ArrayList<Boolean>();

			PriorityQueue<HuffmanTree> codingHeap = buildHeapFromValues();
			HuffmanTree codingTree = buildTreeFromHeap(codingHeap);
			HashMap<Integer, ArrayList<Boolean>> codingMap = treeToHashMap(codingTree);

			for (int i = 0; i < text.length; i++) {

				codedText.addAll(codingMap.get(text[i]));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codedText;

	}

	/**
	 * turns a compressed file back into a text file
	 * 
	 * @param filepath
	 * @param coding
	 */
	public static int[] decompress(ArrayList<Boolean> input, String outputPath) {

		PriorityQueue<HuffmanTree> codingHeap = buildHeapFromValues();
		HuffmanTree codingTree = buildTreeFromHeap(codingHeap);

		// boolean[] input = readCompressedFromPseudoBinary(inputPath);

		HuffmanParser parser = new HuffmanParser(codingTree, toBooleanArray(input.toArray()));
		int[] output = parser.parse();

		return output;
//		try {
//			coding.writeToFile(outputPath, output);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * Turns the values from counting the letters into a heap filled with single
	 * trees containing all letters and their values
	 * 
	 * @return
	 */
	private static PriorityQueue<HuffmanTree> buildHeapFromValues() {

		PriorityQueue<HuffmanTree> heap = new PriorityQueue<HuffmanTree>();
		HashMap<Integer, Integer> codeSet = LetterCounter.readCodingFromFile();

		for (Integer t : codeSet.keySet()) {
			heap.add(new HuffmanTree(t, codeSet.get(t)));
		}

		return heap;

	}

	/**
	 * converts the tree into a map to use for compression
	 * 
	 * @param tree
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static HashMap<Integer, ArrayList<Boolean>> treeToHashMap(HuffmanTree tree)
			throws IllegalArgumentException {

		if (tree.isEmpty() || tree == null) {
			throw new IllegalArgumentException("Heap may not be null or empty");
		}

		return tree.leafsAndPathsAsHashMap();

	}

	/**
	 * reduces the trees in a given heap, until a single one remains, which it
	 * returns
	 * 
	 * @param codingHeap
	 * @return
	 */
	private static HuffmanTree buildTreeFromHeap(PriorityQueue<HuffmanTree> codingHeap) {

		while (codingHeap.size() > 1) {

			HuffmanTree fistTree = codingHeap.poll();
			HuffmanTree secondTree = codingHeap.poll();

			HuffmanTree combine = HuffmanTree.concatTreeOrder(fistTree, secondTree);
			codingHeap.add(combine);

		}

		return codingHeap.poll();
	}

	/**
	 * reads a file with string "0" and "1" and returns them in a boolean array
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean[] readCompressedFromPseudoBinary(String filepath) {
		ArrayList<Boolean> boolList = new ArrayList<Boolean>();

		try (FileReader f = new FileReader(filepath)) {
			boolean end = false;
			while (!end) {
				int c = 0;
				try {
					c = f.read();
				} catch (IOException e) {
					end = true;
				}
				if (c != -1) {
					boolList.add(c == '1');
				} else {
					end = true;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return toBooleanArray(boolList.toArray());
	}

	public static void writeStringAsPseudoBinary(String filepath, ArrayList<Boolean> text) {

		try {
			FileWriter f = new FileWriter(filepath);
			for (int i = 0; i < text.size(); i++) {
				f.write(text.get(i) ? "1" : "0");
				if (i % 100 == 99) {
					f.write("\n");
				}
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * converts an object array to a boolean array
	 * 
	 * @param objectArray
	 * @return
	 */
	private static boolean[] toBooleanArray(Object[] objectArray) {
		int length = objectArray.length;

		boolean[] booleanArray = new boolean[length];
		for (int i = 0; i < length; i++) {
			booleanArray[i] = (boolean) objectArray[i];
		}
		return booleanArray;
	}

}