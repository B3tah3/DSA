package huffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {

	/**
	 * turns a text file into a compressed file ("0" and "1")
	 * 
	 * @param filepath
	 * @param coding
	 * @throws IOException
	 */
	public static boolean[] compress(int[] textInput) {

		PriorityQueue<HuffmanTree> codingHeap = buildHeapFromValues();
		HuffmanTree codingTree = buildTreeFromHeap(codingHeap);
		HashMap<Integer, ArrayList<Boolean>> codingMap = treeToHashMap(codingTree);

		ArrayList<Boolean> results = new ArrayList<Boolean>();

		for (int i = 0; i < textInput.length; i++) {

			results.addAll(codingMap.get(textInput[i]));

		}

		boolean[] codedText = new boolean[results.size()];

		for (int i = 0; i < results.size(); i++) {

			codedText[i] = results.get(i);

		}

		return codedText;

	}

	/**
	 * turns a compressed file back into a text file
	 * 
	 * @param filepath
	 * @param coding
	 */
	/// TODO input boolean Array output to int Arrray
	public static int[] decompress(boolean[] input) {

		PriorityQueue<HuffmanTree> codingHeap = buildHeapFromValues();
		HuffmanTree codingTree = buildTreeFromHeap(codingHeap);

		HuffmanParser parser = new HuffmanParser(codingTree, input);
		int[] output = parser.parse();

		return output;

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
	 * @return finished huffman tree
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
	 * @return input file ass bool Array
	 * @requires input file to only consist out off "0" and "1"
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

	/**
	 * writesb boollean array list to file encoding true as "1" and false as "0"
	 */
	public static void writeStringAsPseudoBinary(String filepath, boolean[] text) {

		try {
			FileWriter f = new FileWriter(filepath);
			for (int i = 0; i < text.length; i++) {
				f.write(text[i] ? "1" : "0");
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