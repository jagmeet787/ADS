package QS.O.String;

class LongestPalindrome {

	private static int evenPalindrome(String s, int idx) {
        int i = idx;
        int j = idx + 1;
        int count = 0;
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count += 2;
            i--;
            j++;
        }
        return count;
    }
    
    private static int oddPalindrome(String s, int idx) {
        int i = idx - 1;
        int j = idx + 1;
        int count = 1;
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count += 2;
            i--;
            j++;
        }
        return count;
    }

    // O(n^2) solution to find the longest palindrome in string
    public static String longestPalindrome(String A) {
        int max = 0;
        int l = 0;
        int r = 0;
        for(int i = 0; i < A.length(); i++) {
            int e = evenPalindrome(A, i);
            int o = oddPalindrome(A, i);
            if(e > max) {
                l = i - e/2 + 1;
                r = i + e/2;
                max = e;
            }
            if(o > max) {
                l = i - (o-1)/2;
                r = i + (o-1)/2;
                max = o;
            }
        }
        return A.substring(l, r+1);
    }
}