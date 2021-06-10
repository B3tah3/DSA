package huffman;

import java.util.HashMap;

import tree.TreeIsEmptyException;

public class HuffmanTree implements Comparable<HuffmanTree> {

	private HuffmanTreeNode root;

	public HuffmanTree() {
		this.root = null;
	}

	private HuffmanTree(HuffmanTreeNode root) {
		this.root = root;
	}

	public HuffmanTree(char character, long value) {
		this.root = new HuffmanTreeNode(character, value);
	}

	/**
	 * Concats two Huffman trees, ordered by value. The lower one becomes the left
	 * child, the greather the right child. The new roots value is the value of both
	 * childs summed up.
	 * 
	 * @param tree1
	 * @param tree2
	 * @return A new Tree that contains both trees.
	 * @throws IllegalArgumentException if one of the trees is null and/ or empty
	 */
	public static HuffmanTree concatTreeOrder(HuffmanTree tree1, HuffmanTree tree2) throws IllegalArgumentException {

		if (tree1 == null || tree2 == null || tree1.isEmpty() || tree2.isEmpty()) {
			throw new IllegalArgumentException("Trees may not be empty!");
		}

		HuffmanTreeNode tree1Node = tree1.getRoot();
		HuffmanTreeNode tree2Node = tree2.getRoot();

		long valueSum = tree1.getRoot().getValue() + tree2.getRoot().getValue();

		HuffmanTree returnTree = new HuffmanTree('-', valueSum);

		if (tree1Node.getValue() <= tree2Node.getValue()) {

			returnTree.getRoot().setLeft(tree1Node);
			returnTree.getRoot().setRight(tree2Node);

		} else {

			returnTree.getRoot().setLeft(tree2Node);
			returnTree.getRoot().setRight(tree1Node);
		}

		return returnTree;

	}

	/**
	 * Checks if Tree is empty
	 * 
	 * @return True if tree is empty, false otherwise
	 */
	public boolean isEmpty() {

		return root == null;

	}

	/**
	 * Returns the left subtree of the root
	 * 
	 * @return Tree object representing the left sub-tree
	 */
	public HuffmanTree left() {

		return new HuffmanTree(root.getLeft());

	}

	/**
	 * Returns the right subtree of the root
	 * 
	 * @return Tree object representing the left sub-tree
	 */
	public HuffmanTree right() {

		return new HuffmanTree(root.getRight());

	}

	/**
	 * Returns key of tree node
	 * 
	 * @throws TreeIsEmptyException
	 */
	public long key() throws TreeIsEmptyException {
		if (isEmpty()) {
			throw new TreeIsEmptyException();
		}

		return root.getValue();

	}

	/**
	 * Task b) Prints all the leaves of this tree, and the path to get to them in
	 * binary representation
	 */
	public void printLeavesAndPath() {
		if (this.isEmpty()) {
			System.out.println("empty");
		} else {
			printLeavesAndPath(this.root, "");
		}
	}

	/**
	 * Task b): Private helper for init.
	 * 
	 * @param node
	 * @param path
	 */
	private void printLeavesAndPath(HuffmanTreeNode node, String path) {
		if (node.hasLeft()) {
			this.printLeavesAndPath(node.getLeft(), path + "0");
		}

		if (!node.hasLeft() && !node.hasRight()) {
			System.out.println(node.getValue() + ", path: " + path);
		}

		if (node.hasRight()) {
			this.printLeavesAndPath(node.getRight(), path + "1");
		}

	}

	public HashMap<Integer, Long> leafsAsHashMap() {

		HashMap<Integer, Long> leafMap = new HashMap<>();
		leafsAsHashMap(root, leafMap);

		return leafMap;

	}

	private void leafsAsHashMap(HuffmanTreeNode node, HashMap<Integer, Long> leafMap) {

		if (node.hasLeft()) {
			leafsAsHashMap(node.getLeft(), leafMap);
		}

		if (!node.hasLeft() && !node.hasRight()) {
			leafMap.put(node.getCharacter(), node.getValue());
		}

		if (node.hasRight()) {
			leafsAsHashMap(node.getRight(), leafMap);
		}

	}

	/**
	 * Task c): Displays a tree in the format of [u]v if u is the treeStructure of l
	 * and v the tree structure of r, where the tree is (l, x, r)
	 * 
	 * @return String representation of tree structure
	 */
	public String treeStructure() {

		return treeStructure(root);
	}

	/**
	 * Task c): Private helper for init.
	 * 
	 * @param node
	 * @return
	 */
	private String treeStructure(HuffmanTreeNode node) {
		String structure = "";

		structure += "[";

		if (node.hasLeft()) {
			structure += treeStructure(node.getLeft());
		}

		structure += "]";

		if (node.hasRight()) {
			structure += treeStructure(node.getRight());
		}

		return structure;
	}

	public static void printInOrder(HuffmanTreeNode node) {
		if (node.hasLeft()) {
			printInOrder(node.getLeft());
		}

		System.out.print(node.getValue() + "; ");

		if (node.hasRight()) {
			printInOrder(node.getRight());
		}
	}

	public HuffmanTreeNode getRoot() {

		return this.root;
	}

	public int compareTo(HuffmanTree t) {

		return (int) (root.getValue() - t.root.getValue());
	}

}
