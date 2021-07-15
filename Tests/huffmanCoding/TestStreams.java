package huffmanCoding;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

import moveToFront.CharacterCoding;

public class TestStreams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharacterCoding code = new CharacterCoding();
		try {
			IntStream testStream = fileToStream("Resources/faust.txt", code);
			streamToFile(testStream, "Resources/streamOutput.txt", code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IntStream fileToStream(String input, CharacterCoding code) throws IOException {

		IntStream stream = Arrays.stream(code.readFromFile(input));
		return stream;
	}

	public static void streamToFile(IntStream stream, String fileName, CharacterCoding code) throws IOException {

		int[] txt = stream.toArray();
		code.writeToFile(fileName, txt);

	}

}
