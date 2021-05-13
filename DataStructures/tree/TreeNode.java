package tree;

public class TreeNode<DataType> {

	private TreeNode<DataType> left;
	private TreeNode<DataType> right;
	private DataType value;

	public TreeNode(final DataType i) {
		this.left = null;
		this.value = i;
		this.right = null;
	}

	public TreeNode(final DataType value, final TreeNode<DataType> right) {
		this.left = right;
		this.value = value;
		this.right = null;
	}

	public TreeNode(final TreeNode<DataType> left, final DataType value) {
		this.left = left;
		this.value = value;
		this.right = null;
	}

	public TreeNode(final TreeNode<DataType> left, final DataType value, final TreeNode<DataType> right) {
		this.left = left;
		this.right = right;
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	TreeNode<DataType> getLeft() {
		return left;
	}

	/**
	 * 
	 * @param left
	 */
	void setLeft(TreeNode<DataType> left) {
		this.left = left;
	}

	/**
	 * 
	 * @return
	 */
	TreeNode<DataType> getRight() {
		return right;
	}

	/**
	 * 
	 * @param right
	 */
	void setRight(TreeNode<DataType> right) {
		this.right = right;
	}

	/**
	 * 
	 * @return
	 */
	DataType getValue() {

		return value;
	}

	/**
	 * 
	 * @param value
	 */
	void setValue(DataType value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	boolean hasRight() {

		return this.right != null;
	}

	/**
	 * 
	 * @return
	 */
	boolean hasLeft() {
		return this.left != null;
	}

}
