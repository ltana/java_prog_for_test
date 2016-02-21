package sv.pft.sandbox;

/**
 * Created by Ltana on 21.02.2016.
 */
public class SecondHomeExercise {

    public static void main(String[] args) {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);

        System.out.println("Distance between p1(" + p1.x + ", " + p1.y +
                ") and p2(" + p2.x + ", " + p2.y + ") is " + p1.distance(p2));

       /* System.out.println("Distance between p1(" + p1.x + ", " + p1.y +
                ") and p2(" + p2.x + ", " + p2.y + ") is " + distance(p1, p2));*/
    }

   /* public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }*/


}
