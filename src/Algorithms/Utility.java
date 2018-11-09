package Algorithms;

import java.util.Arrays;

public class Utility {
	// User Arrays.toString(arr) instead? for single array
	// use Arrays.deepToString instead if single line op
	public static <E> void printArray(E[][] arr){
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	public static boolean integerOverflow(Integer a, Integer b) {
		try {
			Math.addExact(a, b);
		} catch (ArithmeticException ae) {
			return true;
		}
		return false;
	}
}
