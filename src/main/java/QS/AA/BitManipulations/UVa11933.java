package QS.AA.BitManipulations;

//11933 - Splitting Numbers

public class UVa11933 {
// class Main {
    public static void main(String[] args) throws java.io.IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int n = 1, a = 0, b = 0, bit = 1, i = 0;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            i = a = b = 0;
            bit = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    if (bit == 1) a = a | (1 << i);
                    else b = b | (1 << i);
                    bit = 1 - bit;
                }
                i++;
                n = n >> 1;
            }
            System.out.println(a + " " + b);
        }
    }
}
