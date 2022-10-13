package by.it.group151001.shlyk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    public static final int NUM_THREADS = 16;
    public final static int TRIGGER_NEST_LEVEL = 1;
    public static volatile int nestLevel = 0;
    public static final int MAX_NEST_LEVEL;

    static {
        MAX_NEST_LEVEL = (NUM_THREADS - 1) * 2;
    }

    public static boolean isOrdered(int[] sortArray){
        for(int i = 1; i < sortArray.length; i++){
            if(sortArray[i] < sortArray[i - 1])
                return false;
        }
        return true;
    }
    public class Sort extends Thread {
         private long mergeSort(int[] sortArray, int offset, int arrSize) {
            if (arrSize == 1 || arrSize == 0)
                return 0;

            long nInversions = 0;
            if (arrSize > 2) {
                int rightSize = arrSize / 2;
                int leftSize = arrSize - rightSize;
                if(nestLevel < MAX_NEST_LEVEL){
                    nestLevel += 1;
                    threads = new Sort[2];
                        threads[0] = new Sort(sortArray, offset, rightSize);
                        threads[0].start();
                        threads[1] = new Sort(sortArray, offset + rightSize, leftSize);
                        threads[1].start();
                        while(threads[0].isAlive() || threads[1].isAlive())
                            Thread.yield();
                    nInversions += threads[0].getInversions();
                    nInversions += threads[1].getInversions();
                } else
                {
                    nInversions += mergeSort(sortArray, offset, rightSize);
                    nInversions += mergeSort(sortArray, offset + rightSize, leftSize);
                }
                int iRight = offset, iLeft = offset + rightSize;
                int iTemp = 0;
                int[] temp = new int[arrSize];
                for (int i = 0; i < arrSize; i++) {
                    if (iRight < rightSize + offset && iLeft < arrSize + offset) {
                        if (sortArray[iRight] <= sortArray[iLeft]) {
                            temp[i] = sortArray[iRight];
                            iRight++;
                        } else {
                            nInversions += (rightSize + offset - iRight);
                            temp[i] = sortArray[iLeft];
                            iLeft++;
                        }
                    } else {
                        if (iRight == rightSize + offset) {
                            nInversions += (rightSize + offset - iRight);
                            temp[i] = sortArray[iLeft];
                            iLeft++;
                        } else {
                            temp[i] = sortArray[iRight];
                            iRight++;
                        }
                    }
                }
                for (int i = offset; iTemp < temp.length; i++, iTemp++) {
                    sortArray[i] = temp[iTemp];
                }
            }
            if (sortArray[offset] > sortArray[offset + 1]) {
                int temp = sortArray[offset];
                sortArray[offset] = sortArray[offset + 1];
                sortArray[offset + 1] = temp;
                nInversions = 1;
            } //otherwise array is sorted
             return nInversions;
        }

        @Override
        public void run() {

                this.nInversions += mergeSort(this.sortArray, this.offset, this.arrSize);
            //this.stop();
        }

        private int offset;
        private int[] sortArray;
        private int arrSize;
        private long nInversions;
        private Sort[] threads;
        public long getInversions(){
            return nInversions;
        }
        public Sort(int[] sortArray, int offset, int arrSize){
            this.sortArray = sortArray;
            this.offset = offset;
            this.arrSize = arrSize;
            nInversions = 0;
        }
    }

    long calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        long result;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        //mergeSort(a, 0,n);
        Sort mergy = new Sort(a, 0, n);
//        mergy.setName("main");
        mergy.start();
        while(mergy.isAlive()) {
            Thread.yield();
        }
      //  System.out.println(isOrdered(a));
        //boolean some = isOrdered(a, 0, n);
        result = mergy.getInversions();







        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson04/Values");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();
        long result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finishTime - startTime);
        Stream<Integer> test;
    }
}
