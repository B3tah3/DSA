package tree;

public class SearchTreeNode /* extends TreeNode<String> */ {

	private int height, balance, nodeCount, depthSum;
	private String value;

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

	public String getValue() {
		return value;
	}

	SearchTreeNode left, right;

	public SearchTreeNode getLeft() {
		return left;
	}

	public SearchTreeNode getRight() {
		return right;
	}

	public void setLeft(SearchTreeNode left) {
		this.left = left;
	}

	public void setRight(SearchTreeNode right) {
		this.right = right;
	}

	public SearchTreeNode(String i) {
		value = i;
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

}
