package huffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import textCodierung.CharacterCodingLib.CharacterCoding;

public class LetterCounter {

	static String input = "Resources/faust.txt";
	static String output = "Resources/faustCoding.txt";

	public static void main(String[] args) throws IOException {

		HashMap<Integer, Integer> valueMap = countValuesFromText(new CharacterCoding());
		writeToFile(valueMap, output);

	}

	public static void writeToFile(HashMap<Integer, Integer> valueMap, String filename) throws IOException {
		FileWriter f = new FileWriter(filename);
		for (Integer key : valueMap.keySet()) {
			f.write(key + ", " + valueMap.get(key) + ";\n");
		}
		f.close();
	}

	public static HashMap<Integer, Integer> readValueFromFile() {
		try (FileReader f = new FileReader(input)) {
			String line = f.read();
		}

		return new HashMap<Integer, Integer>();
	}

	/**
	 * Counts every letter in a given text file and returns the values in a hashmap
	 * 
	 * @param coding
	 * @return
	 */
	public static HashMap<Integer, Integer> countValuesFromText(CharacterCoding coding) {
		HashMap<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
		try (FileReader f = new FileReader(input)) {
			boolean end = false;
			while (!end) {
				int c = 0;
				try {
					c = f.read();
				} catch (IOException e) {
					end = true;
				}
				if (c != -1) {
					int code = coding.getCode((char) c);
					if (valueMap.containsKey(code)) {
						valueMap.put(code, valueMap.get(code) + 1);
					} else {
						valueMap.put(code, 1);
					}
				} else {
					end = true;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return valueMap;
	}
}
