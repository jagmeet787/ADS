package ADS.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    public static int[] computeLPS(final String pattern) {
        if (pattern.length() < 2) throw new IllegalArgumentException();
        final int[] lps = new int[pattern.length()];
        int i = 1;
        int len = 0;
        while(i < pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = len + 1;
                i++; len++;
            } else {
                if (len > 0) {
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static List<Integer> kmp(final String text, final String pattern) {
        final int[] lps = computeLPS(pattern);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
            }
            if ( j == pattern.length() ) {
                indexes.add(i - j);
                j = lps[j-1];
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) j = lps[j - 1];
                else i++;
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        final String text = "ABCDABCXABXABCDAB";
        final String pattern = "ABCDAB";
        System.out.println(kmp(text, pattern));
    }
}
