package by.it.group151002.shatko.lesson01;

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
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long findFib(int n) {
        long[] start = {1, 1, 2};

        if (n > 3) {
            for (int i = 3; i < n; i++) {
                long next = start[2] + start[1];
                start[0] = start[1];
                start[1] = start[2];
                start[2] = next;
            }
        } else {
            return start[n - 1];
        }

        return start[2];
    }

    long fasterC(long n, int m) {
        int period = 1;
        ArrayList<Integer> remainders = new ArrayList<>();
        int j = 3;

        remainders.add(0);
        remainders.add(1);
        remainders.add(1);

        if (m > 1) {
            while (findFib(j) % m != 0 || findFib(j + 1) % m != 1) {
                remainders.add((int) (findFib(j) % m));
                j++;
            }
            period = j;
        }

        return remainders.get((int)(n % period));
    }
}

