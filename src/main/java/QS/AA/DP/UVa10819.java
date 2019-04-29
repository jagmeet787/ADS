package QS.AA.DP;

// 10819 - Trouble of 13-Dots
// https://uva.onlinejudge.org/external/108/10819.pdf
public class UVa10819 {
// class Main {
	public static void main(String[] args) throws java.io.IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String[] tokens = null;
		String input = null;
		int[] price = new int[100];
		int[] favours = new int[100];
		while (true) {
			input = br.readLine();
			if (input == null || input.equals("")) break;
			tokens = input.trim().split("\\s");
			int m = Integer.parseInt(tokens[0]);
			int n = Integer.parseInt(tokens[1]);
			for (int i = 0; i < n; i++) {
				tokens = br.readLine().trim().split("\\s");
				price[i] = Integer.parseInt(tokens[0]);
				favours[i] = Integer.parseInt(tokens[1]);
			}
			out.append(solve(m, n, price, favours)).append('\n');
		}
		System.out.print(out);
	}

	public static int solve(int m, int n, int[] price, int[] favours) {
		boolean flag = false;
		int M = m;
		if (m > 1800) m += 200;
		int[] dp = new int[m+1];

		for (int i = 0; i < n; i++) {
			for (int j = m; j >= price[i]; j--) {
				if (dp[j - price[i]] > 0 || j == price[i])
					dp[j] = Math.max(dp[j - price[i]] + favours[i], dp[j]);
			}
		}

		int maxF = Integer.MIN_VALUE;
        if (M >= 1801 && M <= 2000) {
        	for (int i = 0; i < M; i++)
        		maxF = Math.max(maxF, dp[i]);
            for (int j = 2001; j <= m; j++)
                maxF = Math.max(maxF, dp[j]);
        }
        else
        	for (int i = 0; i <= m; i++)
        		maxF = Math.max(maxF, dp[i]);
		return maxF;
	}
}