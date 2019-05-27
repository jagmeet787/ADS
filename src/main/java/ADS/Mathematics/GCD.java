package ADS.Mathematics;

class GCD {
	// Time complexity : O(log(a + b))
	public static long gcd(long a, long b) {
		if (b == 0) return (a < 0) ? -a : a;
		return gcd(b, a % b);
	}
}