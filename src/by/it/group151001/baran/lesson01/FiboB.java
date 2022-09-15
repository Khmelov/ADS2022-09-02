package by.it.group151001.baran.lesson01;

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
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        BigInteger prev1 = new BigInteger("1");
        BigInteger prev2 = new BigInteger("1");
        BigInteger res = new BigInteger("0");
        if(n == 1) res = prev1;
        else if(n == 2) res = prev2;
        else
            for(int i = 3; i <= n; i++) {
                res = prev1.add(prev2);
                prev2 = prev1;
                prev1 = res;
            }

        return res;
    }

}

