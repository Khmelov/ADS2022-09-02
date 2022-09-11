package by.it.group151002.zavaliuk.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999_999_999_999_999_999L;
        int m = 10000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public long findPisanoPeriod(long m) {
        long firstFibNum = 0, secondFibNum = 1, sum;

        for (int i = 0; i < m * m; i++) {
            sum = (firstFibNum + secondFibNum) % m;
            firstFibNum = secondFibNum;
            secondFibNum = sum;
            if (firstFibNum == 0 && secondFibNum == 1)
                return i + 1;
        }
        return -1;
    }

    public long fasterC(long n, int m) {
        long numberPisano = n % findPisanoPeriod(m);

        long firstFibNum = 0, secondFibNum = 1, res = numberPisano;

        for (int i = 1; i < numberPisano; i++) {
            res = (firstFibNum + secondFibNum) % m;
            firstFibNum = secondFibNum;
            secondFibNum = res;
        }

        return res % m;
    }

}

