package QS.AA.BitManipulations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//11173 - Grey Codes
//https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2114
// Submission number 22876639
public class UVa1173 {

    // returns the integer at location in the squence starting from 0 to 2^(numberOfBits)
    // it is basically binary to gray conversion
    private static int intAtLoc(int loc) {
        return loc ^ (loc >> 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc, k;
        tc = Integer.parseInt(br.readLine());
        String line = "";
        String[] toks;
        while (tc-- > 0) {
            k = Integer.parseInt(br.readLine().split(" ")[1]);
            System.out.println(intAtLoc(k));
        }
    }
}
