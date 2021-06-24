package machine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class AllTestsEnigmaMachine {
	@Test
	void test() {
		Rotor rotor = new Rotor(0, 0);
		assert rotor.getType() == 0;
		assert rotor.getPosition() == 0;
		assert rotor.getOutput(0) == 24;
		assert rotor.getOutput(5) == 5;
		
		rotor.setPosition(5);
		assert rotor.getOutput(0) == 5;
		assert rotor.getOutput(10) == 12;
		
		Rotors rotors = Rotors.constructRotors("Settings.txt");
		assert rotors.rotor2.getPosition() == 0;
		assert rotors.rotor2.getType() == 2;
		
		Plugboard plugboard = Plugboard.constructPlugboard("Settings.txt");
		assert plugboard.getOutput(0) == 2;
		assert plugboard.getOutput(6) == 16;
		
		// assert rotors.pressPosition(0) == 13;
		
		EnigmaMachine enigma = new EnigmaMachine("Settings.txt");
		/** First test
		
		System.out.println(enigma.pressLetter("a"));
		System.out.println(enigma.pressLetter("b"));
		enigma = new EnigmaMachine("Settings.txt");
		System.out.println(enigma.pressLetter("e"));
		System.out.println(enigma.pressLetter("p"));
		*/

		// The real tests
		// Encode message
		enigma = new EnigmaMachine("Settings.txt");
		String code = enigma.encodeMessage("aardappelpurree");
		
		// Reset enigma machine and decode message
		enigma = new EnigmaMachine("Settings.txt");
		String message = enigma.decodeMessage(code);
		System.out.println(message);
	}
}
