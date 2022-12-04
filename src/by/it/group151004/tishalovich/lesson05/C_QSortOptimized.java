package by.it.group151004.tishalovich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

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

    static class Borders{
        private final int left;
        private final int right;
        public Borders(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    public static int binarySearch(Segment[] arr, Segment value){
        int left = -1;
        int right = arr.length;

        while(right - left > 1){
            int m = (right + left)/2;
            if(arr[m].compareTo(value) >= 0){
                right = m;
            } else{
                left = m;
            }
        }
        return right;
    }
    private static void swap(Segment[] arr, int a, int b){
        Segment temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void quickSort(Segment[] arr){
        Stack<Borders> stack = new Stack<>();
        stack.push(new Borders(0, arr.length));

        while(!stack.isEmpty()){
            Borders borders = stack.pop();
            if(borders.right- borders.left <= 1) continue;

            int i = borders.left;
            int j = borders.right - 1;
            int p = i - 1;
            int q = j + 1;

            Segment middle = arr[(i + j) / 2];

            while(i <= j){
                while(arr[i].compareTo(middle) < 0) i++;
                while(arr[j].compareTo(middle) >0) j--;
                if(i >= j) break;
                swap(arr, i, j);
                if(arr[i].compareTo(middle) == 0){
                    swap(arr, ++p, i++);
                }
                if(arr[j].compareTo(middle) == 0){
                    swap(arr, --q, j--);
                }
            }

            i = j + 1;
            for (int k = borders.left; k <= p; k++, j--) {
                swap(arr, k, j);
            }
            for (int k = borders.right - 2; k >= q; k--, i++) {
                swap(arr, k, i);
            }

            stack.push(new Borders(borders.left, j + 1));
            stack.push(new Borders(i, borders.right));
        }
    }


    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(stop, o.stop);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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

        quickSort(segments);
        for (int i = 0; i < points.length; i++) {
            int index = binarySearch(segments, new Segment(points[i], points[i]));
            while(index < segments.length){
                if(segments[index].start <= points[i]){
                    result[i]++;
                }
                index++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
