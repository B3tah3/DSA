package burrowsWheeler;

import java.util.ArrayList;
import java.util.Collections;

public class BurrowsWheeler {

	public static int[] transform(int[] textInput) {

		ArrayList<BWElement> permutationOrder = new ArrayList<BWElement>();
		for (int i = 0; i < textInput.length; i++) {
			permutationOrder.add(new BWElement(i, textInput));
		}
		
		Collections.sort(permutationOrder);
		
		int[] textOutput = new int[textInput.length];
		int burrowIndex = 0;
		
		for(int i = 0; i < textInput.length; i++) {
			
			
			if(permutationOrder.get(i).permutationIndex == 0) {
				burrowIndex = i;
			}
			
			textOutput[i] = textInput[Math.floorMod(permutationOrder.get(i).permutationIndex - 1, textInput.length)];
		}
		System.out.println(burrowIndex);
		
		return textOutput;
	}

	public static int[] backTransform(int[] textInput, int burrowIndex) {
		
		ArrayList<BWElement> permutationOrder = new ArrayList<BWElement>();
		for (int i = 0; i < textInput.length; i++) {
			permutationOrder.add(new BWElement(i, new int[]{textInput[i]} ));
		}
		
		Collections.sort(permutationOrder);
		
		
		int[] outputText = new int[textInput.length];
		int nextIndex = burrowIndex;
		
		for(int i = 0; i < textInput.length; i++) {
			outputText[i] = permutationOrder.get(nextIndex).refToText[0];
			nextIndex = permutationOrder.get(nextIndex).permutationIndex;
			
		}
		
		return outputText;
	}

}
