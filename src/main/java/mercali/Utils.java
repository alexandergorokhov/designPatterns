package mercali;


import mercali.pojo.Vector;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static double fromDegreesToRadiants(double angleRadiant) {
        return Math.toRadians(angleRadiant);

    }

    public static double fromRadiantsToDegrees(double angleDegree) {
        return Math.toDegrees(angleDegree);


   }

    public static Vector fromPolarToCartesians(double radius, double polarAngle) {
        return new Vector(round(

                radius*Math.cos(polarAngle),3),
                round(radius*Math.sin(polarAngle),3)
                );


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static boolean arePointsAreColinear(double x1, double y1,double x2, double y2, double x3, double y3){
        return (y3-y2)*(x2-x1) == (y2-y1)*(x3-x2);
    }



}
