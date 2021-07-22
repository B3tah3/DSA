package heapsort;

import sorting.Heapsort;

public class HeapsortTest {

	public static void main(String[] args) {

		int testLimit = 10000000;

		for (int i = 10; i < testLimit; i *= 10) {
			Heapsort hs = new Heapsort(erzeugeFeld(i, 100));

			starteUhr();
			hs.heapsort();

			System.out.format("Array Size: %d, Comparisons: %d, Time: %fms\n", i, hs.comparisons, wieLange());

		}

	}

	static int[] erzeugeFeld(int n, int max) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = (int) (Math.random() * max);
		}
		return a;
	}

	static long startZeit;

	/**
	 * Speichert aktuelle Uhrzeit fuer die folgenden Aufrufe von wieLange()
	 * 
	 */
	static void starteUhr() {
		startZeit = System.currentTimeMillis();
	}

	/**
	 * Zeit seit dem letzten Aufruf von starteUhr()
	 * 
	 * @return Verstrichene Zeit in Sekunden
	 */
	static double wieLange() {
		long jetzt = System.currentTimeMillis();
		return (double) (jetzt - startZeit) / 1000.0;
	}
}
