package by.it.group151003.stoyanov.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();
    private BigInteger[] mem;
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
        mem = new BigInteger[n + 1];
        mem[0] = BigInteger.ZERO;
        mem[1] = BigInteger.ONE;
        return helper(n);
    }

    private BigInteger helper(Integer n) {
        if (mem[n] != null) return mem[n];
        return mem[n] = helper(n - 1).add(helper(n - 2));
    }

    // итеративный способ
    /*
    private BigInteger helper(Integer n) {
        for (int i = 2; i <= n; i++) {
            mem[i] = mem[i - 1].add(mem[i - 2]);
        }
        return mem[n];
    }
    */


}

