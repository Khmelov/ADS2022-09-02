package by.it.group151004.belsky.lesson10;

import java.util.Set;
import java.util.TreeSet;

public class MyTest {
    public static void main (String[] args) {
        TaskC<Integer> set = new TaskC<>();
        set.add(20);
        set.add(10);
        set.add(5);

        set.add(30);
        set.add(25);
        set.add(24);
        set.add(23);

        set.add(35);
        set.add(32);

        System.out.println(set);
        System.out.println(set.lower(30));
        System.out.println(set.lower(36));
        System.out.println(set.lower(35));
        System.out.println(set.lower(10));
        System.out.println(set.lower(5));

        System.out.println();

        System.out.println(set.higher(30));
        System.out.println(set.higher(36));
        System.out.println(set.higher(35));
        System.out.println(set.higher(10));
        System.out.println(set.higher(4));

//        TreeSet<Integer> set = new TreeSet<>();
//        set.add(20);
//        set.add(30);
//        set.add(10);

//        System.out.println(set.lower(25)); //only less
//        System.out.println(set.higher(25));
//        System.out.println(set.floor(9)); //less or equal
//        System.out.println(set.ceiling(18));
    }
}
