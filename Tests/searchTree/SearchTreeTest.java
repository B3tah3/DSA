package searchTree;

import tree.SearchTree;
import tree.Tree;

public class SearchTreeTest {

	public static void main(String[] args) {

		randomExperiment(1000, 10);
		randomExperiment(1000, 100);
		randomExperiment(1000, 1000);
		randomExperiment(1000, 10000);

	}

	public static void singleTest() {

		SearchTree st = new SearchTree();

		st.insert("c");
		st.insert("a");
		st.insert("b");
		st.insert("f");
		st.insert("d");
		st.insert("g");
		st.insert("b");
		st.insert("010010");

		st.printTreeDiagram();

		System.out.println("");
		Tree.printInOrder(st.getRoot());
		System.out.println(st.treeStructure());
	}

	public static SearchTree buildRandomTree(long nodeCount) {

		SearchTree tree = new SearchTree();

		for (int i = 0; i < nodeCount; i++) {

			tree.insert(Double.toString(Math.random() * nodeCount * 100));
		}
		tree.getRoot().setTreeData();
		return tree;
	}

	public static void randomExperiment(long times, long treeLength) {

		System.out.println("Experiment Parameters: Repeats:" + times + ", Tree length: " + treeLength);

		double excepctedAverage = 2 * Math.log(2) * (Math.log10(treeLength) / Math.log10(2));
		System.out.println("Expected Average: " + (int) (excepctedAverage * 100) / 100.);

		double runnningAverageDepth = 0;

		for (long i = 0; i < times; i++) {
			SearchTree tree = buildRandomTree(treeLength);
			runnningAverageDepth = (runnningAverageDepth / (i + 1) * i) + (tree.getAverageDepth() / (i + 1));

		}

		System.out.println("Resulted Average: " + (int) (runnningAverageDepth * 100) / 100.);
		System.out.println("Difference in Averages: " + (int) ((excepctedAverage - runnningAverageDepth) * 100) / 100.);
		System.out.println("");

	}

}
