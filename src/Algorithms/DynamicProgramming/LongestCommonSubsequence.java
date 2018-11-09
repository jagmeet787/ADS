package Algorithms.DynamicProgramming;

public class LongestCommonSubsequence {
	
	// Recursive implementation
	// time : O(2^(Min(m,n)), space : ?
	public static int _longestCommonSusequenceRec(String a, int n, String b, int m) {
		if(n == 0 || m == 0) return 0;
		if(a.charAt(n-1) == b.charAt(m-1)) 
			return 1 + _longestCommonSusequenceRec(a, n-1, b, m-1);
		return Math.max(_longestCommonSusequenceRec(a, n-1, b, m), 
				_longestCommonSusequenceRec(a, n, b, m-1));
	}
	
	public static int longestCommonSubsequenceRec(String a, String b) {
		return _longestCommonSusequenceRec(a, a.length(), b, b.length());
	}
	
	public static String getLCS(int[][] lcs, String a, String b) {
		int len = lcs[a.length()][b.length()];
		char[] subsequence = new char[len];
		
		int i = lcs.length - 1, j = lcs[0].length - 1;
		
		while(i > 0 && j > 0) {
			if(a.charAt(i-1) == b.charAt(j-1)) {
				subsequence[--len] = a.charAt(i-1);
				i--; j--;
			}
			else if(lcs[i][j] == lcs[i-1][j]) i--;
			else j--;
		}
		
		return String.valueOf(subsequence);
	}
	
	public static int longestCommonSubsequenceDP(String a, String b) {
		if(a.length() == 0 || b.length() == 0) return 0;
		// By default all entries are 0
		int[][] lcs = new int[a.length() + 1][b.length() + 1];
		for(int i = 1; i < lcs.length; i++) {
			for(int j = 1; j < lcs[0].length; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) lcs[i][j] = 1 + lcs[i-1][j-1];
				else lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}

		// Backtracking
		System.out.println("LCS: " + getLCS(lcs, a, b));
		
		return lcs[a.length()][b.length()];
	}
	
	// Space optimized O(Min(m, n)) time is still same
	public static int longestCommonSubsequenceDPMin(String a, String b) {
		if(a.length() == 0 || b.length() == 0) return 0;

		//	find the min length string so we can use less memory
		String minString = a;
		if(a.length() < b.length()) minString = a;
		else {
			minString = b;
			b = a;
			a = minString;
		}
		
		int[][] lcs = new int[2][a.length() + 1];
		for(int i = 0; i < b.length(); i++) {
			for(int j = 1; j < lcs[0].length; j++) {
				if(a.charAt(j-1) == b.charAt(i)) lcs[(i+1)%2][j] = 1 + lcs[(i)%2][j - 1];
				else lcs[(i+1)%2][j] = Math.max(lcs[(i)%2][j], lcs[(i+1)%2][j-1]);
			}
		}

		return lcs[b.length()%2][a.length()];
	}
	
	public static void main(String[] args) {
		String a = "GXTXAYB";
		String b = "AGGTAB";
		System.out.println("LCS: " + longestCommonSubsequenceRec(a, b));
		System.out.println("LCS: " + longestCommonSubsequenceDP(a, b));
		System.out.println("LCS: " + longestCommonSubsequenceDPMin(a, b));
	}
}
