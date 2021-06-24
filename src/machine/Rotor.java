package machine;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Rotor {
	private int type;
	private int[] rotor;
	private int position;
	
	public Rotor(int type, int position) {
		this.type = type;
		this.position = position;
		this.rotor = getRotor(type);
	}
	
	private int[] getRotor(int type) {
		File rotorFile = new File("Rotors.txt");
	    String delimiter = "//";
	    int[][] rotorList = new int[6][26];
		try {
			Scanner Reader = new Scanner(rotorFile).useDelimiter(delimiter);   
			for(int i = 0; i < 6; i++) {
		    	String[] str = Reader.next().split(",");
		    	
		    	int[] list = new int[26];
		    	for(int j = 0; j < 26; j++) {
		    		list[j] = Integer.parseInt(str[j]);
		    	}
		    	rotorList[i] = list;
			}
			Reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rotorList[type];
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void printRotor() {
		for (int i = 0; i < this.rotor.length; i++) {
			System.out.print(this.rotor[i] + ", ");
		}
		System.out.println();
	}
	
	public int getOutput(int input) {
		return this.rotor[(position + input) % 26];
	}
	
	public int getReverseOutput(int input) {
		int index = -1;
		for (int i = 0; i < 26; i++) {
			if (this.rotor[(i + position) % 26] == input)
				index = i;
		}
		return index;
	}
}
