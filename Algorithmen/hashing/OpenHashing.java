package hashing;

public class OpenHashing {

	/*
	 * Simulate an open hashing process without actual values
	 */
	public static void main(String[] args) {

		int sizeHashtable = 10;
		int numKeys = 20;
		int[] containers = new int[sizeHashtable];

		for (int i = 0; i < numKeys; i++) {
			containers[(int) (Math.random() * sizeHashtable)]++;
		}

		int[] containerSizes = new int[numKeys];
		for (int c : containers) {
			containerSizes[c]++;
		}

		System.out.println("Groesse der Hashtabelle: " + sizeHashtable);
		System.out.println("Anzahl eingefuegter Schluessel: " + numKeys);
		for (int i = 0; i < numKeys; i++) {
			if (containerSizes[i] != 0) {
				System.out.println(containerSizes[i] + " Behaelter mit " + i + " Schluesseln (erwartet: "
						+ expected(numKeys, i, sizeHashtable) + ")");
			}
		}
	}

	/**
	 * calculates the expected number of containers of fill-level k
	 * 
	 * @param n #trials
	 * @param k #fill-level
	 * @param m #containers
	 * @return float approximation of expected containers
	 */
	static float expected(int n, int k, int m) {

		float chanceSuccess = (float) Math.pow(1 / (float) m, k);
		float chancheFailure = (float) Math.pow((m - 1) / (float) m, n - k);

		// swap k with n-k to avoid huge factorial
		if (k < n - k) {
			k = n - k;
		}
		int upper = 1;
		for (int i = k + 1; i <= n; i++) {
			upper *= i;
		}
		int lower = 1;
		for (int i = 2; i <= n - k; i++) {
			lower *= i;
		}
		int binomialCoefficient = upper / lower;

		float expected = binomialCoefficient * chanceSuccess * chancheFailure * m;

		// round result to three digits
		return Math.round(expected * 100) / 100f;
	}

}
