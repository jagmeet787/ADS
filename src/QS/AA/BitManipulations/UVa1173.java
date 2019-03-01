package QS.AA.BitManipulations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa1173 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long tc, n, k;
        tc = Integer.parseInt(br.readLine());
        String line = "";
        String[] toks;
        while (tc-- > 0) {
            toks = br.readLine().split(" ");
            n = Integer.parseInt(toks[0]);
            k = Integer.parseInt(toks[1]);
            System.out.println(k ^ (k >> 1));
        }
    }
}
