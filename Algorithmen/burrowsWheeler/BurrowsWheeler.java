package burrowsWheeler;

import java.util.ArrayList;
import java.util.Collections;

public class BurrowsWheeler {

	/**
	 * Applies BurrowsWheeler Transformation to an array of ints. The index for the
	 * backtransformation is contained in the 5 first indices of the array.
	 * 
	 * @param textInput Array to be transfomed
	 * @return Transformed array, with index for backtransform in the first 5 digits
	 *         in base 110
	 **/
	public static int[] transform(int[] textInput) {

		ArrayList<BWElement> permutationOrder = new ArrayList<BWElement>();
		for (int i = 0; i < textInput.length; i++) {
			permutationOrder.add(new BWElement(i, textInput));
		}

		Collections.sort(permutationOrder);

		int[] textOutput = new int[textInput.length + 5];
		int burrowIndex = 0;

		for (int i = 0; i < textInput.length; i++) {

			if (permutationOrder.get(i).permutationIndex == 0) {
				burrowIndex = i;
			}

			textOutput[i + 5] = textInput[Math.floorMod(permutationOrder.get(i).permutationIndex - 1,
					textInput.length)];
		}

		// writing the burrow-Wheeler index into the output array
		int[] base110Index = convertToBase110(burrowIndex);
		for (int i = 0; i < 5; i++) {

			textOutput[i] = base110Index[i];

		}

		return textOutput;
	}

	/**
	 * Converts Base10 number to Base110
	 * 
	 * @param number Base10 number to be converted
	 * @return Array with length 5 of number in Base110
	 **/
	public static int[] convertToBase110(int number) {

		int[] output = new int[5];
		for (int i = 0; i < 5; i++) {

			output[i] = number % 110;
			number /= 110;

		}
		return output;
	}

	/**
	 * Converts Base110 number to Base10
	 * 
	 * @param base110Number Int array to be converted
	 * @return Number in Base10 as java primitive int
	 **/
	public static int convertFromBase110(int[] base110Number) {
		int base10Int = 0;
		int powerOf110 = 1;
		for (int i = 0; i < 5; i++) {

			base10Int += powerOf110 * base110Number[i];
			powerOf110 *= 110;
		}
		return base10Int;
	}

	/**
	 * Retransforms an int array, that has formerly been transformed by the BW
	 * transform. The index for the transformation has to be in Base110 in the first
	 * 5 digits of the array.
	 * 
	 * @param textInput Int array to be retransformed
	 * @return Original array
	 **/
	public static int[] reverseTransform(int[] textInput) {

		int[] base110Index = new int[5];
		for (int i = 0; i < 5; i++) {

			base110Index[i] = textInput[i];

		}
		int burrowIndex = convertFromBase110(base110Index);

		ArrayList<BWElement> permutationOrder = new ArrayList<BWElement>();
		for (int i = 5; i < textInput.length; i++) {
			permutationOrder.add(new BWElement(i - 5, new int[] { textInput[i] }));
		}

		Collections.sort(permutationOrder);

		int[] outputText = new int[textInput.length - 5];
		int nextIndex = burrowIndex;

		for (int i = 0; i < outputText.length; i++) {
			outputText[i] = permutationOrder.get(nextIndex).refToText[0];
			nextIndex = permutationOrder.get(nextIndex).permutationIndex;

		}

		return outputText;
	}

}
