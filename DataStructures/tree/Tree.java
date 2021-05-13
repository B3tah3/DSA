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

	public void binaryCodingPrintInorder() {
		if (this.isEmpty()) {
			System.out.println("empty");
		} else {
			binaryCodingPrintInorder(this.root);
		}
	}

	private void binaryCodingPrintInorder(TreeNode<DataType> node) {
		if (node.hasLeft()) {
			this.binaryCodingPrintInorder(node.getLeft());
		}

		System.out.println(node.getValue());

		if (node.hasRight()) {
			this.binaryCodingPrintInorder(node.getRight());
		}

	}

//	public String treeStructure() {
//		String structure = "";
//		if (node.hasLeft()) {
//			structure += root.getLeft().tree;
//		}
//
//		if (node.hasRight()) {
//			this.binaryCodingPrintInorder(node.getRight());
//		}
//	}

	private TreeNode<DataType> getRoot() {
		return this.root;
	}
}
