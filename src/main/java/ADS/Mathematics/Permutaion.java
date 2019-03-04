package ADS.Mathematics;

import Utility.Utility;

import javax.rmi.CORBA.Util;
import java.util.Arrays;
import java.util.Comparator;

public class Permutaion {

    public static int[] nextPermutation(int[] arr) {
        final int n = arr.length;
        int i = n - 1;
        for (; i > 0 ; i--) {
            if (arr[i] > arr[i - 1]) break;
        }
        if (i == 0) return null;
        int number = arr[i - 1], smallestBiggerNumber = i;
        // can be improved if binary search is used
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > number && arr[j] < arr[smallestBiggerNumber])
                smallestBiggerNumber = j;
        }
        Utility.swap(arr, i - 1, smallestBiggerNumber);
        // use O(n) variant for placing instead of sort
        Arrays.sort(arr, i, n);
        return arr;
    }
}
