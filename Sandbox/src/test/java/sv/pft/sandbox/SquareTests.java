package sv.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ltana on 25.02.2016.
 */
public class SquareTests {

    @Test
    public void testArea() {
       Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);
    }
}
