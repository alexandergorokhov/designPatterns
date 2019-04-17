import mercali.Utils;
import mercali.pojo.Triangle;
import mercali.pojo.Vector;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class UtilsTest {

    @Test
    public void radTogradTest(){
        Vector result = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(195));
        Assert.assertEquals(-11.59D,result.getX(),0.01);
        Assert.assertEquals(-3.105D, result.getY(),0.01);
    }

    @Test
    public void itShouldReturnTrueRainTest(){
        Vector side1 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(45));
        Vector side2 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(280));
        Vector side3 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(170));
        Triangle triangle = new Triangle(side1.getX(),side1.getY(),side2.getX(),side2.getY(),side3.getX(),side3.getY());
        boolean result = triangle.contains(0d,0d);
        Assert.assertEquals(result,true);

    }

    @Test
    public void itShouldReturnFalseRainTest(){
        Vector side1 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(45));
        Vector side2 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(150));
        Vector side3 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(170));
        Triangle triangle = new Triangle(side1.getX(),side1.getY(),side2.getX(),side2.getY(),side3.getX(),side3.getY());
        boolean result = triangle.contains(0d,0d);
        Assert.assertEquals(result,false);

    }

    @Test
    public void itShouldReturnTrueAllignTest(){
        Vector side1 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(45));
        Vector side2 = Utils.fromPolarToCartesians(0,Utils.fromDegreesToRadiants(0));
        Vector side3 = Utils.fromPolarToCartesians(12,Utils.fromDegreesToRadiants(225));
        boolean result = Utils.arePointsAreColinear(side1.getX(),side1.getY(),side2.getX(),side2.getY(),side3.getX(),side3.getY());
        Assert.assertEquals(result,true);

    }


}
