package by.it.group151001.yankova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

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

    int calc(InputStream stream) throws FileNotFoundException, ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(stream);
        int arrSize = scanner.nextInt();
        Integer[] A = new Integer[arrSize];
        for (int i = 0; i < arrSize; i++)
            A[i] = scanner.nextInt();

        /*int arrSize = 32_768;
        Integer[] A = new Integer[arrSize];
        Random randomizer = new Random();
        for (int i = 0; i < arrSize; i++)
            A[i] = randomizer.nextInt(1_000_000);*/

        /*Declaring threads. Start:*/
        List<Future> futures = new ArrayList<>();
        int processorCount = Runtime.getRuntime().availableProcessors();
        int batchSize = 0;
        for (int i = processorCount; i > 0; i--) {
            batchSize = arrSize / i;
            if (batchSize > 1 && arrSize % i == 0) {
                processorCount = i;
                break;
            }
        }

        AtomicInteger invCount = new AtomicInteger(0);
        AtomicInteger internalInvCount = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();
        final ExecutorService executorService = Executors.newFixedThreadPool(processorCount);
        ArrayList<ThreadSort> threads = new ArrayList<>();
        for (int i = 0; i < processorCount; i++) {
            Integer[] part = new Integer[batchSize];
            System.arraycopy(A, i * batchSize, part, 0, batchSize);
            ThreadSort currentThread = new ThreadSort(part, invCount);
            futures.add(executorService.submit(currentThread));
            threads.add(currentThread);
        }
        for (Future future : futures) {
            future.get();
        }
        executorService.shutdown();
        /*End.*/

        int result = 0, j = 0;
        Integer[] sortedUnitedParts = new Integer[arrSize];
        for (ThreadSort currentThread : threads) {
            if (j == 0) {
                sortedUnitedParts = currentThread.getSorted();
                result += invCount.get();
                j += 1;
            } else {
                Integer[] part = currentThread.getSorted();
                sortedUnitedParts = Join(sortedUnitedParts, part);
                int mid = sortedUnitedParts.length - part.length;
                ThreadSort.MergeArrays(sortedUnitedParts, 0, mid, sortedUnitedParts.length, internalInvCount);
            }
        }
        result += internalInvCount.get();
        /*long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Total time when using threads: " + timeSpent + " msec.(Number of inversions = " + result + ");");

        startTime = System.currentTimeMillis();
        result = countInversions(A);
        timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("\nTotal time without threads: " + timeSpent + " msec.(Number of inversions = " + result + ");");*/
        return result;
    }

    public static Integer[] Join(Integer[] a, Integer[] b){
        Integer[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static int countInversions(Integer[] A){
        int result = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A.length; j++){
                if(A[i] > A[j]) result++;
            }
        }
        return result;
    }

public class ThreadSort implements Runnable{
        private final Integer[] A;
        private AtomicInteger inversionCount;
        private Integer[] SortedA;
        public ThreadSort(Integer[] A, AtomicInteger inversionCount){
            this.A = A;
            this.inversionCount = inversionCount;
            SortedA = new Integer[A.length];
            System.arraycopy(A, 0, SortedA, 0, A.length);
        }

        public void run(){
            MergeSort(SortedA, 0, SortedA.length, inversionCount);
        }
        private void MergeSort(Integer[] a, int l, int r, AtomicInteger inversionCount){
            if(l + 1 >= r)
                return;
            int mid = (l + r)/2;
            MergeSort(a, l, mid, inversionCount);
            MergeSort(a, mid, r, inversionCount);
            MergeArrays(a, l, mid, r, inversionCount);
        }
        public static void MergeArrays(Integer[] a, int l, int mid, int r, AtomicInteger inversionCount){
            Integer[] buffer = new Integer[r - l];
            int b1 = 0, b2 =   0;
            while(b1 + b2 != r - l){
                if((b1>=mid-l) || (b2<r-mid && a[b2+mid]<a[b1+l])){
                    if(b1<mid-l) inversionCount.addAndGet(mid - l - b1);
                    buffer[b1+b2] = a[b2+mid];
                    b2++;
                }else{
                    buffer[b1+b2] = a[b1+l];
                    b1++;
                }
            }
            System.arraycopy(buffer, 0, a, l, r - l);
        }
        public Integer[] getSorted(){
            return SortedA;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
