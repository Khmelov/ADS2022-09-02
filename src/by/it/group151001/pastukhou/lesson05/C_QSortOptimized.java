package by.it.group151001.pastukhou.lesson05;

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
            - за стек отвечает элиминация хвостовой рекурсии                          + рекурсия убрана
            - за сам массив отрезков - сортировка на месте                            + без передачи массивов по значению
            - рекурсивные вызовы должны проводиться на основе 3-разбиения             + рекурсия убрана

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения         + бинарный поиск по началу отрезка
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

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;

            if (this.start > this.stop) {
                int temp = this.start;
                this.start = this.stop;
                this.stop = temp;
            }
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start < o.start) return -1;
            if (this.start == o.start) {
                if (this.stop < o.stop) return -1;
                if (this.stop == o.stop) return 0;
                return 1;
            }
            return 1;
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
            result[i] = 0;
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        Stack<Integer> left = new Stack<>(), right = new Stack<>();
        int l = 0, r = 0;
        left.push(0);
        right.push(n - 1);
        while (!left.empty()) {
            l = left.pop();
            r = right.pop();
            if (r <= l) continue;
            Segment base = segments[(l + r) / 2];
            int i = l, j = r;
            while (i <= j) {
                while (segments[i].compareTo(base) < 0) i++;
                while (segments[j].compareTo(base) > 0) j--;
                if (i >= j) break;
                Segment temp = segments[i];
                segments[i] = segments[j];
                segments[j] = temp;
            }
            if (i - l > r - i) {
                left.push(l);
                right.push(i - 1);
                left.push(i + 1);
                right.push(r);
            } else {
                left.push(i + 1);
                right.push(r);
                left.push(l);
                right.push(i - 1);
            }
        }

        for (int i = 0; i < m; ++i) {
            l = 0;
            r = n - 1;
            while (r - l > 1) {
                int mid = (l + r) / 2;
                if (segments[mid].start <= points[i]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            int j = r - 1;
            while (j >= 0 && segments[j].start <= points[i] && points[i] <= segments[j].stop) {
                result[i]++;
                j--;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/pastukhou/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
