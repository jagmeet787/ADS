package Utility;

import java.util.Arrays;
import java.util.ArrayList;

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

	public static boolean isPrime(int N) {
	    if ( N <= 1 ) return false;
	    if ( N == 2 || N == 3 ) return true;
	    if ( N % 2 == 0 || N % 3 == 0) return false;
	    for ( int p = 5; p * p <= N; p += 6) {
	        if ( N % p == 0 || N % ( p + 2 ) == 0) return false;
        }
	    return true;
    }

    public static boolean[] sieveEratosthenes( int size ) {
        if (size < 2) return null;
        boolean[] sieve = new boolean[size];
        for ( int i = 0; i < size; i++) sieve[i] = true;
        sieve[0] = sieve[1] = false;
        for ( int i = 2; i*i < size; i++ ) {
            if ( sieve[i] ) {
                for ( int p = i*i; p < size; p += i) {
                    sieve[p] = false;
                }
            }
        }
        return sieve;
    }

    public static ArrayList<Integer> factors(int A) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        int temp = A;
        int count = 0;
        while (temp % 2 == 0) {
            temp /= 2;
            count++;
        }
        factors.add(count);
        for ( int div = 3; div <= (int)Math.sqrt(temp); div += 2) {
            count = 0;
            while (temp % div == 0) {
                count++; temp /= div;
            }
            if (count > 0)
                factors.add(count);
        }
        if ( temp > 2 ) factors.add(1);
        return factors;
    }
}
