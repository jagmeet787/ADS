package QS.AA.Geometry;

import java.util.*;

// C. Points on Plane
// https://codeforces.com/problemset/problem/576/C
public class CF_576C {
	public static void main(String[] args) throws java.io.IOException {
		
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		
		StringBuilder out = new StringBuilder();
		
		int num = Integer.parseInt(br.readLine());
		
		Point[] points = new Point[num];
		
		String[] tokens = null;
		int x = 0;
		int y = 0;

		for (int i = 0; i < num; i++) {
			tokens = br.readLine().trim().split("\\s");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
			x /= 1000;
			points[i] = new Point(x, y, (i+1));
		}
		
		Arrays.parallelSort(points, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.x == p2.x) {
					if ((p1.x & 1) != 0) {
						return p2.y - p1.y;
					} else {
						return p1.y - p2.y;
					}
				}
				return p1.x - p2.x;
			}
		});
		
		for (int i = 0; i < num; i++) {
			out.append(points[i].idx).append(' ');
		}

		System.out.println(out);
	}	
}

class Point {
	public int x, y, idx;
	public Point(int _x, int _y, int _idx){
		x = _x; y = _y; idx = _idx;
	}
}
