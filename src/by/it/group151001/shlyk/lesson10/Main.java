package by.it.group151001.shlyk.lesson10;


import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class Main {

    void start(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
            }
        };
        TaskC<Integer> stringSet = new TaskC();
        Random random = new Random(12321);
        for(int i = 0; i < 200; i++){
            stringSet.add(random.nextInt(200));
        }
        System.out.println(stringSet.toString());

        int x = stringSet.lower(15);
        x = stringSet.lower(101);
        x = stringSet.lower(30);

        x = stringSet.floor(30);
        x = stringSet.floor(11);

        x = stringSet.ceiling(10);
        x = stringSet.ceiling(40);

        x = stringSet.higher(195);
        x = stringSet.higher(100);
       // Iterator first = stringSet.iterator();
        Integer last = stringSet.first();
        for(Integer item : stringSet){
            if(item < last)
                System.out.println("Error");
        }
        stringSet.remove(10);
        stringSet.clear();
        stringSet.add(11);

    }
    public static void main (String[] argv){
                Main main = new Main();
                main.start();
    }
}
