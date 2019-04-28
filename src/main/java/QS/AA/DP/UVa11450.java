package QS.AA.DP;

// 11450 - Wedding Shopping
// https://uva.onlinejudge.org/external/114/11450.pdf
public class UVa11450 {
// class Main {
	public static void main(String[] args) throws java.io.IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		int M = 0, C = 0;
		String[] tokens = null;
		int[][] price = new int[21][21];
		while (tc-- > 0) {
			tokens = br.readLine().trim().split("\\s");
			M = Integer.parseInt(tokens[0]);
			C = Integer.parseInt(tokens[1]);
			// get price of all items
			for (int i = 0; i < C; i++) {
				tokens = br.readLine().trim().split("\\s");
				price[i][0] = Integer.parseInt(tokens[0]);; // K
				for (int j = 1; j <= price[i][0]; j++) {
					price[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			out.append(solve(M, C, price)).append('\n');
		}
		System.out.println(out);
	}

	public static String solve(int M, int C, int[][] price) {
		boolean[][] dp = new boolean[C][M+1];
		// base case
		for (int i = 1; i <= price[0][0]; i++) {
			if (price[0][i] <= M)
				dp[0][price[0][i]] = true;
		}

		for (int c = 1; c < C; c++) {
			for (int k = 1; k <= price[c][0]; k++) {
				for (int m = 0; m + price[c][k] <= M; m++) {
					if (dp[c-1][m]) {
						dp[c][m + price[c][k]] = true;
					}
				}
			}
		}
		int i = M;
		for (; i >= 0 && !dp[C-1][i]; i--);
		if (i < 0) return "no solution";
		return "" + i;
	}
}