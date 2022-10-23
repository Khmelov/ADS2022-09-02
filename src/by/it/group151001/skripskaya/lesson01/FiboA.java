package by.it.group151001.skripskaya.lesson01;

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

        int pred1=1, pred2=1, tech = 1, i;
        if ((n==1) | (n==2)) tech = 1;
      //      if (n==1) tech = 0;
      //      else tech = 1;
        else {
            for (i = 3; i <= n; ++i) {
                tech = pred1 + pred2;
                pred1 = pred2;
                pred2 = tech;
            };
        }
        return tech;


    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n <= 1)
            return BigInteger.valueOf(n);
        else
            return (slowA(n-1).add(slowA(n-2)));
    }



}

