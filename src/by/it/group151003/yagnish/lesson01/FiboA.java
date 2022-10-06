package by.it.group151003.yagnish.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        int n1 = 0;
        int n2 = 1;
        int tmp=0;
        for (int i = 0; i < n; i++) {
            tmp = n1 + n2;
            n1 = n2;
            n2 = tmp;
        }
        return n1;
    }


    BigInteger slowA(Integer n) {
       if (n<2) return BigInteger.valueOf(n);
        else
        return slowA(n-1).add(slowA(n-2));

    }



}

