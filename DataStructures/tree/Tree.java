package tree;

public class Tree<DataType> {

	private TreeNode<DataType> root;

	public Tree() {
		this.root = null;
	}

	private Tree(TreeNode<DataType> root) {
		this.root = root;
	}

	public Tree(DataType key) {
		this.root = new TreeNode<DataType>(null, key, null);
	}

	public Tree(Tree<DataType> left, DataType key, Tree<DataType> right) {
		TreeNode<DataType> leftNode = left.getRoot();
		TreeNode<DataType> rightNode = right.getRoot();

		this.root = new TreeNode<DataType>(leftNode, key, rightNode);

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
	public Tree<DataType> left() {

		return new Tree<DataType>(root.getLeft());

	}

	/**
	 * Returns the right subtree of the root
	 * 
	 * @return Tree object representing the left sub-tree
	 */
	public Tree<DataType> right() {

		return new Tree<DataType>(root.getRight());

	}

	/**
	 * Returns key of tree node
	 * 
	 * @throws TreeIsEmptyException
	 */
	public DataType key() throws TreeIsEmptyException {
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
	private void printLeavesAndPath(TreeNode<DataType> node, String path) {
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
	private String treeStructure(TreeNode<DataType> node) {
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

	/**
	 * builds tree using disected Strings representing left and right subtrees
	 * 
	 * @param in String to build tree from
	 * @return fully build tree
	 */
	public static Tree<String> buildTreeFomString(String in) {
		Tree<String> left;
		Tree<String> right;

		String[] substrings = substringsLeftAndRight(in);
		String leftString = substrings[0];

		if (leftString.isEmpty()) {
			left = new Tree<String>();
		} else {
			left = buildTreeFomString(leftString);
		}

		String rightString = substrings[1];
		if (rightString.isEmpty()) {
			right = new Tree<String>();
		} else {
			right = buildTreeFomString(rightString);
		}

		return new Tree<String>(left, in, right);
	}

	/**
	 * Splits a given String into a two substring, the left one is determined by the
	 * outermost brackets, the right one is the outside remaining on the right
	 * 
	 * @param in The String to be Split
	 * @return A 2 Element String Array with both Substrings
	 * @throws IllegalArgumentException
	 */
	private static String[] substringsLeftAndRight(String in) throws IllegalArgumentException {

		StringBuilder builder = new StringBuilder(in);
		int counter = 0;
		StringBuilder stringA = new StringBuilder();

		do {
			if (builder.charAt(0) == '[') {
				counter++;
			} else if (builder.charAt(0) == ']') {
				counter--;
			} else {
				throw new IllegalArgumentException("no non '[]' characters allowed");
			}
			stringA.append(builder.charAt(0));
			builder.deleteCharAt(0);

		} while (counter > 0);

		if (counter < 0) {
			throw new IllegalArgumentException("invalid String Expression");
		}

		stringA.deleteCharAt(0);
		stringA.deleteCharAt(stringA.length() - 1);

		return new String[] { stringA.toString(), builder.toString() };

	}

	public TreeNode<DataType> getRoot() {

		return this.root;
	}
}
