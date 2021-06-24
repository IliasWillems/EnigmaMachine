package rotorFactory;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class AllTestsRotorFactory {
	
	@Test
	void test() {
		for(int i = 0; i < 6; i++) {
			assert MakeRotors.range(0, 5)[i] == i;
		}
		assert Arrays.equals(MakeRotors.range(0, 5), new int[] {0,1,2,3,4,5});
		assert Arrays.equals(MakeRotors.range(0, 0), new int[] {0});
		assert Arrays.equals(MakeRotors.range(5, 6), new int[] {5,6});
		assert Arrays.equals(MakeRotors.range(5, 10), new int[] {5,6,7,8,9,10});
		assert Arrays.equals(MakeRotors.range(0, 25), new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		assert Arrays.equals(MakeRotors.range(2, 25), new int[] {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		
		assert Arrays.equals(MakeRotors.remove(MakeRotors.range(10,15),15), new int[] {10,11,12,13,14});
		assert Arrays.equals(MakeRotors.remove(MakeRotors.range(1,5), 10), MakeRotors.range(1, 5));
		assert Arrays.equals(MakeRotors.remove(MakeRotors.range(0,3),2), new int[] {0,1,3});
	}
	
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
	
}
