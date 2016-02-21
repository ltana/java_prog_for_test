package sv.pft.sandbox;

/**
 * Created by Ltana on 21.02.2016.
 */
public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point point2) {
        return Math.sqrt(Math.pow((point2.x - this.x), 2) + Math.pow((point2.y - this.y), 2));
    }
}
