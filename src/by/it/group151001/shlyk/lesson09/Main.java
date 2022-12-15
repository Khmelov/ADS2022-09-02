package by.it.group151001.shlyk.lesson09;

import org.w3c.dom.ls.LSException;

import java.util.*;

public class Main {
    public static void main (String[] argv){
        ListA<Integer> sample = new ListA<>();
        sample.add(12);
        sample.add(23);
//        List<Integer> ownList = new ListB<>();
//        ownList.add(12);
//        ownList.add(1, 23);
//        ownList.add(33);
//        ownList.remove(1);
//        ArrayList<Integer> arrNums = new ArrayList<>();
//        arrNums.add(45);
//        arrNums.add(35);
//        arrNums.add(98);
//        ownList.addAll(arrNums);
//        Integer sum = 0;
//        for(Integer value : ownList){
//            sum += value;
//        }
//        Integer[] arrCoolNums = new Integer[10];
//        arrCoolNums = ownList.toArray(arrCoolNums);
//        System.out.println(ownList.toString());
        SetC<Integer> ownSet = new SetC<>();
        ownSet.add(12);
        ownSet.add(34);
        System.out.println(ownSet.toString());
        ownSet.remove(12);
        ownSet.remove(44);
        ownSet.add(44);
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(123);
        arrList.add(12);
        ownSet.add(12);
        ownSet.addAll(arrList);
        Integer sum = 0;
        for(Integer value : ownSet){
            sum += value;
        }
        System.out.println(sum);
    }
}
