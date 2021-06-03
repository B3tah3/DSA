package tree;

public class SearchTree extends Tree<String> {

	private SearchTreeNode root;

	public SearchTree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchTree(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	public SearchTree(Tree<String> left, String key, Tree<String> right) {
		super(left, key, right);
		// TODO Auto-generated constructor stub
	}

	private SearchTreeNode insert(SearchTreeNode node, String x) {

		SearchTreeNode returnNode = node;

		if (node == null) {
			return new SearchTreeNode(x);
		} else if (node.getValue().compareTo(x) < 0) {

			node.setLeft(insert(node.getLeft(), x));

		} else if (node.getValue().compareTo(x) > 0) {

			node.setRight(insert(node.getRight(), x));

		} else {
			return node;
		}

		/*
		 * if (node == null) { returnNode = new TreeNode<String>(x); } else {
		 */
		/*
		 * switch (node.getValue().compareTo(x)) { case 1: returnNode =
		 * insert(node.getRight(), x); node.setRight(returnNode); break; case -1:
		 * returnNode = insert(node.getLeft(), x); node.setLeft(returnNode); break; case
		 * 0: returnNode = node; break;
		 * 
		 * }
		 */
		// }

		return returnNode;
	}

	public void insert(String x) {

		root = insert(root, x);
//		TreeNode<String> currentNode = super.getRoot();

	}

	public void printRootData() {
		root.setTreeData();
		System.out.println("Height: " + root.getHeight());
		System.out.println("Balance: " + root.getBalance());
		System.out.println("NodeCount: " + root.getNodeCount());
		System.out.println("DepthSum: " + root.getDepthSum());

	}
}
