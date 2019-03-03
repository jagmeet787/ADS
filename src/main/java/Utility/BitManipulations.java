package Utility;

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
}
