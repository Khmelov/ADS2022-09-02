package by.it.group151003.haritonenko.lesson01;

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
        long[] fibo_arr = new long[m * 6];
        fibo_arr[0] = 0;
        fibo_arr[1] = 1;
        for (int i = 2; i < fibo_arr.length; i++) {
            fibo_arr[i] = (fibo_arr[i - 1] + fibo_arr[i - 2]) % m;
            if (fibo_arr[i] == 1 && fibo_arr[i - 1] == 0) {
                return fibo_arr[(int) (n % (i - 1))];
            }
        }
        return fibo_arr[(int) n];

    }


}

