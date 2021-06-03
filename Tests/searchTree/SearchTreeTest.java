package searchTree;

import tree.SearchTree;

public class SearchTreeTest {

	public static void main(String[] args) {

		SearchTree st = new SearchTree();

		st.insert("g");

		st.insert("a");
		st.insert("f");
		st.insert("e");
		st.insert("c");
		st.insert("f");
		st.insert("b");

		st.printRootData();

		// System.out.println(st.treeStructure());
//		st.printInorder(st.getRoot());
	}

}
