package machine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import rotorFactory.MakeRotors;

public class Plugboard {
	private int[] plugs;
	private Boolean[] connected;
	
	private Plugboard() {
		this.plugs = MakeRotors.range(0, 25);
		this.connected = new Boolean[26];
		Arrays.fill(this.connected, Boolean.FALSE);
	}
	
	private void connect(int a, int b) {
		if (this.connected[a] || this.connected[b])
			throw new IllegalArgumentException("at least one of these nodes is already connected.");
		else {
			this.plugs[a] = b;
			this.plugs[b] = a;
			this.connected[a] = true;
			this.connected[b] = false;
		}
	}
	
	public int getOutput(int input) {
		return this.plugs[input];
	}
	
	public static Plugboard constructPlugboard(String settingsPath) {
		Plugboard plugboard = new Plugboard();
		String ENTER = System.getProperty( "line.separator" );
		File settings = new File(settingsPath);
		String delimiter = ENTER;
		
		try {
			Scanner Reader = new Scanner(settings).useDelimiter(delimiter);
			
			// Skip first 6 lines
			for (int i = 0; i < 6; i++)
				Reader.next();
			
			String original = Reader.next();
			String[] str = original.split(",");
			
			int[][] settingsArray = new int[str.length][2];
			final String alphabet = "abcdefghijklmnopqrstuvwxyz";
			for (String plug : str) {
				String[] nodes = plug.split("-");
				nodes[0] = nodes[0].toLowerCase();
				nodes[1] = nodes[1].toLowerCase();
				int pos0 = alphabet.indexOf(nodes[0]);
				int pos1 = alphabet.indexOf(nodes[1]);
				plugboard.connect(pos0, pos1);
			}
			Reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return plugboard;
	}
}
