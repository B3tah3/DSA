package tree;

public class TreeTest {

	public static void main(String args[]) {

		Tree<Integer> tree1 = new Tree<>(1);
		Tree<Integer> tree2 = new Tree<>(2);

		Tree<Integer> smallTree = new Tree<>(tree1, 0, tree2);
		smallTree.binaryCodingPrintInorder();
	}

}
