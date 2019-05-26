package ADS.Mathematics;

public class CatalanNumber {

    // (1/(n+1)) * (2nCn) = (2n * 2n - 1 * ... n + 1 / n * n-1 * .. * 1) / (n + 1) 
    public static long catalanNumber(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= (n + i);
            res /= i;
        }
        return res / (n + 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i <= n; i++) {
            System.out.println(i  + ": " + catalanNumber(i));
        }
    }
}
