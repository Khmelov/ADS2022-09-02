package by.it.group151001.saprankov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if(start<stop)
            {
                this.start = start;
                this.stop = stop;
            }
            else
            {
                this.start=stop;
                this.stop=start;
            }

        }

        @Override
        public int compareTo(Segment o) {
            int res=0;
            if(this.stop<o.stop){
                res=-1;
            }
            if(this.stop>o.stop){
                res=1;
            }
            if(this.stop==o.stop){
                res=0;
            }
            return res;

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
        qsort(segments,0, segments.length-1);
        result=binarysearch(segments,points);



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public int[] binarysearch(Segment a[] ,int[] points){

        int[] result = new int[points.length];

        for(int i=0;i<points.length;i++){
            int l=0;
            int r= a.length-1;
            int mid=(r-l)/2;
            while(l<r) {
                //if point is lefter than middle
                if (points[i] < a[mid].start) {
                    if(mid==r){
                        l=r;
                        result[i]=0;
                    }else {
                    r = mid;
                    mid = (r - l) / 2;}

                } else //if point is righter
                    if (points[i] > a[mid].stop) {
                        if(mid==l) {
                            l=r;
                            result[i]=0;
                        }
                        else {
                            l = mid;
                            mid = (r - l) / 2;
                        }
                //got the point
                } else if (points[i] <= a[mid].stop && points[i] >= a[mid].start) {
                    result[i] += 1;
                    int j = mid-1;
                    //go to left to find
                    while (j>=0&&points[i] <= a[j].stop && points[i] >= a[j].start) {
                        result[i] += 1;
                        j--;
                    }
                    j = mid+1;
                    while (j<r&&points[i] <= a[j].stop && points[i] >= a[j].start) {
                        result[i] += 1;
                        j++;
                    }
                    l=r;


                }
            }
        }
        return result;

    }
    public void qsort(Segment a[],int left,int right){
        int l=left;
        int r=right;
        while(left<right) {
            int[] pivots = partition(a, l, r);
            l = pivots[0];
            r = pivots[1];
            if(l-left<right-r){
                qsort(a, left, l);
                left = r;
            }
            else {
                qsort(a, r, right);
                right = l;

            }

        }


    }
    public int[] partition(Segment a[],int left,int right){
        //pivot index
        int pInd=0;
        int res[]=new int[2];
        Segment pivot = a[right];
        //counting pivot element correct position
        while(left<right){
            if(a[left].compareTo(pivot)>0){
                Segment tmp = a[left];
                a[left]=a[pInd];
                a[pInd]=tmp;
                pInd++;

            }
            left++;
            pInd++;
        }
        //move pivot to his correct position
        if(a[pInd].compareTo(a[right])>0) {
            Segment tmp = a[right];
            a[right] = a[pInd];
            a[pInd] = tmp;
        }
        //moving all equal to pivot elements on the right place(and creating 2nd pivot)
        int pInd2=pInd;
        while(pInd2<right){
            if(a[pInd2].compareTo(pivot)==0){
                Segment tmp = a[pInd2];
                a[right]=a[pInd2];
                a[pInd2]=tmp;

            }
            pInd2++;
        }
        res[0]=pInd;
        res[1]=pInd2;


        return res;


    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/saprankov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
