package ADS.Mathematics;

class GCD {
	// Time complexity : O(log(a + b))
	public static long gcd_rec(long a, long b) {
		if (b == 0) return (a < 0) ? -a : a;
		return gcd(b, a % b);
	}

	public static long gcd(long a, long b) {
		long temp = 0;
		while (b > 0) {
			temp = b;
			b = a % b;
			a = temp;
		}
		return (a < 0) ? -a : a;
	}
}