package graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphTest {

	@Test(expected = IllegalArgumentException.class)
	public void depthFirstSearchNoPath() {
		int[][] k = { { 0, 1 } };
		Graph g = new Graph(k);

		g.depthFirstSearch(1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void depthFirstSearchTestWrongParameters() {
		int[][] k = { { 0, 1 } };
		Graph g = new Graph(k);

		g.depthFirstSearch(0, 2);
	}

	@Test
	public void depthFirstSearchTest() {
		int[][] k = { { 0, 1 }, { 1, 0 }, { 0, 3 }, { 1, 3 }, { 3, 4 }, { 4, 2 }, { 2, 4 }, { 2, 2 } };
		Graph g = new Graph(k);

		int[] expected = { 0, 3 };
		int[] path = g.depthFirstSearch(0, 3);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], path[i]);
		}

	}

}
