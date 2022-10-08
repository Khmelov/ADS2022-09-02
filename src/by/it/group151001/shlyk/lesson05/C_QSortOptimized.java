package by.it.group151001.shlyk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {
    ArrayList<Integer> arrayList;

    private final static Random randomizer = new Random();

    //including borders
    public static int getRandom(int minRange, int maxRange){
        return randomizer.nextInt(maxRange + 1 - minRange) + minRange;
    }

    private static final int iMinPivot = 0;
    private static final int iMaxPivot = 1;
    public static int[] partition(Segment[] sortArray, int iMin, int iMax){
        if(sortArray[iMin].compareTo(sortArray[iMax]) > 0)
            Segment.swap(sortArray, iMin, iMax);

        int i = iMin + 1;
        int k = i;
        int j = iMax - 1;
        Segment pSmall = sortArray[iMin], pHuge = sortArray[iMax];
        while(k <= j){
            if(sortArray[k].compareTo(pSmall) < 0){
                Segment.swap(sortArray, k, i);
                i++;
            } else if(sortArray[k].compareTo(pHuge) >= 0) {
                while(sortArray[j].compareTo(pHuge) > 0 && k < j)
                    j--;
                Segment.swap(sortArray, k, j);
                j--;
                if(sortArray[k].compareTo(pSmall) < 0){
                    Segment.swap(sortArray, k, i);
                    i++;
                }
            }
            k++;
        }
        i--;
        j++;
        Segment.swap(sortArray, iMin, i);
        Segment.swap(sortArray, iMax, j);
        return new int[]{i, j};
    }
    public static void doubleQuickSort (Segment[] sortArray, int iMin, int iMax){
        if(iMin < iMax){
            int[] pIndex = partition(sortArray, iMin, iMax);
            doubleQuickSort(sortArray, iMin, pIndex[iMinPivot] - 1);
            doubleQuickSort(sortArray, pIndex[iMinPivot] + 1, pIndex[iMaxPivot] - 1);
            doubleQuickSort(sortArray, pIndex[iMaxPivot] + 1, iMax);
        }
    }
    public static void insertSort(Segment[] sortArray, int iMin, int iMax){
        for(int i = iMin + 1; i <= iMax; i++){
            int j = i - 1;
            Segment x = sortArray[i];
            while(j >= 0 && sortArray[j].compareTo(x) > 0){
                sortArray[j + 1] = sortArray[j];
                j--;
            }
            sortArray[j + 1] = x;
        }
    }

    public static final int NOT_FOUND = -1;
    public static int getIndex(Segment[] array, int value) {
        int l = -1;
        int r = array.length;
        int iMiddle = (r + l) / 2;
        while(r - l > 1) {

            if (array[iMiddle].consists(value)) {
                r = iMiddle;
                break;
            }
            if( array[iMiddle].isGreater(value) ){
                r = iMiddle;
            } else {
                l = iMiddle;
            }
            iMiddle = (l + r) / 2;
        }
        if(r < array.length && array[r].consists(value))
            return r;
        return NOT_FOUND;
    }

        //find by left side
    public static int getStartIndex(Segment[] sortArray, int value){
        int iAny = getIndex(sortArray, value);
        int iStart = iAny;
        if(iAny != NOT_FOUND){
            boolean isStart = false;
            //descend over array
            while(!isStart && iStart != 0) {
                if(sortArray[iStart - 1].consists(value) ){
                    iStart--;
                }
                else isStart = true;

            }
        }
        return iStart;
    }
    public static int getSingleDuplicates(Segment[] sortArray, int event){
        int iStart = getStartIndex(sortArray, event);
        int result = 0;
        if(iStart != NOT_FOUND) {
            for (int i = iStart; i < sortArray.length && sortArray[i].consists(event); i++) {
                result++;
            }
        }
        return result;
    }

    private class Segment implements Comparable<Segment>{
        enum CompType {General, Inside}
        int start;
        int stop;

        Segment(int start, int stop){
            if(start > stop){
                this.start = stop;
                this.stop = start;
            } else {
                this.start = start;
                this.stop = stop;
            }
        }
        public static CompType comparatorType;
        @Override
        public int compareTo(Segment o) {
            int result;
            if (comparatorType == CompType.General) {
                result = this.start - o.start;
                if (result != 0) {
                    result = result > 0 ? 1 : -1;
                }
            } else {
                if (this.start != o.start)
                    result = 0;
                else {
                    result = (this.stop - o.stop);
                    if (result != 0) {
                        result = result > 0 ? 1 : -1;
                    }
                }
            }
            return result;

        }
        public static void swap(Segment[] array, int i, int j){
            Segment temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        //sort by sequences
        public static void  sort(Segment[] sortArray){
            int iStart = 0;
            int iEnd = iStart;
            for(int i = 1; i < sortArray.length; i++){
                if(sortArray[iStart].start == sortArray[i].start){
                    iEnd++;
                } else
                {
                    if(iEnd - iStart >= 1)
                    {
                        insertSort(sortArray, iStart, iEnd);
                    }
                    iStart = i;
                    iEnd = iStart;
                }
            }
        }
        public boolean isGreater(int event){
            return this.start > event;
        }
        public boolean consists(int event){
            return (this.stop >= event && this.start <= event);
        }
        //get any value inside this period
        public int getAny(){
            return getRandom(this.start, this.stop);
        }
    }
    //return num of times when events was corrupted or noticed
    private static boolean isOrdered(Segment[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i - 1].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }
    public static int[] getDuplicates(Segment[] periods, int[] events){
        int[] result = new int[events.length];
        Segment.comparatorType = Segment.CompType.General;
        doubleQuickSort(periods, 0, periods.length - 1);
        Segment.comparatorType = Segment.CompType.Inside;
        Segment.sort(periods);

        for(int i = 0; i< events.length; i++){
            result[i] = getSingleDuplicates(periods, events[i]);
        }


        return result;
    }

    //add or not to add
    public static final int FORTUNE_LEVEL = 70; //percents
    public static final int IMPOSSIBLE_EVENT = 10_000_000;
    final int MIN_POINTS_NUM = 5;
    public boolean isAddable(){
        return (getRandom(1, 100) <= FORTUNE_LEVEL);
    }
    public int[] getEvents(Segment[] segments){
        int nSegment = segments.length;

        int nPoints = getRandom(MIN_POINTS_NUM, nSegment);
        int[] points = new int[nPoints];
        for(int i = 0; i < points.length; i++){
            if(isAddable())
                points[i] = segments[getRandom(0, segments.length - 1)].getAny();
            else
                points[i] = IMPOSSIBLE_EVENT;
        }
        return points;
    }
    //should to be or not to be
    public int[] getResponse(){
        int[] result = new int[events.length];
        int iStart = 0;
        for(int event : events){
            if(event != IMPOSSIBLE_EVENT)
                result[iStart] = 1;
            else
                result[iStart] = 0;
            iStart++;
        }
        return result;
    }
    private int[] events;

    int[] getAccessory2(InputStream stream)  {
        Scanner scanner = new Scanner(stream);
        int nSegment = scanner.nextInt();
        Segment[] segments=new Segment[nSegment];
        for (int i = 0; i < nSegment; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        this.events = getEvents(segments);
        int[] result = getDuplicates(segments, events);
        return result;

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson05/Values");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}