package QS.O;

import ADS.Mathematics.Permutaion;
import Utility.Utility;

import java.util.Arrays;

public class AssignmentProblem {

    private static void checkConstraint(int[][] cost) {
        if (cost.length != cost[0].length)
            throw new IllegalArgumentException("cost is not N * N");
    }

    public static int activitySelectionBruteFroce(final int[][] cost) {
        checkConstraint(cost);

        final int n = cost.length;
        final int FACTORIAL = Utility.factorial(n);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        int sum = Integer.MAX_VALUE, curSum = 0;
        for (int i = 0; i < FACTORIAL; i++) {
            curSum = 0;
            for (int j = 0; j < n; j++) {
                curSum += cost[j][arr[j]];
            }
            if (curSum < sum)
                sum = curSum;
            arr = Permutaion.nextPermutation(arr);
        }
        return sum;
    }

    public static void main(String[] args) {
        final int[][] cost = {{1,2,3},{3,4,5},{3,2,11}};
        System.out.println(activitySelectionBruteFroce(cost));
    }
}
