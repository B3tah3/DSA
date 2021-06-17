package graphs;

/**
 * Gerichtete Graphen mittels Adjazenzlisten
 * 
 * vertex sind numeriert 0 .. n - 1, die Adjazenzliste eines vertexs i ist eine
 * einfach verkettete Liste mit Listenkopf in allNeighbours[i]
 * 
 */
public class Graph {
	/**
	 * Lokale Klasse fuer die einfach verkette Listen mit vertexnummern
	 * 
	 */
	class VertexList {
		/**
		 * vertexnummer
		 */
		public int vertex;
		/**
		 * Nachfolger
		 */
		public VertexList succ;

		public VertexList(VertexList l, int v) {
			vertex = v;
			succ = l;
		}

		@Override
		public String toString() {
			if (succ == null) {
				return vertex + "";
			}
			return vertex + " " + succ.toString();
		}

		/**
		 * Converts a VertexList into an int array
		 * 
		 * @return Array representation of list
		 **/
		public int[] toArray() {

			return this.toArray(0);

		}

		private int[] toArray(int index) {

			if (this.succ == null) {

				int[] returnArray = new int[index + 1];
				returnArray[index] = vertex;

				return returnArray;

			} else {

				int[] returnArray = succ.toArray(index + 1);
				returnArray[index] = vertex;

				return returnArray;
			}
		}
	}

	/**
	 * Feld mit den Adjazenzlisten
	 */
	public VertexList[] allNeighbours;

	private boolean[] visited;

	private boolean[] visitedTopologyVertex;
	private boolean[] permanentTopologyVertex;
	private VertexList list;

	/**
	 * Graph aus Feld von edges erstellen
	 * 
	 * @param edges Feld von Feldern von je zwei nichtneg. int, {i, j} fuegt Kante
	 *              von i nach j ein
	 */
	public Graph(int[][] edges) {
		int min = 0, max = 0;
		// Maximale vertexnummer bestimmen,
		// dabei pruefen, dass alle Felder 2 nichtneg.
		// Zahlen enthalten
		for (int i = 0; i < edges.length; i++) {
			if (edges[i].length != 2) {
				throw new IllegalArgumentException();
			}
			for (int j = 0; j < 2; j++) {
				if (edges[i][j] < min) {
					min = edges[i][j];
				}
				if (edges[i][j] > max) {
					max = edges[i][j];
				}
			}
		}
		if (min < 0) {
			throw new IllegalArgumentException();
		}
		// Adjazenzlisten initialisieren
		allNeighbours = new VertexList[max + 1];
		for (int i = 0; i < allNeighbours.length; i++) {
			allNeighbours[i] = null;
		}
		// edges einfuegen
		for (int i = 0; i < edges.length; i++) {
			int von = edges[i][0], nach = edges[i][1];
			allNeighbours[von] = new VertexList(allNeighbours[von], nach);
		}

		// Init visited array for dfs search
		visited = new boolean[max + 1];

		// init visited and permanent array for topolgy sort
		visitedTopologyVertex = new boolean[max + 1];
		permanentTopologyVertex = new boolean[max + 1];
	}

	/**
	 * Adjazenzmatrix des Graphen
	 *
	 * @return n∗n−Matrix von int, Komponente (i, j) ist 1, falls Kante von i nach j
	 *         ex. , sonst 0
	 */

	public int[][] adjazenzMatrix() {

		int[][] returnMatrix = new int[allNeighbours.length][allNeighbours.length];

		for (int x = 0; x < allNeighbours.length; x++) {

			VertexList vertex = allNeighbours[x];
			while (vertex != null) {

				returnMatrix[x][vertex.vertex] = 1;
				vertex = vertex.succ;
			}
		}

		return returnMatrix;

	}

	@Override
	public String toString() {
		String s = "";
		int[][] matrix = adjazenzMatrix();

		for (int[] r : matrix) {
			for (int i : r) {
				s += i + " ";
			}
			s += "\n";
		}
		return s;
	}

	/**
	 * public interface to depth first search
	 * 
	 * @param startElement index of seach entry point
	 * @param endElement   value of vertex, at wich search is to be stopped
	 * @return an ordered integer array of vertecies through wich the end element
	 *         can be reached
	 * @throws IllegalArgumentException if the given vertex isn't in the Graph
	 * @throws IllegalArgumentException if no path to the end vertex can be found
	 * @requires startElement to be an valid index of a vertex in the Graph
	 * @requires endElement to be in the graph
	 * @ensures int[] valid path from startElement -> endElement
	 */
	public int[] depthFirstSearch(int startElement, int endElement) throws IllegalArgumentException {

		if (startElement < 0 || startElement > allNeighbours.length) {
			throw new IllegalArgumentException("No such element in graph");
		}

		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}

		VertexList path = internalDepthFirstSearch(startElement, endElement);

		if (path == null) {
			throw new IllegalArgumentException("No such path found");
		}
		return path.toArray();
	}

	private VertexList internalDepthFirstSearch(int startElement, int endElement) {

		if (!visited[startElement]) {

			if (startElement == endElement) {
				return new VertexList(null, startElement);
			}

			visited[startElement] = true;

			VertexList iterNeighbours = allNeighbours[startElement];

			while (iterNeighbours != null) {

				VertexList afterPath = internalDepthFirstSearch(iterNeighbours.vertex, endElement);
				iterNeighbours = iterNeighbours.succ;

				if (afterPath != null) {
					return new VertexList(afterPath, startElement);
				}
			}
		}

		return null;
	}

	public VertexList topologySort() {

		list = null;
		// r reset marks
		for (int i = 0; i < allNeighbours.length; i++) {
			visitedTopologyVertex[i] = false;
			permanentTopologyVertex[i] = false;
		}

		for (int i = 0; i < allNeighbours.length; i++) {
			if (!permanentTopologyVertex[i]) {
				visit(i);
			}
		}
		return list;

	}

	private void visit(int n) {
		if (permanentTopologyVertex[n]) {
			return;
		}
		if (visitedTopologyVertex[n]) {
			throw new IllegalArgumentException("Zyklus entdeckt bei Knoten " + n);
		}

		visitedTopologyVertex[n] = true;

		VertexList neighbour = allNeighbours[n];
		while (neighbour != null) {
			visit(neighbour.vertex);
			neighbour = neighbour.succ;
		}

		visitedTopologyVertex[n] = false;
		permanentTopologyVertex[n] = true;

		list = new VertexList(list, n);
	}

}
