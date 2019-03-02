package ADS.BitManipulation;

public class GrayCode {

    public static long binaryToGray(long num) {
        if (num < 0) throw new IllegalArgumentException("Non negative integers only. Passed value: " + num);
        return num ^ (num >> 1);
    }

    // convert 1010 to gray code (((1010 ^ 101) ^ 10) ^ 1) = 1100
    public static long grayToBinary(long num) {
        if (num < 0) throw new IllegalArgumentException("Non negative integers only. Passed value: " + num);
        long mask = num >> 1;
        while (mask != 0) {
            num = num ^ mask;
            mask = mask >> 1;
        }
        return num;
    }

    public static int grayToBinary32(int num) {
        if (num < 0) throw new IllegalArgumentException("Non negative integers only. Passed value: " + num);
        num = num ^ (num >> 16);
        num = num ^ (num >> 8);
        num = num ^ (num >> 4);
        num = num ^ (num >> 2);
        num = num ^ (num >> 1);
        return num;
    }
}
