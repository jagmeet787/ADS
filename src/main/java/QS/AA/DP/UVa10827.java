package QS.AA.DP;

import java.util.*;
import java.io.*;

// 10827 - Maximum sum on a torus
// https://uva.onlinejudge.org/external/108/10827.pdf

class Main {
	
	public static void main(String[] args) throws java.io.IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String[] tokens = null;
		int tc = Integer.parseInt(br.readLine());
		int n = 0;
		int[][] torus = new int[400][400];
		while (tc-- > 0) {
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				tokens = br.readLine().trim().split("\\s");
				for (int j = 0; j < n; j++) {
					torus[i][j] = Integer.parseInt(tokens[j]);
					torus[i + n][j] = torus[i][j];
					torus[i][j + n] = torus[i][j];
					torus[i + n][j + n] = torus[i][j];
				}
			}
			out.append(solve(torus, n)).append('\n');
		}
		System.out.print(out);
	}

	public static int solve(int[][] torus, int n) {
		for (int i = 0; i < (n << 1); i++) {
			for (int j = 0; j < (n << 1); j++) {
				if (i > 0) torus[i][j] += torus[i-1][j];
				if (j > 0) torus[i][j] += torus[i][j-1];
				if (i > 0 && j > 0) torus[i][j] -= torus[i-1][j-1];
			}
		}
		int maxR = Integer.MIN_VALUE;
		int subR = maxR;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = i; k < n + i && k < (n << 1); k++) {
					for (int l = j; l < j + n && l < (n << 1); l++) {
						subR = torus[k][l];
						if (i > 0) subR -= torus[i-1][l];
						if (j > 0) subR -= torus[k][j-1];
						if (i > 0 && j > 0) subR += torus[i-1][j-1];
						maxR = Math.max(maxR, subR);		
					}
				}
			}
		}
		return maxR;
	}
}

class UVa10827 {
	public static void main(String[] args) throws java.io.IOException {
		Main.main(args);
	}
}