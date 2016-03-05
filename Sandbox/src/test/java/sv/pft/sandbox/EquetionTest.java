package sv.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ltana on 05.03.2016.
 */
public class EquetionTest {

    @Test
    public  void test0() {
        Equetion e = new Equetion(1, 1, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public  void test1() {
        Equetion e = new Equetion(1, 2, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public  void test2() {
        Equetion e = new Equetion(1, 5, 6);
        Assert.assertEquals(e.rootNumber(), 2);
    }

    @Test
    public  void testLinear() {
        Equetion e = new Equetion(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public  void testConstant() {
        Equetion e = new Equetion(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public  void testZero() {
        Equetion e = new Equetion(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }
}
