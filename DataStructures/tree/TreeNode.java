package tree;

public class TreeNode<DataType> {

	protected TreeNode<DataType> left;
	protected TreeNode<DataType> right;
	private DataType value;
	private int height, balance, nodeCount, depthSum;

	public TreeNode(final DataType i) {
		this.left = null;
		this.value = i;
		this.right = null;
	}

	public TreeNode(final DataType value, final TreeNode<DataType> right) {
		this.left = null;
		this.value = value;
		this.right = right;
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

	public int getHeight() {
		return height;
	}

	public int getBalance() {
		return balance;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public int getDepthSum() {
		return depthSum;
	}

	public void setTreeData() {

		int rightHeight = -1;
		int leftHeight = -1;

		int rightNodeCount = 0;
		int leftNodeCount = 0;

		int rightDepthSum = 0;
		int leftDepthSum = 0;

		if (right != null) {
			right.setTreeData();

			rightHeight = right.height;

			rightNodeCount = right.nodeCount;
			rightDepthSum = right.depthSum;
		}

		if (left != null) {
			left.setTreeData();

			leftHeight = left.height;
			leftNodeCount = left.nodeCount;
			leftDepthSum = left.depthSum;
		}

		height = Math.max(rightHeight, leftHeight) + 1;
		balance = leftHeight - rightHeight;
		nodeCount = rightNodeCount + leftNodeCount + 1;
		depthSum = rightDepthSum + rightNodeCount + leftDepthSum + leftNodeCount;

	}

	public String getDataAsString() {

		return String.format("height: %d, balance: %d, nodeCount: %d, depthSum: %d", height, balance, nodeCount,
				depthSum);

	}
}
