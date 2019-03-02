
import QS.Array.Kadane;
import org.junit.Test;
import org.junit.Assert;

public class MainTest {
    @Test
    public void test() {
        Assert.assertEquals(5, Kadane.kadane(new int[]{-1,0,2,3,-6}));
    }
}
