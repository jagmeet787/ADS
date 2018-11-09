package Algorithms.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingDecreasingSequence {
	
	//https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/	
	
	public static Integer longestIncreasingSequenceRec(Integer[] arr) {
		// Discuss the recursive solution of this
		return 0;
	}
	
	//Sort the sequence and find lcs with the original sequence
	public static Integer longestIncreasingSubsequenceLCS(Integer[] arr) {
		Integer[] arr1 = Arrays.copyOf(arr, arr.length);
		Arrays.sort(arr1);
		// convert this to array
//		return LongestCommonSubsequence.longestCommonSubsequenceDPMin(arr, arr1);
		return 0;
	}
	
	private static String backTrack(Integer[] LIS, Integer[] P, Integer[] arr, Integer max) {
		Integer[] result = new Integer[LIS[max]];
		Integer j = result.length - 1;
		for(int i = max; i != arr.length; i = P[i]) result[j--] = arr[i];
		return Arrays.toString(result);
	}
	
	//	Time : O(n^2) and Space O(n)
	public static Integer longestIncreasingSequenceDP(Integer[] arr) {
		Integer[] LIS = new Integer[arr.length];
		Integer[] P = new Integer[arr.length];
		Arrays.fill(P, arr.length);
		Arrays.fill(LIS, 1);
		for ( Integer i = 1; i < arr.length; i++ ) 	{
			for ( Integer j = 0; j < i; j++ ) {
				if ( arr[i] > arr[j] && LIS[i] < LIS[j] + 1 ) {
					LIS[i] = LIS[j] + 1;
					P[i] = j;
				}
			}
		}
		//max index
		Integer max = 0;
		for ( Integer i = 0; i < arr.length; i++ )
			if(LIS[i] > LIS[max]) max = i;
		
		//Backtracking
		System.out.println(backTrack(LIS, P, arr, max));
		
		return LIS[max];
	}
	
	// Time : O(nlogn)
	// Space : O(Max LIS)
	public static int longestIncreasingSequenceDP_OT(int[] arr) {
		ArrayList<Integer> run = new ArrayList<Integer>();
		run.add(arr[0]);
		int max = 1;
		for(int i = 1; i < arr.length; i++) {
			int insertionPoint = Collections.binarySearch(run, arr[i]);
			if(insertionPoint < 0) {
				insertionPoint = -(insertionPoint) - 1;
				if(insertionPoint == run.size()) run.add(arr[i]);
				else run.set(insertionPoint, arr[i]);
			}
			if(max < run.size()) max = run.size();
		}
		return max;
	}
	
//	public static int longestIncreasingSequenceDP_O(int[] arr) {
//		class Pair {
//			public static <T, U> Map.Entry<T, U> of(T first, U second) {
//				return new AbstractMap.SimpleEntry<>(first, second);
//			}
//		}
//		ArrayList<Entry> run = new ArrayList<Entry>();
//		run.add(Pair.of(arr[0], 0));
//		int max = 1;
//		for(int i = 1; i < arr.length; i++) {
//			int insertionPoint = Collections.BinarySearch(run, arr[i], new Comparator<Entry>(){
//				public int compare(Entry e1, Entry e2) {
//					Integer first = (Integer)e1.getKey();
//					Integer second = (Integer)e2.getKey();
//					return second.compareTo(first);
//				}
//			});
//			if(insertionPoint < 0) {
//				insertionPoint = -(insertionPoint) - 1;
//				if(insertionPoint == run.size()) run.add(arr[i]);
//				else run.set(insertionPoint, arr[i]);
//			}
//			if(max < run.size()) max = run.size();
//		}
//		return max;
//	}
	
	private static String backTrackIncrDecr(Integer[] LIS, Integer[] PI, 
			Integer[] LDS, Integer[] PD, Integer[] arr, Integer max) {
		
		Integer[] result = new Integer[LIS[max] + LDS[max] - 1];
		Integer index = result.length - LIS[max];
		for (Integer i = max; i < arr.length && index < result.length; i = PI[i]) {
			result[index++] = arr[i]; 
		}
		index = LDS[max] - 2;
		for (Integer i = PD[max]; i < arr.length && index >= 0; i = PD[i]) {
			result[index--] = arr[i]; 
		}
		return Arrays.toString(result);
	}
	
	public static Integer longestIncreasingDecreasing(Integer[] arr) {
		Integer[] LIS = new Integer[arr.length];
		Integer[] LDS = new Integer[arr.length];
		Integer[] PI = new Integer[arr.length];
		Integer[] PD = new Integer[arr.length];
		Arrays.fill(LDS, 1);
		Arrays.fill(LIS, 1);
		Arrays.fill(PI, arr.length);
		Arrays.fill(PD, arr.length);
		for ( Integer i = arr.length - 2; i >= 0; i-- ) 	{
			for ( Integer j = arr.length - 1; j > i; j-- ) {
				if ( arr[i] < arr[j] && LIS[i] < LIS[j] + 1 ) {
					LIS[i] = LIS[j] + 1;
					PI[i] = j;
				}
				if ( arr[i] > arr[j] && LDS[i] < LDS[j] + 1 ) {
					LDS[i] = LDS[j] + 1;
					PD[i] = j;
				}
			}
		}

		//max index
		Integer max = 0;
		for ( Integer i = 0; i < arr.length; i++ )
			if(LIS[i] + LDS[i] - 1 > LIS[max] + LDS[max] - 1) max = i;
		
		// Backtracking
		System.out.println(backTrackIncrDecr(LIS, PI, LDS, PD, arr, max));
		
		return LIS[max] + LDS[max] - 1;
	}
	
	private static String backTrackBitonic(Integer[] LIS, Integer[] PI, 
			Integer[] LDS, Integer[] PD, Integer[] arr, Integer max) {
		
		Integer[] result = new Integer[LIS[max] + LDS[max] - 1];
		
		Integer index = LIS[max] - 1;
		
		for (Integer i = max; i < arr.length && index >= 0; i = PI[i]) {
			result[index--] = arr[i]; 
		}
		
		index = LIS[max];
		
		for (Integer i = PD[max]; i < arr.length && index < result.length; i = PD[i]) {
			result[index++] = arr[i]; 
		}
		
		return Arrays.toString(result);
	}
	
	public static Integer longestBitonicSequence(Integer[] arr) {
		Integer[] LIS = new Integer[arr.length];
		Integer[] LDS = new Integer[arr.length];
		Integer[] PI = new Integer[arr.length];
		Integer[] PD = new Integer[arr.length];
		Arrays.fill(LDS, 1);
		Arrays.fill(LIS, 1);
		Arrays.fill(PI, arr.length);
		Arrays.fill(PD, arr.length);
		
		// Longest increasing sequence from start
		for ( Integer i = 1; i < arr.length; i++ ) 	{
			for ( Integer j = 0; j < i; j++ ) {
				if ( arr[i] > arr[j] && LIS[i] < LIS[j] + 1 ) {
					LIS[i] = LIS[j] + 1;
					PI[i] = j;
				}
			}
		}
		
		// Longest decreasing sequence from end
		for ( Integer i = arr.length - 2; i >= 0; i-- ) 	{
			for ( Integer j = arr.length - 1; j > i; j-- ) {
				if ( arr[i] > arr[j] && LDS[i] < LDS[j] + 1 ) {
					LDS[i] = LDS[j] + 1;
					PD[i] = j;
				}
			}
		}
		
		//max index
		Integer max = 0;
		for ( Integer i = 0; i < arr.length; i++ )
			if(LIS[i] + LDS[i] - 1 > LIS[max] + LDS[max] - 1) max = i;

		// Backtracking
		System.out.println(backTrackBitonic(LIS, PI, LDS, PD, arr, max));
		
		return LIS[max] + LDS[max] - 1;
	}
	
	public static void main(String[] args) {
		Integer[] arr = {50, 60, 3, 65, 40, 20, 10};
		System.out.print("Array: [");
		for ( Integer e : arr ) System.out.print(e + ", ");
		System.out.println("\b\b]");
		System.out.println(longestIncreasingDecreasing(arr));
		System.out.print(longestBitonicSequence(arr));
	}
}
