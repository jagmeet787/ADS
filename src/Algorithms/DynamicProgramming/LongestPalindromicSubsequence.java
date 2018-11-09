package Algorithms.DynamicProgramming;


public class LongestPalindromicSubsequence {
	
	private static int _longestPalindromicSubsequenceRec(String a, int start, int end) {
		if(start > end) return 0;
		
		if(end - start + 1 == 1) return 1;
		
		if(a.charAt(start) == a.charAt(end))
			return 2 + _longestPalindromicSubsequenceRec(a, start + 1, end - 1);
		else {
			return Math.max(_longestPalindromicSubsequenceRec(a, start + 1, end)
					, _longestPalindromicSubsequenceRec(a, start, end - 1));
		}
	}
	
	public static int longestPalindromicSubsequenceRec(String a) {
		if(a.length() < 2) return a.length();
		
		return _longestPalindromicSubsequenceRec(a, 0, a.length() - 1);
	}
	
	private static String getLPS(int[][] lps, String a) {
		int i = 0, j = a.length() - 1;
		char[] LPS = new char[lps[0][a.length() - 1]];
		int li = 0;
		while(i < a.length() && j >= 0 && i <= j) {
			if(a.charAt(i) == a.charAt(j)) {
				LPS[li] = a.charAt(i);
				LPS[LPS.length - li - 1] = LPS[li];
				i++; j--; li++;
			} else if(lps[i][j] == lps[i+1][j]) i++;
			else j--;
		}
		return String.valueOf(LPS);
	}
	
	public static int longestPalindromicSubsequenceDP(String a) {
		if(a.length() < 2) return a.length();
		
		int[][] lps = new int[a.length()][a.length()];
		
		for(int i = 0; i < a.length(); i++) lps[i][i] = 1;
		
		for(int i = a.length() - 2; i >= 0; i--) {
			for(int j = i + 1; j < a.length(); j++) {
				if(a.charAt(i) == a.charAt(j))
					lps[i][j] = 2 + lps[i + 1][j - 1];
				else
					lps[i][j] = Math.max(lps[i + 1][j], lps[i][j - 1]);
			}
		}
		
		// Backtrack
		System.out.println("LPS : " + getLPS(lps, a));

		return lps[0][a.length() - 1];
	}
	
	public static int longestPalindromicSubsequenceDPMin(String a) {
		if(a.length() < 2) return a.length();
		
		int[][] lps = new int[2][a.length()];
		
		for(int i = a.length() - 1; i >=0; i--) {
			lps[i % 2][i] = 1;
			for(int j = i + 1; j < a.length(); j++) {
				if(a.charAt(i) == a.charAt(j))
					lps[i % 2][j] = 2 + lps[(i + 1) % 2][j - 1];
				else
					lps[i % 2][j] = Math.max(lps[(i + 1) % 2][j], lps[i % 2][j - 1]);
			}
		}
		return lps[(a.length() - 1) % 2][a.length() - 1];
	}
	
	// Reverse the string and do lcs
	public static Integer longestPalindromicSubsequenceUsingLCS(String a) {
		StringBuilder sb = new StringBuilder(a);
		sb = sb.reverse();
		return LongestCommonSubsequence.longestCommonSubsequenceDPMin(a, sb.toString());
	}
}
