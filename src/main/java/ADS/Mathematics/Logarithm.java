// package ADS.Mathematics;

class Logarithm {
	
	// Compute log upto n numbers
	// (int)Math.log(k)
	// use library function instead
	public static int[] logarithm(int n, int base) {
		int[] log = new int[n+1];
		log[0] = Integer.MAX_VALUE;
		int currentLog = 0;
		long nextPow = base;
		for (int i = 1; i <= n; i++) {
			if (i == nextPow) {
				currentLog++;
				nextPow *= base;
			}
			log[i] = currentLog;
		}
		return log;
	}

	public static double log2(int num) {
		return logb(num, 2);
	}

	public static double logb(int num, int base) {
		return Math.log(num) / Math.log(base);
	}

	public static void main(String[] args) {
		// javac Logarithm.java && java -ea Logarithm
		int[] arr = logarithm(65536, 2);
		for (int i = 1; i <= 65536; i++) {
			int comp = (int)(Math.log(i) / Math.log(2));
			assert arr[i] ==  comp : ("" + arr[i] + " != " + comp);
		}
		System.out.println(java.util.Arrays.toString(logarithm(18, 2)));
		System.out.println((int)logb(18, 2));
		System.out.println(log2(19));
	}
}