import ADS.BitManipulation.PowerSet;
import ADS.DynamicProgramming.UglyNumbers;
import Utility.Utility;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        final PowerSet ps = new PowerSet();
        ps.powerSetRecursive(new int[] {1,2});
        ps.powerSet(new int[] {3,4});
    }
}
