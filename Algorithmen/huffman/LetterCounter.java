package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import moveToFront.CharacterCoding;

public class LetterCounter {

//	static String input = "Resources/faustBWMoveToFrontTransformed.txt";

	static String input = "Resources/faust.txt";

	static String output = "Resources/faustCoding.txt";

	public static void main(String[] args) throws IOException {

		int[] valueMap = countValuesFromText(new CharacterCoding());
		writeToFile(valueMap, output);

		// HashMap<Integer, Integer> readList = readValueFromFile();
	}

	/**
	 * writes the character value map to a file
	 * 
	 * @param valueMap
	 * @param filename
	 * @throws IOException
	 */
	public static void writeToFile(int[] valueMap, String filename) throws IOException {
		FileWriter f = new FileWriter(filename);
		for (int i = 0; i < valueMap.length; i++) {
			f.write(i + ", " + valueMap[i] + "\n");
		}

//		for(int i = 0; i < )

		f.close();
	}

	/**
	 * Reads the amount of letters pregenerated in the faustCoding.txt file
	 * 
	 * @return
	 */
	public static HashMap<Integer, Integer> readCodingFromFile() {

		HashMap<Integer, Integer> characterMap = new HashMap<Integer, Integer>();

		try (BufferedReader f = new BufferedReader(new FileReader(output))) {

			String line;
			while ((line = f.readLine()) != null) {
				String[] values = line.split(", ");
				characterMap.put(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return characterMap;
	}

	/**
	 * Counts every letter in a given text file and returns the values in a hashmap
	 * 
	 * @param coding
	 * @return
	 */
	public static int[] countValuesFromText(CharacterCoding coding) {

		int[] valueMap = new int[111];

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

					valueMap[code]++;

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
