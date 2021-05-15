package tree;

public class TreeTest {

	public static void main(String args[]) {

		Tree<Integer> tree1 = new Tree<>(1);
		Tree<Integer> tree2 = new Tree<>(2);

		Tree<Integer> smallTree = new Tree<>(tree1, 0, tree2);

		Tree<Integer> slightlyBiggerTree = new Tree(new Tree(5), 2, smallTree);

		smallTree.printLeavesAndPath();
		System.out.println();
		slightlyBiggerTree.printLeavesAndPath();
		System.out.println(slightlyBiggerTree.treeStructure());
		System.out.println();

		String struc = "[[[]][[]]][][[]]";
		System.out.println("Building Tree from the String: " + struc);
		Tree<String> stringTree = Tree.buildTreeFomString(struc);

		stringTree.printLeavesAndPath();

		String builtStruc = stringTree.treeStructure();
		System.out.println("Built Structure: " + builtStruc);
		if (struc.equals(builtStruc)) {
			System.out.println("Built Structure matches!");
		}
	}

}
