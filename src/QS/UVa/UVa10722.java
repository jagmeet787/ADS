package QS.UVa;

import java.util.Scanner;
import java.math.BigInteger;

// 10722 Super Lucky Numbers
// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1663
// Submission number 22876629
// class test 1 AA

public class UVa10722 {
//    class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int B = 0, N = 0;
        BigInteger[][] table = new BigInteger[2][2];
        while(true) {
            B = sc.nextInt();
            N = sc.nextInt();
            if ( B == 0 && N == 0) break;
            if ( B < 4 || B  > 128 || N < 0 || N > 100 ) continue;
            if ( N == 1 ) {System.out.println(B-1); continue;}
            table[0][1] = BigInteger.ONE; //numbers possible other than starting from 1
            table[1][1] = BigInteger.ONE; // numbers possible starting from 1
            for ( int i = 2; i < N; i++ ) {
                table[0][i%2] = (BigInteger.valueOf((B - 1)).multiply(table[0][(i-1)%2])).add(table[1][(i-1)%2]);
                table[1][i%2] = (BigInteger.valueOf((B - 2)).multiply(table[0][(i-1)%2])).add(table[1][(i-1)%2]); // excluding 3
            }
            table[0][N%2] = BigInteger.valueOf(B - 2).multiply(( BigInteger.valueOf(B - 1).multiply(table[0][(N-1)%2]).add(table[1][(N-1)%2]))); // not including 0
            table[1][N%2] = BigInteger.valueOf(B - 2).multiply(table[0][(N-1)%2]).add(table[1][(N-1)%2]);
            System.out.println( (table[0][N%2].add(table[1][N%2])));
        }
    }
//    }
}
