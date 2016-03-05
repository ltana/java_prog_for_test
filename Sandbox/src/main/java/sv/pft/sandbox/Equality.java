package sv.pft.sandbox;

/**
 * Created by Ltana on 05.03.2016.
 */
public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = s1;

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
