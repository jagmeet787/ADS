package QS.AA.DP;

import java.util.*;
import java.io.*;

// 11472 - Beautiful Numbers
// https://uva.onlinejudge.org/external/114/11472.pdf

class Main {

    public static final int MOD = 1000_000_000 + 7;
    public static final double EPS = 1e-6;
    public static final boolean DEBUG = false;

    public static int solve(int[][][] table, int N, int M) {
        int result = 0;
        int size = (1 << N);
        for (int i = 1; i < N; i++) {
            table[1][1 << i][i] = 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < N; k++) {
                    if ( k > 0) {
                        table[i + 1][j | (1 << (k - 1))][k - 1] = 
                                            (
                                                table[i + 1][j | (1 << (k - 1))][k - 1] + 
                                                table[i][j][k]
                                            ) % MOD;
                    }
                    if (k != N - 1) {
                        table[i + 1][j | (1 << (k + 1))][k + 1] = 
                                            (
                                                table[i + 1][j | (1 << (k + 1))][k + 1] + 
                                                table[i][j][k]
                                            ) % MOD;
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                result = ( result + table[i][size - 1][j]) % MOD;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder out = new StringBuilder("");
        final int[][][] table = new int[150][1200][15];
        int N, M;
        int tc = Integer.parseInt(br.readLine());
        String[] tokens;
        while(tc-- > 0) {
            tokens = br.readLine().trim().split("\\s");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);
            if (N > M) {
                out.append(0).append('\n');
                continue; 
            }
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    Arrays.fill(table[i][j], 0);
                }
            }
            out.append(solve(table, N, M)).append('\n');
        }
        System.out.print(out);
    }
}

class UVa11472 {

    public static void main(String[] args) throws IOException {
        Main.main(args);
    }
}
