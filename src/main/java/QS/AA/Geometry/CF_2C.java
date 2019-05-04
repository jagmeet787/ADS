package QS.AA.Geometry;

// C. Commentator problem
// https://codeforces.com/problemset/problem/2/C
public class CF_2C {
	static double difference(double x,double y, Circle[] circles) {
	    double[] difference = new double[3];
	    for(int i = 0; i < 3; i++) {
	    	difference[i] = Math.hypot(Math.abs(x - circles[i].x), Math.abs(y - circles[i].y)) / circles[i].r;
	    }
	    double res = 0;
	    for(int i = 0; i < 3; i++)
	        res += (difference[i] - difference[(i + 1) % 3]) * (difference[i] - difference[(i + 1) % 3]);
	    return res;
	}

	public static void main(String[] args) throws java.io.IOException {
		int[][] move ={{1,0},{-1,0},{0,1},{0,-1}};
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		Circle[] circles = new Circle[3];
		String[] tokens = null;
		double ix = 0.0;
		double iy = 0.0;
		double ir = 0.0;
		for (int i = 0; i < 3; i++) {
			tokens = br.readLine().trim().split("\\s");
			ix = Double.parseDouble(tokens[0]);
			iy = Double.parseDouble(tokens[1]);
			ir = Double.parseDouble(tokens[2]); 
			circles[i] = new Circle(ix, iy, ir);
		}

		// calculate the average coordinates of three circles for intial
		// value of the center of the circle where point can possiby lie
		double x = (circles[0].x + circles[1].x + circles[2].x) / 3.0;
		double y = (circles[0].y + circles[1].y + circles[2].y) / 3.0;
		// size of steps to take for moving the circle around
		double step = 2.0;
		// for precision
		final double EPS = 1e-6;
		// repeat until step is greater than the precision
		while (step > EPS) {
			// calculate the difference in the arcsin of theta of each angle from the 
			// point
			double prev = difference(x, y, circles);
			int idx = -1;
			// move the center of the circle left, right, up and down to compute the 
		 	// difference of arcsin
			for (int i = 0; i < 4; i++) {
				double cur = difference(x + move[i][0] * step, y + move[i][1] * step, circles);
				// if current difference is smaller than previous update it along with index
				if (cur < prev) {
					prev = cur;
					idx = i;
				}
			}
			if (idx == -1) step /= 2.0;
			else {
				// update center of circle
				x += move[idx][0] * step;
				y += move[idx][1] * step;
			}
		}
		if (difference(x, y, circles) < EPS) {
			System.out.println(x + " " + y);
		}
	}
}

class Circle {
	public double x, y, r;
	public Circle(double _x, double _y, double _r) {
		x = _x; y = _y; r = _r;
	}
	@Override
	public String toString() {
		return "(" + x + " " + y + " " + r + ")";
	}
}