package by.it.group151003.shafarenko.lesson01;

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
        int n = 11;
        int m = 5;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        n = n % pisanoPeriod(m);

        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;
        BigInteger temp;

        for (int i = 0; i < n - 1; ++i) {
            temp = prev;
            prev = curr;
            curr = temp.add(curr);
        }

        return curr.mod(BigInteger.valueOf(m)).longValue();

    }

    private long pisanoPeriod(long m) {
        long previous = 0;
        long current = 1;
        long result = 0;
        long tmp;


        for (long i = 0; i < m * m; i++) {
            tmp = previous;
            previous = current;
            current = (tmp + current) % m;

            if (previous == 0 && current == 1) {
                result = i + 1;
            }
        }

        return result;
    }

}

