package rotorFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MakeRotors {

	public static void main(String[] args) {
		int NBR_OF_ROTORS = 6;
		String ENTER = System.getProperty( "line.separator" );
		
		FileWriter Rotors;
		try {
			Rotors = new FileWriter("Rotors.txt");
			for (int i = 0; i < NBR_OF_ROTORS; i++) {
				//Rotors.write("//Rotor_" + i + ":");
				generateExtraRotor(Rotors);
				Rotors.write("//");
				
			}
			Rotors.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateExtraRotor(FileWriter Rotors) {
		Random random = new Random();
		String[] ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x","y","z"};
		String ENTER = System.getProperty( "line.separator" );
		
		int[] possiblePositions = range(0,25);
		
		for (int i = 0; i < 26; i++) {
			int position = random.nextInt(possiblePositions.length);
			
			try {
				Rotors.write(String.format("%d", possiblePositions[position]));
				if (i != 25)
					Rotors.write(",");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			possiblePositions = remove(possiblePositions, possiblePositions[position]);
		}
		
	}
	
	public static int[] range(int min, int max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum can't be bigger than maximum");
		int[] rtrn = new int[max - min + 1];
		for (int i = 0; i <= (max - min); i++) {
			rtrn[i] = min + i;
		}
		return rtrn;
	}
	
	public static int[] remove(int[] array, int number) {
		int newArrayLength = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != number) {
				newArrayLength++;
			}
		}
		
		int[] newArray = new int[newArrayLength];
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != number) {
				newArray[j] = array[i];
				j++;
			}
		}
		
		if (j != newArrayLength)
			throw new IllegalArgumentException("Method failed");
		
		return newArray;
	}
	
}
