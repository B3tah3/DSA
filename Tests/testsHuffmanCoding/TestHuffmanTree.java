package testsHuffmanCoding;

import huffman.HuffmanTree;

public class TestHuffmanTree {

	public static void main(String[] args) {

		HuffmanTree treeA = new HuffmanTree('a', 5l);
		HuffmanTree treeB = new HuffmanTree('b', 5l);
		HuffmanTree treeC = new HuffmanTree('c', 20l);
		HuffmanTree treeD = new HuffmanTree('d', 10l);

		HuffmanTree concatAB = HuffmanTree.concatTreeOrder(treeA, treeB);
		HuffmanTree.printInOrder(concatAB.getRoot());
		System.out.println();

		HuffmanTree concatCD = HuffmanTree.concatTreeOrder(treeC, treeD);
		HuffmanTree.printInOrder(concatCD.getRoot());
		System.out.println();

		HuffmanTree concatABCD = HuffmanTree.concatTreeOrder(concatAB, concatCD);
		HuffmanTree.printInOrder(concatABCD.getRoot());
		System.out.println();

	}

}
