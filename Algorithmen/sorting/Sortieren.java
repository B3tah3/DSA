package sorting;

/**
 * Programmrahmen zur Implementierung von Sortierverfahren
 */
public class Sortieren {
	/** Beginn der Zeitrechnung von wieLange() **/
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

	/**
	 * Neues Feld mit Zufallszahlen
	 * 
	 * @param n   So viele Komponenten, bitte
	 * @param max Komponenten sind im Bereich 1 .. max
	 * @return Neues Feld
	 */
	static int[] erzeugeFeld(int n, int max) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = (int) (Math.random() * max);
		}
		return a;
	}

	/**
	 * Feld ausgeben
	 * 
	 * @param a       Auszugebendes Feld
	 * @param anfang  Text vor der ersten Komponente
	 * @param trenner Text zwischen zwei Komponenten
	 * @param ende    Text nach der letzten Komponente
	 */
	static void druckeFeld(int[] a, String anfang, String trenner, String ende) {
		System.out.print(anfang);
		for (int i = 0; i < a.length; i++) {
			if (i > 0) {
				System.out.print(trenner);
			}
			System.out.print(a[i]);
		}
		System.out.print(ende);
	}

	/**
	 * Bubblesort
	 * 
	 * @param a       Feld, das sortiert wird.
	 * @param verbose If set to true, runtime, swap- and comparison count will be
	 *                printed
	 */
	static void bubblesort(int[] a, boolean verbose) {

		long comparisons = 0;
		long swaps = 0;
		starteUhr();

		boolean notDone = true;

		for (int i = a.length - 1; notDone; i--) {

			notDone = false;

			int bubble = a[0];
			for (int j = 1; j < i + 1; j++) {

				comparisons++;
				if (a[j] < bubble) {

					a[j - 1] = a[j];
					swaps++;
					notDone = true;

				} else {

					a[j - 1] = bubble;
					bubble = a[j];

				}

			}
			a[i] = bubble;

		}
		if (verbose) {
			System.out.format("Laufzeit:  %f ms; Swaps: %d; Comparisons: %d \n", wieLange(), swaps, comparisons);
		}
	}

	/**
	 * Insertsort
	 * 
	 * @param a       Feld, das sortiert wird
	 * @param verbose If set to true, runtime, swap- and comparison count will be
	 *                printed
	 */
	static void insertsort(int[] a, boolean verbose) {

		long comparisons = 0;
		long swaps = 0;

		int j = 0;
		for (int i = 1; i < a.length; i++) {

			j = i - 1;

			comparisons++;

			while (j >= 0 && a[j + 1] < a[j]) {

				swaps++;
				int temp = a[j];
				a[j] = a[j + 1];
				a[j + 1] = temp;

				j--;

				// check if the next loop fails on the first criteria, avoiding one comparison
				// because of &&
				if (j >= 0) {
					comparisons++;
				}
			}

		}
		if (verbose) {
			System.out.format("Laufzeit:  %f ms; Swaps: %d; Comparisons: %d \n", wieLange(), swaps, comparisons);
		}
	}

	/**
	 * Haupt-Methode
	 *
	 * @param args Kommandozeilenparameter (hier nicht verwendet)
	 */
	public static void main(String[] args) {
		int[] a;

		System.out.println("Bubblesort:");
		a = erzeugeFeld(10, 100);
		druckeFeld(a, "Test - Vorher: ", "-", ".\n");
		starteUhr();
		bubblesort(a, true);
		druckeFeld(a, "Test - Nachher: ", "-", ".\n\n");

		System.out.println("Insertsort:");
		a = erzeugeFeld(10, 100);
		druckeFeld(a, "Test - Vorher: ", "-", ".\n");
		starteUhr();
		insertsort(a, true);
		druckeFeld(a, "Test - Nachher: ", "-", ".\n\n");

		System.out.println("n = 100");
		System.out.println("Bubblesort:");
		a = erzeugeFeld(100, 100);
		starteUhr();
		bubblesort(a, true);

		System.out.println("Insertsort:");
		a = erzeugeFeld(100, 100);
		starteUhr();
		insertsort(a, true);

		System.out.println("\nn = 1000");
		System.out.println("Bubblesort:");
		a = erzeugeFeld(1000, 100);
		starteUhr();
		bubblesort(a, true);

		System.out.println("Insert:");
		a = erzeugeFeld(1000, 100);
		starteUhr();
		insertsort(a, true);

		System.out.println("\n");
		int timesToTest = 20;

		double[][] times = new double[timesToTest][2];
		double[][] differences = new double[timesToTest - 1][2];
		float[] averageComputationTimeDifference = new float[2];

		// Zehn Tests in wurzel-abstand, um ergebnis linear zu analysieren
		for (int i = 0; i < timesToTest; i++) {
			int n = (int) Math.sqrt((i + 1) * 100000000);

			System.out.format("Testing with %d array length.\n", n);
			times[i] = test(n);

			if (i > 0) {
				differences[i - 1][0] = Math.abs(times[i][0] - times[i - 1][0]);
				differences[i - 1][1] = Math.abs(times[i][1] - times[i - 1][1]);
			}

		}

		for (int i = 0; i < timesToTest - 1; i++) {
			System.out.format("Bubble time diff: %fms, Insert time diff: %fms\n", differences[i][0], differences[i][1]);

			for (int j = 0; j < averageComputationTimeDifference.length; j++) {
				averageComputationTimeDifference[j] += (1f / timesToTest) * differences[i][j];
			}
		}

		System.out.format("Average Time Difference: \n Bubblesort: %fms \n Insertionsort: %fms \n",
				averageComputationTimeDifference[0], averageComputationTimeDifference[1]);

	}

	private static double[] test(int arraySize) {
		double[] times = new double[2];
		int[] a;
		a = erzeugeFeld(arraySize, arraySize);

		starteUhr();
		bubblesort(a, true);
		times[0] = wieLange();
		System.out.format("Bubblesort: %fms \n", times[0]);

		a = erzeugeFeld(arraySize, arraySize);

		starteUhr();
		insertsort(a, true);
		times[1] = wieLange();
		System.out.format("Insertsort: %fms \n\n", times[1]);

		return times;
	}
}
