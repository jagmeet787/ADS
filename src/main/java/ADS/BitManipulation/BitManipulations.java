package ADS.BitManipulation;

public class BitManipulations {

    // set the ith bit in bitMask to 1
    public static int setBit(int bitMask, int i) {
        return bitMask | (1 << i);
    }

    // clear ith bit in bitMask to 0
    public static int unsetBit(int bitMask, int i) {
        return bitMask & ~(1 << i);
    }

    // toggle bit at the ith location
    public static int toggleBit(int bitMask, int i) {
        //if (isSet(bitMask, i)) return unsetBit(bitMask, i);
        //return setBit(bitMask, i);
        return bitMask ^ (1 << i);
    }

    // check if ith bit is set or not
    public static boolean isSet(int bitMask, int i) {
        return (bitMask & (1 << i)) != 0;
    }

    // return a number with n lsb bits set
    public static int setAllUpto(int n) {
        return (1 << n) - 1;
    }

    // return true if number is power of two
    public static boolean isPowerOfTwo(int number) {
        if (number == 0) return false;
        if (number < 0) number = -1 * number;
        if (number == 1) return true;
        return ((number - 1) & number) == 0;
    }

    // count the number of set bits in the number
    // n & (n - 1) removes the last set bit from the n
    public static int numberOfSetBits(int number) {
        int count = 0;
        while(number > 0) {
            count++;
            number = number & (number - 1);
        }
        return count;
    }

    public static int union(int bitSet1, int bitSet2) {
        return bitSet1 | bitSet2;
    }

    public static int intersection(int bitSet1, int bitSet2) {
        return bitSet1 & bitSet2;
    }

    public static int leastSignificantSetBit(int bitMask) {
        return bitMask & (-bitMask);
    }

    // iterate through all the subsets of set size n
    // for (int i = 0; i < (1 << n); i++)
    // iterate through all the subsets of y excluding empty subset
    // for (int i = y; i > 0; i = y & (i - 1))

    public static int[] xorSwapping(int[] arr, int index1, int index2) {
        // a = a ^ b;
        // b = a ^ b; // = (a ^ b) ^ b
        // a = a ^ b; // = (a ^ b) ^ a
        arr[index1] = arr[index1] ^ arr[index2];
        arr[index2] = arr[index2] ^ arr[index1];
        arr[index1] = arr[index1] ^ arr[index2];
        return arr;
    }
}
