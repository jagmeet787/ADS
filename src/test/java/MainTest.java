
import ADS.BitManipulation.BitManipulations;
import QS.Array.Kadane;
import Utility.Utility;
import org.junit.Test;
import org.junit.Assert;

public class MainTest {
    @Test
    public void test() {
        Assert.assertEquals(5, Kadane.kadane(new int[]{-1,0,2,3,-6}));
    }

    @Test
    public void testXorSwap() {
        Assert.assertArrayEquals(new int[]{1,-2,-3}, BitManipulations.xorSwapping(new int[]{1,-3,-2}, 1, 2));
    }

    @Test
    public void testSwap() {
        Assert.assertArrayEquals(new int[]{1,-2,-3}, Utility.swap(new int[]{1,-3,-2}, 1, 2));
    }

}
