package Algorithms.DP;

import java.util.Arrays;

public class CoinChange {
	
//Min number of coins
	
	// for top down the state is (size + "-" + n) 
	public static Integer countMinCoinsRec(Integer[] coins, Integer size, Integer n) {
		// If sum is zero then  don't include any coin
		if(n == 0) return 0;
		// sum is negative No solution possible
		if(n < 0) return Integer.MAX_VALUE - 1;
		// if size is less than or equal to zero Not possible 
		if(size <= 0) return Integer.MAX_VALUE - 1;
		
		// Minimum of 1 + including a coin and not including it
		return Math.min(1 + countMinCoinsRec(coins, size, n - coins[size - 1]),
					countMinCoinsRec(coins, size - 1, n));
	}
	
	public static Integer _countMinCoinsRec(Integer[] coins, Integer n) {
		if(n == 0) return 0;
		Integer min = Integer.MAX_VALUE - 1;
		Integer cur = 0;
		for(Integer i = 0; i < coins.length; i++) {
			if(coins[i] <= n) {
				cur = 1 + _countMinCoinsRec(coins, n - coins[i]);
				if(cur < min) min = cur;
			}
		}
		return min;
	}
	// for top down only n can be used as state in this
	public static Integer countMinCoinsRec1(Integer[] coins, Integer n) {
		if(n < 0) return Integer.MAX_VALUE;
		if(coins.length <= 0 ) return Integer.MAX_VALUE;
		return _countMinCoinsRec(coins, n);
	}
	
	public static Integer[] backTrack2D(Integer[][] table, Integer[] coins) {
		Integer row = table.length - 1;
		Integer col = table[0].length - 1;
		Integer[] res = new Integer[table[row][col]];
		Integer ri = res.length - 1;
		while(col >= 0 && row >= 0) {
			if(coins[col] <= row) {
				if(table[row][col] == 1 + table[row - coins[col]][col]) {
					res[ri--] = coins[col];
					row = row - coins[col];
				} else col--;
			} else col--;
		}
		return res;
	}
	
	public static Integer countMinCoinsDP(Integer[] coins, Integer n) {
		Integer[][] table = new Integer[n + 1][coins.length];
		for (int j = 0; j < table[0].length; j++)
			table[0][j] = 0;
		for(int i = 1; i <= n; i++) Arrays.fill(table[i], Integer.MAX_VALUE - 1);
		for(int i = coins[0]; i < table.length; i++) {
			table[i][0] = Math.min(table[i][0], 1 + table[i - coins[0]][0]);
		}
		for(int col = 1; col < coins.length; col++) {
			for(int row = 1; row <= n; row++) {
				if(row < coins[col]) {
					table[row][col] = table[row][col-1];
				}
				else {
					table[row][col] = Math.min(table[row][col - 1], 1 + table[row - coins[col]][col]);
				}
			}
		}
		System.out.println(Arrays.toString(backTrack2D(table, coins)));
		return table[n][coins.length - 1];
	}
	
	public static Integer[] backTrack(Integer[] table, Integer[] coins) {
		Integer[] res = new Integer[table[table.length - 1]];
		int i = table.length - 1;
		int j = coins.length - 1;
		int ri = res.length - 1;
		while(i >= 0 && j >= 0) {
			if(coins[j] <= i) {
				if(table[i] == 1 + table[i - coins[j]]) {
					i = i - coins[j];
					res[ri--] = coins[j];
				} else j--;
			} else {
				j--;
			}
		}
		return res;
	}
	
	public static Integer countMinCoinsDPMin(Integer[] coins, Integer n) {
		Integer[] table = new Integer[n + 1];
		Arrays.fill(table, Integer.MAX_VALUE - 1);
		table[0] = 0;
		for(int i = 0; i < coins.length; i++) {
			for(int j = coins[i]; j < table.length; j++) {
				table[j] = Math.min(table[j], 1 + table[j - coins[i]]);
			}
		}
		
		// Backtracking
		System.out.println(Arrays.toString(backTrack(table, coins)));
		
		return table[n];
	}
	
	
//Count number of ways 
	public static Integer countNumWaysRec(Integer[] coins, Integer size, Integer n) {
		// If sum is zero there is 1 way empty set
		if(n == 0) return 1;
		// if sum is less than zero than no way
		if(n < 0) return 0;
		// if size of coins is less than or zero then no way
		// note that here n >= 1 is implied
		if(size <= 0) return 0;
		
		// no of ways including a coin and excluding a coin
		return countNumWaysRec(coins, size, n - coins[size - 1])
				+ countNumWaysRec(coins, size - 1, n);
		
	}
	
	public static Integer _countNumWaysCombinationRec(Integer[] coins, Integer start, Integer n) {
		if(n == 0) return 1;
		Integer count = 0;
		for(Integer i = start; i < coins.length; i++)
			if(coins[i] <= n)
				count += _countNumWaysCombinationRec(coins, start + i, n - coins[i]);
		return count;
	}
	
	public static Integer countNumWaysCombinationRec(Integer[] coins, Integer n) {
		if(n == 0) return 1;
		if(coins.length <= 0) return Integer.MAX_VALUE;
		return _countNumWaysCombinationRec(coins, 0, n);
	}
	
	// this will count permutations
	public static Integer countNumWaysPermutationRec(Integer[] coins, Integer n) {
		if(n == 0) return 1;
		Integer count = 0;
		for(Integer i = 0; i < coins.length; i++)
			if(coins[i] <= n)
				count += countNumWaysPermutationRec(coins, n - coins[i]);
		return count;
	}
	
	public static Integer countNumWaysDP(Integer[] coins, Integer n) {
		Integer[][] table = new Integer[n + 1][coins.length];
		for(Integer i = 1; i < table.length; i++)
			Arrays.fill(table[i], 0);
		for(Integer i = 0; i < table[0].length; i++)
			table[0][i] = 1;
		for(Integer i = coins[0]; i < table.length; i++)
			table[i][0] = table[i - coins[0]][0];
		for(Integer col = 1; col < coins.length; col++) {
			for(Integer row = 1; row < table.length; row++) {
				if(row < coins[col])
					table[row][col] = table[row][col - 1];
				else
					table[row][col] = table[row][col - 1] + table[row - coins[col]][col];
			}
		}
		return table[n][coins.length - 1];
	}
	
	public static Integer countNumWaysDPMin(Integer[] coins, Integer n) {
		Integer[] table = new Integer[n + 1];
		Arrays.fill(table, 0);
		table[0] = 1;
		for(int i = 0; i < coins.length; i++) {
			for(int j = coins[i]; j <= n; j++) {
				table[j] = table[j - coins[i]] + table[j];
			}
		}
		return table[n];
	}
}
