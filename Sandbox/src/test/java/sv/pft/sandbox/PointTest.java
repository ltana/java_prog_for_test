package sv.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ltana on 27.02.2016.
 */
public class PointTest {
    @Test
    public void distance1() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(6, 6);
        double dist = p1.distance(p2);
        Assert.assertTrue(dist==1.4142135623730951);
    }

    @Test
    public void distanceZero() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(5, 5);
        Assert.assertNotEquals(p1.distance(p2), 0.1);
    }

    @Test
    public void distanceInt() {
        Point p1 = new Point(8, 4);
        Point p2 = new Point(5, 0);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }
}
