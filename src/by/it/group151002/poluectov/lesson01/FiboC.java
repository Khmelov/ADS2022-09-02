package by.it.group151002.poluectov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;

        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int pisanoLen = pisano(m);
        long num = fastB(n % pisanoLen ) ;

        return num % m;
    }

    int pisano(int m) {
        if (m == 1) return 1;
        if (m == 2) return 3;

        // [6 * m] is the largest pisano cycle possible
        int[] fibo = new int[6 * m];
        int[] pisano = new int[6 * m];
        // constants
        fibo[0] = 0; pisano[0] = 0;
        fibo[1] = 1; pisano[1] = 1;
        fibo[2] = 1; pisano[2] = 1;
        int i;
        for (i = 3; i < fibo.length; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
            pisano[i] = fibo[i] % m;
            //the first 0 after 0 1 combination
            if (pisano[i - 1] == 0) {
                // (i - 1) is the distance between 0s
                if ((i - 1) % 2 == 1) {
                    return (i - 1) * 4;
                } else if (pisano[i] == 1) {
                    return (i - 1);
                } else return (i - 1) * 2;
            }
        }
        return -1;
    }

    long fastB(long n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n == 0) return 0;
        if (n < 2) return 1;
        long[] arr = new long[(int) n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[(int) n - 1];
    }

    void printPisano(int n, int m) {
        int[] arr = new int[n];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i < n; i++) {
            arr[i] = arr[i - 1] + (arr[i - 2]);
        }
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (int a : arr) {
            System.out.print(a % m + " ");
        }
    }


}

