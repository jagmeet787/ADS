package Algorithms.DynamicProgramming;

import java.util.Arrays;

public class ZeroOneKnapsack {
	public static Integer zeroOneKnapsackRec(Integer[] values, Integer[] weights, Integer size, Integer W) {
		// Base case if weight is negative or zero return 0 value
		// If size of array is 0 then return 0
		if(W <= 0 || size == 0) return 0;
		// If W is less than the weight of current item then don't include this in knapsack
		if(weights[size - 1] > W) return zeroOneKnapsackRec(values, weights, size - 1, W); 
		// Return max value obtained by either including this or excluding it
		return Math.max(values[size - 1] + zeroOneKnapsackRec(values, weights, size - 1, W - weights[size - 1]), 
				zeroOneKnapsackRec(values, weights, size - 1, W));
	}
	
	public static String backTrack(Integer[][] table, Integer[] values, Integer[] weights) {
		StringBuilder sb = new StringBuilder();
		int row = table.length;
		int col = table[0].length - 1;
		sb.append("[");
		while(row-- > 1 && col > 0 && table[row][col] != 0) {
			if(col >= weights[row - 1] && table[row][col] != table[row - 1][col]) {
				col = col - weights[row - 1];
				sb.append("(W:" + weights[row - 1] + ", V:" + values[row - 1] + "), ");
			}
		}
		if(sb.length() > 2) sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static Integer zeroOneKnapsackDP(Integer[] values, Integer[] weights, Integer W) {
		if(values.length != weights.length) return -1;
		if(values.length == 0 || W == 0) return 0;
		Integer[][] table = new Integer[weights.length + 1][W + 1];
		for(int row = 0; row < table.length; row++) {
			for(int col = 0; col < table[0].length; col++) {
				if(row == 0 || col == 0) table[row][col] = 0;
				else if(weights[row - 1] > col)
					table[row][col] = table[row - 1][col];
				else
					table[row][col] = Math.max(values[row - 1] + table[row - 1][col - weights[row - 1]], 
							table[row - 1][col]);
			}
		}
		
		// Backtrack
		System.out.println(backTrack(table, values, weights));
		
		return table[weights.length][W];
	}
	
	public static Integer zeroOneKnapsackDPMin(Integer[] values, Integer[] weights, Integer W) {
		if(values.length != weights.length) return -1;
		if(values.length == 0 || W == 0) return 0;
		Integer[] table = new Integer[W + 1];
		Arrays.fill(table, 0);
		for(int j = 0; j < values.length; j++) {
			for(int i = W; i >= 0; i--) {
				if(i >= weights[j]) {
					table[i] = Math.max(table[i - weights[j]] + values[j], table[i]);
				}
			}
		}
		return table[W];
	}
	
	// if weight of knapsack is large but profit are small
	public static Integer zeroOneKnapsackProfitDP(Integer[] values, Integer[] weights, Integer W) {
		Integer P = 0;
		for(Integer e : values) P += e;
		Integer[][] table = new Integer[weights.length][P + 1];
		for(int row = 0; row < table.length; row++) {
			for(int col = 0; col < table[0].length; col++) {
				if(col == 0) table[row][col] = Integer.MAX_VALUE;
				else if(row == 0) {
					if(col == values[0]) table[row][col] = weights[0];
					else table[row][col] = Integer.MAX_VALUE;
				} else {
					if(values[row] > col) table[row][col] = table[row - 1][col];
					else {
						if(table[row - 1][col] != Integer.MAX_VALUE && table[row - 1][col - values[row]] != Integer.MAX_VALUE) {
							table[row][col] = Math.min(table[row - 1][col],
									table[row - 1][col - values[row]] + weights[row]);
						}
						else if(table[row - 1][col] != Integer.MAX_VALUE) table[row][col] = table[row - 1][col];
						else if(table[row - 1][col - values[row]] != Integer.MAX_VALUE) {
							table[row][col] = table[row - 1][col - values[row]] + weights[row];
						}
						else table[row][col] = Integer.MAX_VALUE;
						
						if(col == values[row]) table[row][col] = 
								Math.min(weights[row], table[row][col]);
					}
				}
			}
		}
		Integer index = table[0].length - 1;
		while(index >= 0 && table[table.length - 1][index] > W) index--;
		return index;
	}
	
	public static Integer zeroOneKnapsackProfitDPMin(Integer[] weights, Integer[] values, Integer W) {
		Integer P = 0;
		for(Integer e : values) P += e;
		Integer[] table = new Integer[P + 1];
		Arrays.fill(table, Integer.MAX_VALUE);
		table[values[0]] = weights[0];
		for(int row = 1; row < values.length; row++) {
			for(int col = table.length - 1; col >= 0; col--) {
				if(values[row] <= col) {
					if (table[col] != Integer.MAX_VALUE && table[col - values[row]] != Integer.MAX_VALUE) {
						table[col] = Math.min(table[col], weights[row] + table[col - values[row]]);
					}
					else if (table[col - values[row]] != Integer.MAX_VALUE) {
						table[col] = weights[row] + table[col - values[row]];
					}
					if(col == values[row]) table[col] = Math.min(weights[row], table[col]);
				}
			}
		}
		Integer index = table.length - 1;
		while(index >= 0 && table[index] > W) index--;
		return index;
	}
}
