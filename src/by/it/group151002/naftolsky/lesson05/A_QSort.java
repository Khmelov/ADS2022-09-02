package by.it.group151002.naftolsky.lesson05;

import by.it.group151002.naftolsky.lesson02.C_GreedyKnapsack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (stop > start) {
                this.stop = stop;
                this.start = start;
            } else {
                this.start = stop;
                this.stop = start;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
//            return Integer.compare(stop - start, o.stop - o.start);
            return Integer.compare(stop, o.stop);
        }
    }



    int partition(Segment[] segments, int lowIndex, int highIndex) {
        Segment pivotValue = segments[lowIndex + highIndex / 2];

        int i = lowIndex - 1;
        int j = highIndex + 1;
        while (true)
        {
            do {
                i++;
            } while (segments[i].compareTo(pivotValue) == -1);

            do {
                j--;
            } while (segments[j].compareTo(pivotValue) == 1);

            if (i >= j) {
                return j;
            }

            Segment tempNumber = segments[i];
            segments[i] = segments[j];
            segments[j] = tempNumber;
        }
    }

    void quickSort(Segment[] segments, int  lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = partition(segments, lowIndex, highIndex);
        quickSort(segments, lowIndex, pivotIndex);
        quickSort(segments,pivotIndex + 1, highIndex);
    }


//    int partitionNumber(Segment[] segments, int lowIndex, int highIndex) {
//        Segment pivotValue = segments[lowIndex + highIndex / 2];
//
//        int i = lowIndex - 1;
//        int j = highIndex + 1;
//        while (true)
//        {
//            do {
//                i++;
//            } while (segments[i].compareTo(pivotValue) == -1);
//
//            do {
//                j--;
//            } while (segments[i].compareTo(pivotValue) == 1);
//
//            if (i >= j) {
//                return j;
//            }
//
//            Segment tempNumber = segments[i];
//            segments[i] = segments[j];
//            segments[j] = tempNumber;
//        }
//    }
//
//    void quickSortNumbers(Segment[] segments, int  lowIndex, int highIndex) {
//        if (lowIndex >= highIndex) {
//            return;
//        }
//        int pivotIndex = partition(segments, lowIndex, highIndex);
//        quickSort(segments, lowIndex, pivotIndex);
//        quickSort(segments,pivotIndex + 1, highIndex);
//    }




    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments,0, n - 1);

        for (int i = 0; i < m; i++) {
            result[i] = 0;
            int j = 0;
            while (j < n && segments[j].stop <= points[i]) {
                j++;
            }
            for (; j < n; j++) {
                if (segments[j].start <= points[i]) {
                    result[i]++;
                }
            }
        }

//        int n = events.length;
//        while (events[i].start < from && i < n) {
//            i++;
//        }

//        if (i < n && events[i].stop <= to) {
//            result.add(events[i]);
//            i++;
//            while (i < n && events[i].stop <= to) {
//                if (events[i].start >= result.get(result.size() - 1).stop) {
//                    result.add(events[i]);
//                }
//                i++;
//            }
//        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }




    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/naftolsky/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
