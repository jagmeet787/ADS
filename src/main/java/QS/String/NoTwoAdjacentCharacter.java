package QS.String;

import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
// https://practice.geeksforgeeks.org/problems/rearrange-characters/0
public class NoTwoAdjacentCharacter {

    public static String rearrangeString(String s) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Key> pq = new PriorityQueue<Key>();
        s = s.toLowerCase();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) pq.add(new Key((char)('a' + i), count[i]));
        }
        Key prev = new Key('#', -1);
        Key out;
        while(!pq.isEmpty()) {
            out = pq.peek();
            sb.append(out.ch);
            pq.poll();
            if (prev.count > 0) pq.add(prev);
            (out.count)--;
            prev = out;
        }
        if (sb.length() != s.length()) return "Not Possible";
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "aaaabbcc";
        System.out.println(rearrangeString(input));
    }
}

class Key implements Comparable<Key>{
    public char ch;
    public int count;

    public Key(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }

    @Override
    public int compareTo(Key k) {
        return k.count - this.count;
    }

    @Override
    public String toString() {
        return "[" + this.ch + " " + this.count + "]";
    }
}