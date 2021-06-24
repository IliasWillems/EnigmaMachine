package machine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import rotorFactory.MakeRotors;

public class Reflector {
	private int[] reflector;
	private Boolean[] connected;
	
	private Reflector() {
		this.reflector = MakeRotors.range(0, 25);
		this.connected = new Boolean[26];
		Arrays.fill(this.connected, Boolean.FALSE);
	}
	
	private void connect(int a, int b) {
		if (this.connected[a] || this.connected[b])
			throw new IllegalArgumentException("at least one of these nodes is already connected.");
		else {
			this.reflector[a] = b;
			this.reflector[b] = a;
			this.connected[a] = true;
			this.connected[b] = false;
		}
	}
	
	public int getOutput(int input) {
		return this.reflector[input];
	}
	
	public static Reflector constructReflector(String settingsPath) {
		Reflector reflector = new Reflector();
		String ENTER = System.getProperty( "line.separator" );
		File settings = new File(settingsPath);
		String delimiter = ENTER;
		
		try {
			Scanner Reader = new Scanner(settings).useDelimiter(delimiter);
			
			// Skip first 9 lines
			for (int i = 0; i < 9; i++)
				Reader.next();
			
			String original = Reader.next();
			String[] str = original.split(",");
			
			for (String pair : str) {
				String[] nodes = pair.split("-");
				int pos0 = Integer.parseInt(nodes[0]);
				int pos1 = Integer.parseInt(nodes[1]);
				reflector.connect(pos0, pos1);
			}
			Reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return reflector;
	}
}
