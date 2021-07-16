package burrowsWheeler;

public class BWElement implements Comparable<BWElement>{

	int permutationIndex;
	int[] refToText;

	public BWElement(int index, int[] refToText) {
		this.permutationIndex = index;
		this.refToText = refToText;
	}

	@Override
	public int compareTo(BWElement o) {
		
		int signum = 0;
		int compareIndex = 0;
		
		while(signum == 0 && (compareIndex < refToText.length)) {
			
			signum  = refToText[Math.floorMod(compareIndex + this.permutationIndex, refToText.length)] - o.refToText[Math.floorMod(compareIndex + o.permutationIndex, o.refToText.length)];
			compareIndex++;
			
		}
			
		
		return signum;
	}
	
	@Override
	public String toString() {
		return permutationIndex + ", " + refToText[0];
	}
}
