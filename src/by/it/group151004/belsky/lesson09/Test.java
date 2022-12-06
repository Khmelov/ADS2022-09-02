package by.it.group151004.belsky.lesson09;

import java.util.LinkedList;

public class Test {
    public static void main (String[] args) {
        ListA<Integer> list = new ListA<>();
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(3, 13);
        list.add(3, 14);
        list.add(3, 15);

        list.add(6, 16);

        list.add(1, 9);

        list.add(0, 8);

        System.out.println(list);
        System.out.println(list.size());

        LinkedList<Integer> ll = new LinkedList<>();
//        ll.addAll
    }
}
