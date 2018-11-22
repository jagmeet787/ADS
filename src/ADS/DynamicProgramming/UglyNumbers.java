package ADS.DynamicProgramming;

public class UglyNumbers {
	static private boolean isUgly(int number) {
		while ( number % 2 == 0 ) number /= 2;
		while ( number % 3 == 0 ) number /= 3;
		while ( number % 5 == 0 ) number /= 5;
		if ( number == 1 ) return true;
		return false;
	}
	
	static public int nthUglyNumber(int n) {
		int count = 1;
		int number = 1;
		while ( count < n ) {
			number++;
			if ( isUgly( number ) )
				count++;
		}
		return number;
	}
	
	static public int nthUglyNumberDP(int n) {
		if ( n <= 0 ) return -1;
		if ( n == 1) return 1;
		int[] uglyNumbers = new int[n];
		uglyNumbers[0] = 1;
		int i2 = 0, i3 = 0, i5 = 0;
		int nextMultiple2 = 2, nextMultiple3 = 3;
		int nextMultiple5 = 5;
		for ( int i = 1; i < n; i++ ) {
			uglyNumbers[i] = Math.min(nextMultiple2, 
					Math.min(nextMultiple3, nextMultiple5));
			if ( uglyNumbers[i] == nextMultiple2 ) {
				i2++; nextMultiple2 = 2 * uglyNumbers[i2];
			}
			if ( uglyNumbers[i] == nextMultiple3 ) {
				i3++; nextMultiple3 = 3 * uglyNumbers[i3];
			}
			if ( uglyNumbers[i] == nextMultiple5 ) {
				i5++; nextMultiple5 = 5 * uglyNumbers[i5];
			}
		}
		return uglyNumbers[n-1];
	}
}
