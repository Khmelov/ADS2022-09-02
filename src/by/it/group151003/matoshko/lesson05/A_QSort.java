package by.it.group151003.matoshko.lesson05;

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
            if(start <= stop){
                this.start = start;
                this.stop = stop;
            }else{
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(start, o.start);
        }
    }

    private void swap(Segment[] segments,int i,int j)
    {
        Segment tmp=segments[i];
        segments[i]=segments[j];
        segments[j]=tmp;
    }

    private void qSort(Segment[] segments,int L,int R)
    {
        // BackStep для выхода из рекурсии(если левая граница = правой, то есть 1 элемент остался в сортируемой части)
        if(L>=R)
            return;
        // Ставим дополнительные границы, которые будем сдвигать
        int i=L;
        int j=R;
        // Выбираем опорный элемент(в моем случае это средний)
        Segment x=segments[(L+R)/2];
        while (i<=j)
        {
            // Сдвигаем границы, пока не найдем элемент слева, который больше опорного
            // а элемент справа меньше опорного
            while(segments[i].compareTo(x)==-1)
                i++;
            while(segments[j].compareTo(x)==1)
                j--;
            // Если мы еще проходим по отведенным левой и правой части и границы не пересеклись, то обмениваем элементы
            if(i<=j)
            {
                swap(segments,i,j);
                i++;
                j--;
            }

        }
        // Рекурсивно применяем к оставшейся левой и правой части
        if(L<j)
            qSort(segments,L,j);
        if(R>i)
            qSort(segments,i,R);

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
        qSort(segments,0, segments.length-1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i]>=segments[j].start && points[i]<=segments[j].stop){

                    result[i]++;
                }
            }
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
