package by.it.group151002.rusakovich.lesson05;

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
        int len;

        Segment(int start, int stop){
            if(start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
            len = this.stop-this.start;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            if(this.start != o.start)
                return this.start - o.start;
            return this.len - o.len;
        }
    }

    void swap(Segment[] arr, int i, int j){
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    int partition(Segment[] arr, int p, int r){
        Segment x = arr[r];
        int i = p -1;
        for(int j = p; j < r; ++j){
          if (arr[j].compareTo(x) <= 0){
              ++i;
             swap(arr, i, j);
          }
        }
        swap(arr, i+1, r);
        return i + 1;
    }
/*
    int randomisedPartition(Segment[] arr, int p, int r){
        int i = (int)(Math.random() * r + p);
        swap(arr, r, i);
        return partition(arr, p, r);
    }

    void randomisedQuickSort(Segment[] arr, int p, int r){
        int q;
        if(p < r){
            q = randomisedPartition(arr, p, r);
            randomisedQuickSort(arr, p, q -1);
            randomisedQuickSort(arr, q + 1, r);
        }
    }
*/
    void quickSort(Segment[] arr, int p, int r){
        int q;
        if(p < r){
            q = partition(arr, p, r);
            quickSort(arr, p, q -1);
            quickSort(arr, q + 1, r);
        }
    }


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
        quickSort(segments, 0, segments.length-1);
        for(int i = 0; i< m; ++i){
            int j = 0;
            while(j < segments.length && points[i] >= segments[j].start){
                if(points[i] <= segments[j].stop)
                    ++result[i];
                ++j;
            }
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson05/dataA1.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
