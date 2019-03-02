package QS.Array;

public class Kadane {
    public static int kadane(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int anArr : arr) {
            currentSum += anArr;
            if (currentSum > maxSoFar) maxSoFar = currentSum;
            if (currentSum < 0) currentSum = 0;
        }
        return maxSoFar;
    }
}
