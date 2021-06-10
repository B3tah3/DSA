package huffman;

import java.util.HashMap;
import java.util.PriorityQueue;

import textCodierung.CharacterCodingLib.CharacterCoding;

public class Huffman {

	public static void main(String[] args) {

		PriorityQueue heap = new PriorityQueue<HuffmanTree>();
	}

	/**
	 * 
	 * @param filepath
	 * @param coding
	 */
	public static void encode(String filepath, String coding) {
		// open file and read to string/ stream
		// build
		// encode
		// build coding tree
		HuffmanTree codingTree = buildTreeFromCode(coding);
		// build hashmap int -> bits
		// proccess text bits
		// save to file

	}

	/**
	 * 
	 * @param filepath
	 * @param coding
	 */
	public static void decode(String filepath, String coding) {
		// open file and read to string/ stream
		// decode
		// build coding tree
		HuffmanTree codingTree = buildTreeFromCode(coding);
		// process stream to text
		// save to file

	}

	private PriorityQueue<HuffmanTree> buildHeapFromCode(CharacterCoding coding) {

		PriorityQueue<HuffmanTree> heap = new PriorityQueue<HuffmanTree>();
		for (Integer t : LetterCounter.readValuesFromFile(coding)) {
			heap.add(new HuffmanTree(t.c, t.value));
		}
		return heap;

	}

	public static HashMap<Integer, Long> treeHeapToHashMap(PriorityQueue<HuffmanTree> heap)
			throws IllegalArgumentException {

		if (heap.isEmpty() || heap == null) {
			throw new IllegalArgumentException("Heap may not be null or empty");
		}

		HuffmanTree finishedHuffmanTree = heap.poll();

		return finishedHuffmanTree.leafsAsHashMap();

	}

}