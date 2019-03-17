package ADS.String;

public class NaivePatternMatching {

    // Time O(n * m) and Space O(1)
    public static int find(String text, String pattern) {
        if (pattern.length() == 0) return 0;
        if (text.length() == 0 && pattern.length() > 0) return -1;
        if (text.length() < pattern.length())
            throw new IllegalArgumentException("Text size less than pattern");
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            for (; j < pattern.length() && text.charAt(i + j) == pattern.charAt(j); j++);
            if (j == pattern.length()) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(find("abcdefghi", "i"));
    }
}
