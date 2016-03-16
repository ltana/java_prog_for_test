package sv.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ltana on 13.03.2016.
 */
public class Collections {
    public static void main(String[] args){
        String[] langs = {"Java", "C#", "Python", "C++"};

        List<String> languages = new ArrayList<String>();
        languages.add("Lava list");
        languages.add("C# list");

        List<String> langlist = Arrays.asList("Java as", "C# as", "Python as", "C++ as");

        for (int i =0; i < langlist.size(); i++) {
            System.out.println("I want to learn asList " + langlist.get(i));
        }

        for (String l : languages) {
            System.out.println("I want to learn list " + l);
        }

        for (String l : langs) {
            System.out.println("I want to learn array " + l);
        }
    }
}
