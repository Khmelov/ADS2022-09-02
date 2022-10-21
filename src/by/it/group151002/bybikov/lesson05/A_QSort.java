package by.it.group151002.bybikov.lesson05;

import java.io.*;
import java.util.Arrays;
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

    boolean isCorrect;
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start > stop) {
                start = start + stop;
                stop = start - stop;
                start = start - stop;
            }
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if(this.stop > o.stop)
                return 1;
            return 0;
        }
    }

    int[] completeTaskStupidWay (Segment[] segmentArray, int[] eventArray) {
        int[] result= new int[eventArray.length];
        for (int i = 0; i < eventArray.length; i++) {
            for (int j = 0; j < segmentArray.length; j++)
                if(eventArray[i] >= segmentArray[j].start && eventArray[i] <= segmentArray[j].stop)
                result[i]++;
        }
        return result;
    }

    private void exchangeValuesByIndex (Segment[] array, int firstIndex, int secondIndex) {
        Segment tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }

    private int partition(Segment[] array, int lowerIndex, int highIndex) {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int pivotIndex = randomValuesMethods.getIntRandomValueInRange(lowerIndex, highIndex);
        exchangeValuesByIndex(array, pivotIndex, highIndex);
        pivotIndex = highIndex;
        int i = lowerIndex;
        for (int j = lowerIndex; j < pivotIndex; j++)
            if(array[j].compareTo(array[pivotIndex]) > 0) {
                exchangeValuesByIndex(array, i, j);
                i++;
            }
        exchangeValuesByIndex(array, i, pivotIndex);
        pivotIndex = i;
        return pivotIndex;
    }

    private Segment[] quickSortMethod (Segment[] array, int lowerIndex, int highIndex) {
        if(highIndex <= lowerIndex)
            return array;
        int pivotIndex = partition(array, lowerIndex, highIndex);
        array = quickSortMethod(array, lowerIndex, pivotIndex);
        array = quickSortMethod(array, pivotIndex + 1, highIndex);
        return array;
    }

    Segment[] quickSort (Segment[] array) {
        if(array == null)
            return null;
        else if(array.length < 2)
            return array;
        return quickSortMethod(array, 0, array.length - 1);
    }

    boolean isSortedArray (Segment[] array) {
        if(array == null)
            return true;
        boolean isSorted = true;
        for (int i = 1; i < array.length; i++)
            if (array[i - 1].stop < array[i].stop) {
                isSorted = false;
            }
        return isSorted;
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
        segments = quickSort(segments);
        this.isCorrect = isSortedArray(segments);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < segments.length && points[i] <= segments[j].stop; j++) {
                if(points[i] >= segments[j].start)
                    result[i]++;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        scanner.close();
        int[] otherResult = completeTaskStupidWay(segments, points);
        if (this.isCorrect)
            this.isCorrect = Arrays.equals(otherResult, result);
        return result;
    }


    public static void main(String[] args) throws IOException {
        A_QSort instance = new A_QSort();
        instance.isCorrect = true;
        String root = System.getProperty("user.dir") + "/src/";
        InputStream input = new FileInputStream(root + "by/it/group151002/bybikov/lesson05/dataA_Test.txt");
        String outputPath = root + "by/it/group151002/bybikov/lesson05/dataA_Test.txt";
        FileMethods fileMethods = new FileMethods();
        fileMethods.createInputFile(outputPath);
        int[] result = instance.getAccessory(input);
        if(instance.isCorrect)
            System.out.println("A_QSort Test Complete");
        else
            System.err.println("A_QSort Test wrong");
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
