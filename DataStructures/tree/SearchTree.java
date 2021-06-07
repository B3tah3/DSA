package tree;

public class SearchTree extends Tree<String> {

	public SearchTree() {
		super();
	}

	public SearchTree(String key) {
		super(key);
	}

	public SearchTree(Tree<String> left, String key, Tree<String> right) {
		super(left, key, right);
	}

	private TreeNode<String> insert(TreeNode<String> node, String x) {

		TreeNode<String> returnNode = node;

		if (node == null) {
			return new TreeNode(x);
		} else if (node.getValue().compareTo(x) > 0) {

			node.setLeft(insert(node.getLeft(), x));

		} else if (node.getValue().compareTo(x) < 0) {

			node.setRight(insert(node.getRight(), x));

		} else {
			return node;
		}

		return returnNode;
	}

	public void insert(String x) {

		root = insert(root, x);
//		TreeNode<String> currentNode = super.getRoot();

	}

	public void printRootData() {
		root.setTreeData();
		System.out.println(root.getDataAsString());

	}

	public void printTreeDiagram() {
		root.setTreeData();
		printTreeDiagram(root, 0);
	}

	private static void printTreeDiagram(TreeNode node, int depth) {
		int currentDepth = depth + 1;

		if (node.hasLeft()) {
			printTreeDiagram(node.getLeft(), currentDepth);
		}

		System.out.println(pipeSpacer(depth) + node.getValue() + ", " + node.getDataAsString());

		if (node.hasRight()) {
			printTreeDiagram(node.getRight(), currentDepth);
		}

	}

	private static String pipeSpacer(int depth) {
		String spaceholder = "";
		for (int i = 0; i < depth; i++) {
			spaceholder += "|  ";
		}
		return spaceholder;
	}

	public double getAverageDepth() {
		return root.getDepthSum() / root.getNodeCount();
	}

}
