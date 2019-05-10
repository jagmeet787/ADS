package QS.AA.DP;

import java.util.*;
import java.io.*;

// 11002 - Towards Zero
// https://uva.onlinejudge.org/external/110/11002.pdf

class Main {
	public static void main(String[] args) throws IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		String[] tokens = null;
		String input = null;

		while (true) {
			input = br.readLine();
			if (input == null || input.equals("") || input.equals("0")) break;

			int n = Integer.parseInt(input);

			ArrayList<ArrayList<HashMap<Integer, Integer>>> hm = 
				new ArrayList<ArrayList<HashMap<Integer, Integer>>>();

			for (int i = 0; i < 60; i++) {
				hm.add(new ArrayList<HashMap<Integer, Integer>>());
				for (int j = 0; j < 30; j++) {
					hm.get(i).add(new HashMap<Integer, Integer>());
				}
			}

			int[][] A = new int[60][30];

			for (int i = 0; i < (n << 1) - 1; i++) {
				tokens = br.readLine().trim().split("\\s");
				hm.add(new ArrayList<HashMap<Integer, Integer>>());
				for (int j = 0; j < n - Math.abs(n - i - 1); j++) {
					A[i][j] = Math.abs(Integer.parseInt(tokens[j]));
					hm.get(i).add(new HashMap<Integer, Integer>());
				}
			}

			hm.get(2 * n - 2).get(0).put(A[2 * n - 2][0], 1);
			
			for (int i = 2*n-2; i >= n; i--) {
				for (int j = 0; j < n - Math.abs(n-i-1); j++) {
					for (Integer e : hm.get(i).get(j).keySet()) {
						hm.get(i-1).get(j).put(Math.abs(e + A[i-1][j]), 1);
						hm.get(i-1).get(j).put(Math.abs(e - A[i-1][j]), 1);
						hm.get(i-1).get(j+1).put(Math.abs(e + A[i-1][j+1]), 1);
						hm.get(i-1).get(j+1).put(Math.abs(e - A[i-1][j+1]), 1);
					}
				}
			}
			
			for (int i = n-1; i >= 1; i--) {
				for (int j = 0; j < n - Math.abs(n-i-1); j++) {
					for (Integer e : hm.get(i).get(j).keySet()) {
						hm.get(i-1).get(j).put(Math.abs(e + A[i-1][j]), 1);
						hm.get(i-1).get(j).put(Math.abs(e - A[i-1][j]), 1);
						if (j > 0) {
							hm.get(i-1).get(j-1).put(Math.abs(e + A[i-1][j-1]), 1);
							hm.get(i-1).get(j-1).put(Math.abs(e - A[i-1][j-1]), 1);
						}

					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (Integer e : hm.get(0).get(0).keySet()) {
				if (e < min) {
					min = Math.abs(e);
				}
			}
			System.out.println(min);
		}
	}
}

class UVa11002 {
	public static void main(String[] args) throws IOException {
		Main.main(args);
	}
}