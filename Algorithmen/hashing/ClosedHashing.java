package hashing;

public class ClosedHashing {

	static int sizeHashtable = 20;
	static int numKeys = 10;

	public static void main(String[] args) {

		boolean[] table = new boolean[sizeHashtable];

		// inserting the keys into the table
		for (int i = 0; i < numKeys; i++) {
			int index = (int) (Math.random() * sizeHashtable);

			while (table[index]) {
				index = after(index);
			}
			table[index] = true;
		}

		// find the first empty index to start counting the lengths of chains
		int emptyIndex = 0;
		while (table[emptyIndex]) {
			emptyIndex = after(emptyIndex);
		}
		int sum = 0;
		int chain = 0;
		// walk backwards and count the chain lengths
		for (int i = before(emptyIndex); i != emptyIndex; i = before(i)) {
			if (table[i]) {
				chain++;
				sum += chain;
			} else {
				chain = 0;
			}
		}

		float averageSearchSteps = sum / (float) numKeys;

		System.out.println("Groesse der Hashtabelle: " + sizeHashtable);
		System.out.println("Anzahl eingefuegter Schluessel: " + numKeys);
		System.out.println(tableToString(table));
		System.out.println("Mittle Sondierungsschritte bei erfolgloser Suche: " + averageSearchSteps);
	}

	static String tableToString(boolean[] table) {
		String s = "";
		for (boolean b : table) {
			s += b ? "#" : "_";
		}
		return s;
	}

	static int before(int i) {
		return (i + sizeHashtable - 1) % sizeHashtable;
	}

	static int after(int i) {
		return (i + 1) % sizeHashtable;
	}
}
