package machine;

public class EnigmaMachine {
	Rotors rotors;
	Plugboard plugboard;
	final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public EnigmaMachine(String settingsPath) {
		this.rotors = Rotors.constructRotors(settingsPath);
		this.plugboard = Plugboard.constructPlugboard(settingsPath);	
	}
	
	public String pressLetter(String l) {
		int pos = l2p(l);
		pos = this.rotors.pressPosition(pos);
		//pos = this.plugboard.getOutput(pos);
		return p2l(pos);
	}
	
	public String encodeMessage(String m) {
		m = m.toLowerCase();
		String[] letters = m.split("");
		String codedMessage = "";
		for (int i = 0; i < letters.length; i++) {
			codedMessage += pressLetter(letters[i]);
		}
		
		return codedMessage;
	}
	
	public String decodeMessage(String c) {
		String[] letters = c.split("");
		String m = "";
		for (int i = 0; i < letters.length; i++) {
			m += pressLetter(letters[i]);
		}
		
		return m;
	}
	
	/**
	 * Convert position to letter.
	 */
	private String p2l(int pos) {
		return String.valueOf(this.alphabet.charAt(pos));
	}
	
	/**
	 * Convert letter to position.
	 */
	private int l2p(String l) {
		return this.alphabet.indexOf(l);
	}
}
