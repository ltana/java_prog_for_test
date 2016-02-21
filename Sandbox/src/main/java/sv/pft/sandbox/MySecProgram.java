package sv.pft.sandbox;

public class MySecProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Sv");

        Square s = new Square(5);
        System.out.println("пл кв сл стор " + s.l + "им пл = " + s.area());

        Rectangle r = new Rectangle(3, 5);
        System.out.println("пл пр со стор " + r.a + " and " + r.b + " is = " + r.area());
    }

    public static void hello(String som) {
        System.out.println("Hel " + som + "!");
    }




}