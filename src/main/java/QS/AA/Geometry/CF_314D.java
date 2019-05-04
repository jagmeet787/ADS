package QS.AA.Geometry;
import java.io.*;
import java.util.*;
// D. Sereja and Straight Lines
// https://codeforces.com/problemset/problem/314/D
public class CF_314D {
	public static void main(String[] args) throws java.io.IOException {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		long[] minYLeft = new long[N], maxYLeft = new long[N], minYRight = new long[N], maxYRight = new long[N];
		Point[] points = new Point[N];
		String[] tokens = null;
		String input = null;
		long x = 0;
		long y = 0;
		for (int i = 0; i < N; i++) {
			tokens = br.readLine().trim().split("\\s");
			x = Long.parseLong(tokens[0]);
			y = Long.parseLong(tokens[1]);
			points[i] = new Point(x - y, x + y);
		}
		Arrays.parallelSort(points, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
				return (int)(p1.x - p2.x);
			}
		});
		minYLeft[0] = points[0].y;
		maxYLeft[0] = points[0].y;
		for (int i = 1; i < N; i++) {
			minYLeft[i] = Math.min(minYLeft[i-1], points[i].y);
			maxYLeft[i] = Math.max(maxYLeft[i-1], points[i].y);
		}
		minYRight[N-1] = points[N-1].y;
		maxYRight[N-1] = points[N-1].y;
		for (int i = N-2; i >= 0; i--) {
			minYRight[i] = Math.min(minYRight[i+1], points[i].y);
			maxYRight[i] = Math.max(maxYRight[i+1], points[i].y);
		}

		long left = 0, right = Long.MAX_VALUE, mid = 0;

		while (left < right) {
			mid = (left + right) / 2;
			int j = 0;
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				while (j < N - 1 && points[j+1].x - points[i].x <= mid)
					j++;
				if (i == 0 && j == N - 1) flag = true;
				long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
				if (i > 0) {
					minY = Math.min(minY, minYLeft[i-1]);
					maxY = Math.max(maxY, maxYLeft[i-1]);
				}
				if (j < N - 1) {
					minY = Math.min(minY, minYRight[j+1]);
					maxY = Math.max(maxY, maxYRight[j+1]);
				}
				if (maxY - minY <= mid) flag = true;
				if (flag) break;
			}
			if (flag) right = mid;
			else left = mid + 1;
		}
		System.out.printf("%.8f\n", (left / 2.0));
	}
}

class Point {
	public long x, y;
	public Point(long _x, long _y) {
		x = _x; y = _y;
	}
	@Override
	public String toString() {
		return "(" + x + " " + y + ")";
	}
}