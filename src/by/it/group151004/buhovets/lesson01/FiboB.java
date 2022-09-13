package by.it.group151004.buhovets.lesson01;

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

    public BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] arrFib = new BigInteger[n+1];
        BigInteger  result;
        arrFib[0]= BigInteger.ZERO;
        arrFib[1]= BigInteger.ONE;

        for(int i=2; i<=n;i++){
            arrFib[i]=arrFib[i-1].add(arrFib[i-2]);
        }

        result  = arrFib[n];
        return result;
    }

}

