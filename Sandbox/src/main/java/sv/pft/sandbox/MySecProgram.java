package sv.pft.sandbox;

public class MySecProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Sv");

        double l = 5;
        System.out.println("пл кв сл стор " + l + "им пл = " + area(l));

        double a = 3;
        double b = 4;
        System.out.println("пл пр со стор " + a + " and " + b + " is = " + area(a, b));
    }

    public static void hello(String som) {
        System.out.println("Hel " + som + "!");
    }

    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}