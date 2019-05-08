package QS.AA.DP;

import java.io.*;
import java.util.*;

// 10898 - Combo Deal
// https://uva.onlinejudge.org/external/108/10898.pdf

class Main {
	public static void main(String[] args) throws java.io.IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		String[] tokens = null;
		String input = null;

		while (true) {
			input = br.readLine();
			if (input == null || input.equals("")) break;
			tokens = input.trim().split("\\s");
			int I = Integer.parseInt(tokens[0]);
			int[] items = new int[I];
			
			for (int i = 1; i <= I; i++)
				items[i-1] = Integer.parseInt(tokens[i]);

			int C = Integer.parseInt(br.readLine()); // number of combos
			
			int[][] combos = new int[C][I+1];

			for (int i = 0; i < C; i++) {
				tokens = br.readLine().trim().split("\\s");
				for (int j = 0; j <= I; j++)
					combos[i][j] = Integer.parseInt(tokens[j]);
			}
			
			int tc = Integer.parseInt(br.readLine()); // number of orders
			int[] orders = new int[6];
			while (tc-- > 0) {
				tokens = br.readLine().trim().split("\\s");
				for (int i = 0; i < I; i++)
					orders[i] = Integer.parseInt(tokens[i]);
				
				out.append(solve(items, combos, orders)).append('\n');
			}
		}
		System.out.print(out);
	}

	public static int solve(int[] items, int[][] combos, int[] orders) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < combos.length; i++) {
			boolean flag = true;
			for (int j = 0; j < items.length; j++) {
				if (orders[j] < combos[i][j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				for (int j = 0; j < items.length; j++) {
					orders[j] -= combos[i][j];
				}
				result = Math.min(result, combos[i][combos[i].length - 1] + solve(items, combos, orders));
				for (int j = 0; j < items.length; j++) {
					orders[j] += combos[i][j];
				}
			}
		}
		if (result == Integer.MAX_VALUE) {
			result = 0;
			for (int i = 0; i < items.length; i++) {
				result += orders[i] * items[i];
			}
		}

		return result;
	}

}

class UVa10898 {
	public static void main(String[] args) throws IOException {
		Main.main(args);
	}
}