package machine;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Rotors {
	 Rotor rotor1;
	 Rotor rotor2;
	 Rotor rotor3;
	 Reflector reflector;
	 
	 private Rotors(int type1, int pos1, int type2, int pos2, int type3, int pos3, String settingsPath) {
		 this.rotor1 = new Rotor(type1, pos1);
		 this.rotor2 = new Rotor(type2, pos2);
		 this.rotor3 = new Rotor(type3, pos3);
		 this.reflector = Reflector.constructReflector(settingsPath);
	 }
	 
	 public static Rotors constructRotors(String settingsPath) {
		 String ENTER = System.getProperty( "line.separator" );
		 File settings = new File(settingsPath);
		 String delimiter = ENTER;
		 
		 int[][] settingsArray = new int[3][2];
		 try {
				Scanner Reader = new Scanner(settings).useDelimiter(delimiter);
				
				// Skip first line
				Reader.next();
				
				for (int i = 0; i < 3; i++) {
					String[] str = Reader.next().split("\t");
					settingsArray[i][0] = Integer.parseInt(str[1]);
					settingsArray[i][1] = Integer.parseInt(str[2]);
				}
				Reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 
		 return new Rotors(settingsArray[0][0], settingsArray[0][1], settingsArray[1][0], settingsArray[1][1], settingsArray[2][0], settingsArray[2][1], settingsPath);
	 }
	 
	 public int pressPosition(int pos) {
		 // First pass
		 int output = rotor3.getOutput(rotor2.getOutput(rotor1.getOutput(pos)));
		 // Reflect signal
		 output = reflector.getOutput(output);
		 // Reverse pass
		output = rotor1.getReverseOutput(rotor2.getReverseOutput(rotor3.getReverseOutput(output)));
		 
		 // Rotate rotor 1
		 if (rotor1.getPosition() < 25) {
			 rotor1.setPosition(rotor1.getPosition() + 1);
			 // If necessary, rotate rotor 2
		 } else {
			 if (rotor2.getPosition() < 25) {
				 rotor1.setPosition(0);
				 rotor2.setPosition(rotor2.getPosition() + 1);
				 // If necessary, rotate rotor 3
			 } else {
				 if (rotor3.getPosition() < 25) {
					 rotor1.setPosition(0);
					 rotor2.setPosition(0);
					 rotor3.setPosition(rotor3.getPosition() + 1);
				 } else {
					 rotor1.setPosition(0);
					 rotor2.setPosition(0);
					 rotor3.setPosition(0);
				 }
			 }
		 }
		 
		 return output;
	 }
	 
	 public String getState() {
		 String ENTER = System.getProperty( "line.separator" );
		 
		 String str = "";
		 str += "Rotor1: type = " + this.rotor1.getType() + ", position = " + this.rotor1.getPosition() + ENTER;
		 str += "Rotor2: type = " + this.rotor2.getType() + ", position = " + this.rotor2.getPosition() + ENTER;
		 str += "Rotor3: type = " + this.rotor3.getType() + ", position = " + this.rotor3.getPosition() + ENTER;
		 
		 return str;
	 }
}
