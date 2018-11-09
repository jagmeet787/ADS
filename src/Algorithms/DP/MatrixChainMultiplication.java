package Algorithms.DP;

import java.util.Arrays;

public class MatrixChainMultiplication {
	public static Integer _matrixChainMultiplicationRec(Integer[] arr, Integer start, Integer end) {
		if(end <= start + 1) return 0;
		if(end - start + 1 == 3)
			return arr[start] * arr[start + 1] * arr[start + 2];
		Integer res = Integer.MAX_VALUE, cur = 0;
		for(int i = start + 1; i < end; i++) {
			cur = _matrixChainMultiplicationRec(arr, start, i) + 
					_matrixChainMultiplicationRec(arr, i, end) + arr[start] * arr[i] * arr[end];
			res = Math.min(res, cur);
		}
		return res;
	}
	
	public static Integer matrixChainMultiplicationRec(Integer[] arr) {
		if(arr.length < 3) return 0;
		return _matrixChainMultiplicationRec(arr, 0, arr.length - 1);
	}
	
	public static Integer matrixChainMultiplicationDP(Integer[] arr) {
		if(arr.length < 3) return 0;
		if(arr.length == 3) return arr[0] * arr[1] * arr[2];
		Integer[][] table = new Integer[arr.length - 1][arr.length - 1];
		for(int i = 0; i < table.length; i++) Arrays.fill(table[i], Integer.MAX_VALUE);
		for(int i = 0; i < table.length; i++) table[i][i] = 0;
		// column outside
		for(int j = 1; j < table[0].length; j++) {
			for(int i = j - 1; i >= 0; i--) {
				// Base case 2 matrix
				if(i == j - 1) {
					table[i][j] = arr[i] * arr[i + 1] * arr[i + 2];
				}
				else {
					table[i][j] = Integer.MAX_VALUE;
					for(int k = i; k < j; k++) {
						Integer cur = table[i][k] + table[k+1][j] + arr[i]*arr[k]*arr[j];
						if(cur < table[i][j]) {
							table[i][j] = Math.min(table[i][j], cur);
							table[j][i] = k;
						}
					}
				}
			}
		}
System.out.println(Arrays.deepToString(table));
		return table[0][arr.length - 2];
	}
}
