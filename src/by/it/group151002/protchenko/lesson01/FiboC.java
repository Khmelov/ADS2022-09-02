package by.it.group151002.protchenko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 10;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }
    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (m == 1) {
            return n;
        }
        if (n <= 1) {
            return n;
        }
        long periodLength = 1;
        long prev;
        long curr = 1;
        long fiboPrev = 0;
        long fiboCurr = 1;
        long temp;

        boolean isFound = false;
        do {
            temp = fiboCurr;
            fiboCurr += fiboPrev;
            fiboPrev = temp;
            prev = curr;
            curr = fiboCurr % m;
            if (prev == 0 && curr == 1)
                isFound = true;
            periodLength++;
        } while (!isFound);
        periodLength--;

        n %= periodLength;
        fiboPrev = 0;
        fiboCurr = 1;
        for (int i = 2; i <= n; i++) {
            temp = fiboCurr;
            fiboCurr += fiboPrev;
            fiboPrev = temp;
        }

        System.out.println(periodLength);
        return fiboCurr % m;
    }


}

