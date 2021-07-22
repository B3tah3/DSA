package sorting;

public class Heapsort {

	final int[] heap;
	public int comparisons = 0;

	public Heapsort(final int[] heap) {
		this.heap = heap;
	}

	/**
	* 
	*/
	public int[] heapsort() {

		for (int i = heap.length - 2; i >= 0; i--) {
			reheap(i, heap.length - 1);

			// System.out.println(isPartialHeap(i, heap.length - 1));
			// System.out.println(this);
		}

		for (int i = heap.length - 1; i > 0; i--) {

			swap(0, i);
			reheap(0, i - 1);

		}

		return heap;

	}

	void swap(int a, int b) {
		int t = heap[a];
		heap[a] = heap[b];
		heap[b] = t;
	}

	/**
	 * Checks, if a[l]...a[r] is a Max-Heap in heap a
	 * 
	 * @param l First Component of Part-Heap
	 * @param r Last Component of Part-Heap
	 */
	private boolean isPartialHeap(final int l, final int r) {

		for (int i = r; i > 0 && (i - 1) / 2 >= l; i--) {
			comparisons++;
			if (heap[i] > heap[(i - 1) / 2]) {
				return false;
			}

		}

		return true;

	}

	/**
	 * Sinks in element a[l] into heap a[l+1]...a[r]
	 * 
	 * @param l extension element
	 * @param r end element of part heap
	 */
	private void reheap(final int l, final int r) {

		int k = l;

		boolean notDone = true;
		while (notDone) {

			int j = 2 * k + 1;

			if (j > r) {
				notDone = false;
			} else {
				comparisons++;
				if (j + 1 <= r && heap[j + 1] > heap[j]) {
					j++;
				}
				comparisons++;
				if (heap[k] < heap[j]) {
					swap(k, j);
					k = j;

				} else {
					notDone = false;
				}
			}
		}

	}

	public String toString() {

		String output = "Heap:[" + heap[0];
		for (int i = 1; i < heap.length; i++) {
			output += ", " + heap[i];
		}
		output += "], Comparisons:" + comparisons;
		return output;
	}

}
