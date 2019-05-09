package QS.AA.NetworkFlow;

// F. Anti-Palindromize
// https://codeforces.com/contest/884/problem/F

import java.io.*;
import java.util.*;
public class CF_884F {
	
	public static final int MOD = 1000_000_000 + 7;
	public static final double EPS = 1e-6;
	public static final boolean DEBUG = true;

	public static void main(String[] args) throws java.io.IOException {
		final java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		char[] s = br.readLine().trim().toCharArray();
		String[] tokens = br.readLine().trim().split("\\s");
		int[] beauty = new int[n];
		
		int ans = 0;
		int m = 0, x = 0, t = 0;
		int[] b = new int[101];
		int[] count = new int[26];

		for (int i = 0; i < n; i++) {
			beauty[i] = Integer.parseInt(tokens[i]);
			ans += beauty[i];
		}

		for (int i = 0; i < n / 2; i++) {
			if (s[i] == s[n-i-1]) {
				t++;
				count[(int)(s[i] - 'a')]++;
				ans -= Math.min(beauty[i], beauty[n-i-1]);
			}
		}
		
		for (int i = 0; i < 26 ; i++) {
			if (m < count[i]) {
				m = count[i];
				x = i;
			}
		}

		if (2 * m <= t) {
			System.out.println(ans);
			return;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < n/2; i++) {
			if ((int)(s[i] - 'a') != x && (int)(s[n-i-1] - 'a') != x && s[i] != s[n-i-1]) {
				list.add(Math.min(beauty[i], beauty[n-i-1]));
			}
		}

		Collections.sort(list);
		for (int i = 0; i < 2 * m - t; i++) {
			ans -= list.get(i);
		}
		System.out.print(ans);
	}
}
