package by.it.group151002.shatko.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[] start = {BigInteger.ONE, BigInteger.ONE, BigInteger.TWO};

        if (n > 3) {
            for (int i = 3; i < n; i++) {
                BigInteger next = start[2].add(start[1]);
                start[0] = start[1];
                start[1] = start[2];
                start[2] = next;
            }
        } else {
            return start[n - 1];
        }
        return start[2];
    }

}

