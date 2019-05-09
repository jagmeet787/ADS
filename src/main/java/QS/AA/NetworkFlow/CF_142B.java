package QS.AA.NetworkFlow;

// B. Help General
// https://codeforces.com/problemset/problem/142/B
import java.util.*;
import java.io.*;

public class CF_142B {
	public static void main(String[] args) throws IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tokens = br.readLine().trim().split("\\s");
		int n = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		if (n > m) {int temp = n; n = m; m = temp;}
		int ans = 0;
		if (n == 1 || m == 1) {
			ans = m * n;
		} else if (n == 2 && m >= 2) {
			ans = m - (m % 4) + Math.min(m % 4, 2) * 2;
		} else {
			ans = (m * n + 1) / 2;
		}
		System.out.println(ans);
	}
}