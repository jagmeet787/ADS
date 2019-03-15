package QS.String;

//https://www.geeksforgeeks.org/find-one-extra-character-string/
public class FindExtraCharacter {
    public static char findExtra(String smaller, String larger) {
        int set = 0;
        for (int i = 0; i < smaller.length(); i++) set ^= (int) smaller.charAt(i);
        for (int i = 0; i < larger.length(); i++) set ^= (int) larger.charAt(i);
        return (char) set;
    }
    public static void main(String[] args) {
        String a = "abcdefa";
        String b = "abcdfe";
        System.out.println(findExtra(a, b));
    }
}
