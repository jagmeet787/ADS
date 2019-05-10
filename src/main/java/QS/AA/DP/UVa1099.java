package QS.AA.DP;

import java.util.*;
import java.io.*;

// 1099 - Sharing Chocolate
// https://uva.onlinejudge.org/external/10/1099.pdf
class UVa1099 {
	public static void main(String[] args) throws IOException {
		Main.main(args);
	}
}

class Main {
	
	public static final int MOD = 1000_000_000 + 7;
	public static final double EPS = 1e-6;
	public static final boolean DEBUG = true;

	public static int chk(int d, int m, int n, int[][] table, int[] sum) {
        int next = ((1 << n) - 1) ^ m;
        int d2 = sum[next] / d;
        if ((next & (next - 1)) == 0)
            return table[d][m] = 1;
        if (table[d][m] != -1)
            return table[d][m];
        int res = 0;
        for (int i = (next - 1) & next; i > (next ^ i) && res == 0; i = (i - 1)
                & next) {
            if (sum[i] % d == 0 && sum[i ^ next] % d == 0)
                res |= chk(d, m | i, n, table, sum) & chk(d, m | (i ^ next), n, table, sum);
            if (res == 0 && sum[i] % d2 == 0 && sum[i ^ next] % d2 == 0)
                res |= chk(sum[i] / d2, m | (i ^ next), n, table, sum)
                        & chk(sum[i ^ next] / d2, m | i, n, table, sum);
        }
        return table[d][m] = res;
    }

	public static String solvef(int n, int x, int y, int[] pieces, int[][] table) {
		int size = 1 << n;
		int[] sum = new int[size];
		sum[0] = 0;
	    for (int i = 0; i < n; i++) {
            sum[1 << i] = pieces[i];
        }
        for (int i = 1; i < size; i++) {
            int last = i & -i;
            sum[i] = sum[last] + sum[i ^ last];
        }
        for (int i = 0; i < table.length; i++)
            Arrays.fill(table[i], -1);
        if (chk(Math.min(x, y), 0, n, table, sum) == 1)
        	return "Yes";
        return "No";
	}	

	public static void main(String[] args) throws java.io.IOException {
		final java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		final StringBuilder out = new StringBuilder();

		String[] tokens = null;
		String input = null;
		
		int n, x, y;
		final int[] pieces = new int[20];
		final int[][] table = new int[110][1 << 15 + 100];
		int caseNo = 1;
		while (true) {
			input = br.readLine();
			if (input == null || input.equals("") || input.equals("0")) break;
			n = Integer.parseInt(input);
			tokens = br.readLine().trim().split("\\s");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
			if (x > y) {
				int temp = x; x = y; y = temp;
			}
			Arrays.fill(pieces, 0);
			for (int i = 0; i < table.length; i++) Arrays.fill(table[i], 0);
			tokens = br.readLine().trim().split("\\s");
			int sum = 0;
			for (int i = 0; i < n; i++) {
				pieces[i] = Integer.parseInt(tokens[i]);
				sum += pieces[i];
			}
			out.append("Case " + caseNo + ": ");
			if (sum != (x * y)) {
				out.append("No\n");
			} else {
				out.append(solvef(n, x, y, pieces, table)).append('\n');
			}
			caseNo++;
		}
		System.out.print(out);
	}
}