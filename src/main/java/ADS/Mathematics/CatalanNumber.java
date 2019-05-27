package ADS.Mathematics;

public class CatalanNumber {

    // Recursive implementation
    // Time Complexity T(n) = Sum(i = 0 to n-1) [T(i) * T(n - i - 1)]
    // catalan(n) = Sum(i = 0 to n - 1) [catalan(i) * catalan(n - i - 1)]
    // https://wikimedia.org/api/rest_v1/media/math/render/svg/5c513933c476c27b9c623605771cacedd13926e0
    public static long catalanRec(int n) {
        if (n <= 1) return 1;
        long res = 0;
        for(int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - i - 1);
        }
        return res;
    }

    // Using Dynamic Programming
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static long catalanDP(int n) {
        if (n <= 1) return 1;
        long[] catalan = new long[n+1];
        catalan[0] = catalan[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                catalan[i] += (catalan[j] * catalan[i - j - 1]);
            }
        }
        return catalan[n];
    }

    // Using binomial Coefficient C(n) = (1 / (n+1)) ((2n)!/(n! * n!))
    // Time Complexity: O(n)
    // (1/(n+1)) * (2nCn) = ( (2n * 2n - 1 * ... n + 1) / (n * n-1 * .. * 1) ) / (n + 1) 
    public static long catalan(int n) {
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
            System.out.println(i  + ": " + catalan(i) + " " + catalanRec(i) + " " + catalanDP(i));
        }
    }
}
