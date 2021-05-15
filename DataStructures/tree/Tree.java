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
	 * change
	 * 
	 * @return
	 * @throws TreeIsEmptyException
	 */
	public DataType key() throws TreeIsEmptyException {
		if (isEmpty()) {
			throw new TreeIsEmptyException();
		}

		return root.getValue();

	}

	public void printLeavesAndPath() {
		if (this.isEmpty()) {
			System.out.println("empty");
		} else {
			printLeavesAndPath(this.root, "");
		}
	}

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

	public String treeStructure() {

		return treeStructure(root);
	}

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

	private TreeNode<DataType> getRoot() {
		return this.root;
	}
}
