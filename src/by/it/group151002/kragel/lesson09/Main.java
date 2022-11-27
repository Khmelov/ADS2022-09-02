package by.it.group151002.kragel.lesson09;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> s = new SetC<>();
       List<Integer> l = new ListB<>();
        for (int i = 0; i < 120; i++) {
            l.add(i);
        }
        List<Integer> k = new ListB<>();
        for (int i = 10; i < 121; i++) {
            l.add(i);
        }
        s.addAll(l);
        s.addAll(l);
        System.out.println(s.containsAll(k));

    }
}
