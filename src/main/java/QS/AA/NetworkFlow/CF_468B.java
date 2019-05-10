package QS.AA.NetworkFlow;

// B. Two Sets
// https://codeforces.com/problemset/problem/468/B

import java.util.*;
import java.io.*;

public class CF_468B {
	
	public static final int MOD = 1000_000_000 + 7;
	public static final double EPS = 1e-6;
	public static final boolean DEBUG = true;

	static HashMap<Integer, Integer> m;
	static ArrayList<ArrayList<Pair>> g;
	static int n, a, b, cnt;
	static int[] p, res, deg, v;

	public static void init(int n) {
		m = new HashMap<Integer, Integer>();

		g = new ArrayList<ArrayList<Pair>>();
		for (int i = 0; i < n; i++) g.add(new ArrayList<Pair>());	
		
		p = new int[n];
		deg = new int[n];
		res = new int[n];
		v = new int[n];

		cnt = 0;
	}

	public static void dfs(int cur, int prev) {
		for(Pair pr : g.get(cur)) {
			if (pr.c == prev) continue;
			res[cur] = res[pr.c] = pr.set;
			cnt += 2;
			dfs(pr.c, cur);
			return;
		}
	}
	public static String solve(int n, int a, int b, String[] tokens) {			
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(tokens[i]);
			m.put(p[i], i);
			res[i] = -1;
		}
	
		for (int i = 0; i < n; i++) {
			if (m.containsKey(a - p[i])) {
				g.get(i).add(new Pair(m.get(a - p[i]), 0));
				deg[i]++;
			}
			if (m.containsKey(b - p[i])) {
				g.get(i).add(new Pair(m.get(b - p[i]), 1));
				deg[i]++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 0; i < n; i++) {
			if (deg[i] == 0) return "NO";
			if (deg[i] == 1) q.add(i);
		}

		int cur = 0, next = 0;

		while (!q.isEmpty()) {
			cur = q.poll();
			if (v[cur] == 1) continue;
			int color = 0;
			next = -1;
			for (Pair pr : g.get(cur)) {
				if (v[pr.c] == 0) {
					next = pr.c;
					color = pr.set;
				}
			}
			if (next != -1) {
				if (next == cur) {
					res[cur] = color;
					v[cur] = 1;
					deg[cur]--;
					cnt++;
				} else {
					res[cur] = res[next] = color;
					v[cur] = v[next] = 1;
					deg[next]--;
					deg[cur]--;
					cnt += 2;
				}
				for (Pair pr : g.get(next)) {
					deg[pr.c]--;
					if (v[pr.c] == 0 && deg[pr.c] == 1) {
						q.add(pr.c);
					}
				}
			} else {
				return "NO";
			}
		}

		for (int i = 0; i < n; i++) {
			if (res[i] == -1) dfs(i, -1);
		}

		if (cnt < n) return "NO";

		final StringBuilder out = new StringBuilder();
		out.append("YES\n");
		for (int i = 0; i < n; i++) {
			out.append(res[i]).append(" ");
		}
		return out.toString();
	}

	public static void main(String[] args) throws java.io.IOException {
		final java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tokens = br.readLine().trim().split("\\s");
		n = Integer.parseInt(tokens[0]);
		a = Integer.parseInt(tokens[1]);
		b = Integer.parseInt(tokens[2]);
		init(n);
		tokens = br.readLine().trim().split("\\s");
		System.out.print(solve(n, a, b, tokens));
	}

	static class Pair {
		int c, set;
		public Pair(int _c, int _set) {
			this.c = _c; this.set = _set;
		}
		@Override
		public String toString() {
			return "(" + c + " " + set + ")";
		} 
	}
}